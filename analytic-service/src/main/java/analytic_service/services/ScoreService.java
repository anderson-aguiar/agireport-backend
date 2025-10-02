package analytic_service.services;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    // Injetamos diretamente o Modelo e o Normalizador, que agora são Beans (@Bean)
    private final MultiLayerNetwork model;
    private final NormalizerStandardize normalizer;

    private static final double SCORE_MIN = 200;
    private static final double SCORE_MAX = 1000;

    public ScoreService(MultiLayerNetwork model, NormalizerStandardize normalizer) {
        // Atribuição direta dos Beans injetados
        this.model = model;
        this.normalizer = normalizer;
    }

    public int calc(double[] features) {
        // Transforma vetor em INDArray (1 linha, n colunas)
        INDArray input = Nd4j.create(features).reshape(1, features.length);

        DataSet ds = new DataSet(input, null);
        normalizer.transform(ds);

        // Faz previsão, usando o Bean injetado
        INDArray output = this.model.output(ds.getFeatures());

        // Converte para escala 200-1000
        double scaledScore = SCORE_MIN + output.getDouble(0) * (SCORE_MAX - SCORE_MIN);

        // Garante limites e arredonda para inteiro
        int finalScore = (int) Math.round(Math.max(SCORE_MIN, Math.min(SCORE_MAX, scaledScore)));

        return finalScore;
    }
}