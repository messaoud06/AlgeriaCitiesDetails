package dz.web.api.algeriacitiesdetails.service;

import dz.web.api.algeriacitiesdetails.model.PrayerTimes;
import dz.web.api.algeriacitiesdetails.model.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author Messaoud GUERNOUTI on 2/26/2024
 */
@Service
@RequiredArgsConstructor
public class PrayerTimesService {



    private final   WebClient adhanWebClient;

    public PrayerTimes getPrayer(String state, String date){


        Root root = adhanWebClient.get()
               .uri(uriBuilder -> {
                    return uriBuilder
                            .path(date)
                            .queryParam("country","DZ")
                            .queryParam("method","3")
                            .queryParam("city",state)
                            .build();
                            })
                .retrieve()
                .bodyToMono(Root.class)
                .onErrorResume(throwable -> {
                    System.out.println(throwable.getMessage());
                    throwable.printStackTrace();
                    return Mono.empty();
                })
                .block();

       return PrayerTimes.build(root) ;
    }
}
