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

                // Variável de controle de status
                String status = (String) payload.get("status");

                if ("EMPRESTIMO".equals(eventType) || "FINANCIAMENTO".equals(eventType)) {

                    if ("EMPRESTIMO".equals(eventType)) {
                        loans++;
                    } else {
                        financings++;
                    }

                    // Cálculo da Parcela e Soma (APENAS SE ESTIVER ATIVO)
                    // Filtra para somar apenas os compromissos ATIVOS
                    if ("ATIVO".equals(status)) {
                        Object valueObj = payload.get("value"); // Valor total
                        Object installmentsObj = payload.get("installments"); // Qtd. parcelas

                        if (valueObj instanceof Number && installmentsObj instanceof Number) {
                            double sum = ((Number) valueObj).doubleValue();
                            int installments = ((Number) installmentsObj).intValue();

                            if (installments > 0) {
                                // Parcela Mensal (simples)
                                sumActiveMonthlyInstallments += sum / installments;
                            }
                        }
                    }
                } else if ("ATRASO".equals(eventType)) {
                    latePayments12m++;

                    // Extrai o maior atraso
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

            // Extração da Renda e Cálculo do Comprometimento
            double income = customer.getIncome();
            double incomeCommitmentPercentage = 0.0;

            if (income > 0) {
                // Cálculo: Comprometimento = (Soma das Parcelas ATIVAS) / Renda
                incomeCommitmentPercentage = sumActiveMonthlyInstallments / income;
            }
            // Cálculo da Idade
            // Usa LocalDate.now() para calcular a idade no dia de hoje
            LocalDate dateOfbirth = customer.getDateOfbirth();
            int age = 0;
            if (dateOfbirth != null) {
                age = Period.between(dateOfbirth, LocalDate.now()).getYears();
            }

            // Atribuição dos Valores ao Array
            // Ordem -> [emprestimos, financiamentos, atrasos12m, maiorAtraso, renda, comprometimento, idade, restricao]
            data[0] = loans;
            data[1] = financings;
            data[2] = latePayments12m;
            data[3] = maxLatePayment;
            data[4] = income;
            data[5] = incomeCommitmentPercentage;
            data[6] = age;
            data[7] = restriction;

        }
        return data;
    }
}
