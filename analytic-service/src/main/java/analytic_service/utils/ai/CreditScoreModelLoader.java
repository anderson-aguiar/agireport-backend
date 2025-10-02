package analytic_service.utils.ai;


import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.dataset.api.preprocessor.serializer.NormalizerSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class CreditScoreModelLoader {
    @Bean
    public MultiLayerNetwork dl4jModel() throws IOException {
        try (InputStream modelStream =
                     new ClassPathResource("models/credit_score_model.zip").getInputStream()) {
            return ModelSerializer.restoreMultiLayerNetwork(modelStream);
        }
    }

    @Bean
    public NormalizerStandardize dl4jNormalizer() throws IOException {
        try (InputStream normStream =
                     new ClassPathResource("models/credit_score_normalizer.bin").getInputStream()) {
            return (NormalizerStandardize) NormalizerSerializer.getDefault()
                    .restore(normStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
   /* @Bean
    public NormalizerStandardize dl4jNormalizer() throws IOException {
        try (InputStream normStream =
                     new ClassPathResource("models/credit_score_normalizer.bin").getInputStream()) {
            return ModelSerializer.restoreNormalizerFromInputStream(normStream);
        }
    }*/

    }
}
