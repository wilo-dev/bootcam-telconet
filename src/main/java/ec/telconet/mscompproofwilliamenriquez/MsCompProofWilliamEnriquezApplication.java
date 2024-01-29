package ec.telconet.mscompproofwilliamenriquez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;


@SpringBootApplication
public class MsCompProofWilliamEnriquezApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCompProofWilliamEnriquezApplication.class, args);
    }


//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
//        configuration.addAllowedHeader(null);
//        configuration.addAllowedMethod("POST");
//        source.registerCorsConfiguration("/**", configuration);
//        return new CorsFilter(source);
//    }
}
