package dz.web.api.algeriacitiesdetails.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;


import java.time.Duration;

/**
 * @Author Messaoud GUERNOUTI on 2/26/2024
 */
@Configuration
public class WebClientConfig {

    @Value("${server.adhan.api.url}")
    private  String PRAYER_TIME_URL;

    @Bean("adhanWebClient")
    public WebClient adhanWebClient(){
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(20));

        WebClient webClient = WebClient
                .builder()
                .filters(exchangeFilterFunctions -> {
                                exchangeFilterFunctions.add(logRequest());
                                })
                .baseUrl(PRAYER_TIME_URL)
                .build();

        return webClient;
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {

            StringBuilder sb = new StringBuilder("Request: \n");

            sb.append("Method : "+clientRequest.method());
            sb.append(" url : "+clientRequest.url());

            System.out.println(sb);

            return Mono.just(clientRequest);
        });
    }
}
