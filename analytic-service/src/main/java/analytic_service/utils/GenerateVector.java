package analytic_service.utils;

import analytic_service.dto.CustomerResponseDTO;
import analytic_service.dto.HistoryResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

@Component
public class GenerateVector {

    public double[] generateData(List<HistoryResponseDTO> histories, CustomerResponseDTO customer) {

        double[] data = new double[8];

        int loans = 0;
        int financings = 0;
        int latePayments12m = 0;
        int maxLatePayment = 0;
        int restriction = 0;

        // Variável para calcular a soma das obrigações mensais ATIVAS
        double sumActiveMonthlyInstallments = 0.0;

        for (HistoryResponseDTO history : histories) {
            Map<String, Object> payload = history.getPayload();
            Object eventTypeObj = payload.get("eventType");

            if (eventTypeObj instanceof String) {
                String eventType = (String) eventTypeObj;

                String status = (String) payload.get("status");

                if ("EMPRESTIMO".equals(eventType) || "FINANCIAMENTO".equals(eventType)) {

                    if ("ATIVO".equals(status)) {
                        if ("EMPRESTIMO".equals(eventType)) {
                            loans++;
                        } else {
                            financings++;
                        }
                        Object valueObj = payload.get("value");
                        Object installmentsObj = payload.get("installments");

                        if (valueObj instanceof Number && installmentsObj instanceof Number) {
                            double sum = ((Number) valueObj).doubleValue();
                            int installments = ((Number) installmentsObj).intValue();

                            if (installments > 0) {
                                sumActiveMonthlyInstallments += sum / installments;
                            }
                        }
                    }

                } else if ("ATRASO".equals(eventType)) {
                    latePayments12m++;

                    Object lateDaysObj = payload.get("days_overdue");
                    if (lateDaysObj instanceof Number) {
                        int lateDays = ((Number) lateDaysObj).intValue();
                        if (lateDays > maxLatePayment) {
                            maxLatePayment = lateDays;
                        }
                    }

                } else if ("INADIMPLENCIA".equals(eventType)) {
                    restriction = 1;
                }
            }
        }

        // Extração da Renda e Cálculo do Comprometimento
        double income = customer.getIncome();
        double incomeCommitmentPercentage = 0.0;

        if (income > 0) {
            incomeCommitmentPercentage = sumActiveMonthlyInstallments / income;
            incomeCommitmentPercentage = Math.round(incomeCommitmentPercentage * 100.0) / 100.0;
        }

        // Cálculo da Idade
        LocalDate dateOfbirth = customer.getDateOfbirth();
        int age = 0;
        if (dateOfbirth != null) {
            age = Period.between(dateOfbirth, LocalDate.now()).getYears();
        }

        // ===== CLIPPING / LIMITES =====
        loans = Math.min(loans, 14);                       // dataset treino: 0–14
        financings = Math.min(financings, 6);             // 0–6
        latePayments12m = Math.min(latePayments12m, 11);  // 0–11
        maxLatePayment = Math.min(maxLatePayment, 59);    // 0–59
        incomeCommitmentPercentage = Math.min(incomeCommitmentPercentage, 0.8); // 0–0.8
        income = Math.min(income, 18000);                 // 1000–18000
        age = Math.min(age, 67);                          // 18–67

        // Ordem -> [emprestimos, financiamentos, atrasos12m, maiorAtraso, renda, comprometimento, idade, restricao]
        data[0] = loans;
        data[1] = financings;
        data[2] = latePayments12m;
        data[3] = maxLatePayment;
        data[4] = income;
        data[5] = incomeCommitmentPercentage;
        data[6] = age;
        data[7] = restriction;

        return data;
    }
}