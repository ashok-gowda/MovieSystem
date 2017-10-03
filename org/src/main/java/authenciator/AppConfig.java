package authenciator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import jobs.SimilarityFinder;

@Configuration
@EnableScheduling
public class AppConfig {
 
    @Bean
    public SimilarityFinder bean() {
        return new SimilarityFinder();
    }
 
}