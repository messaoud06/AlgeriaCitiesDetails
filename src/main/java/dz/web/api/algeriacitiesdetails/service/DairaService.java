package dz.web.api.algeriacitiesdetails.service;

import dz.web.api.algeriacitiesdetails.config.JacksonProviderConfig;
import dz.web.api.algeriacitiesdetails.entity.Daira;
import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.repository.DairaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Messaoud GUERNOUTI on 10/30/2023
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class DairaService {

    private final DairaRepository dairaRepository;
    public Optional<Daira> getDairaByName(String wilayaid, Long dairaId, WilayaDetail detail) {

        JacksonProviderConfig.fieldNames.clear();
        switch (detail){
            case DAIRA_ONLY, COMMUNE_ONLY -> JacksonProviderConfig.fieldNames.add("postDetails");
            case ALL ->  JacksonProviderConfig.fieldNames.clear();
        }

        log.info("Getting Daira {} Wilaya {} from Database with detail {} ",
                dairaId,
                wilayaid,
                (JacksonProviderConfig.fieldNames.size()>0)? JacksonProviderConfig.fieldNames.toArray()[0]:"ALL"
        );

        return dairaRepository.findDairaByIdAndWilaya_WilayaCode(dairaId,wilayaid);

    }

    public List<Daira> getAll(String wilyaCode,WilayaDetail detail) {

        JacksonProviderConfig.fieldNames.clear();
        switch (detail){
            case COMMUNE_ONLY -> JacksonProviderConfig.fieldNames.add("postDetails");
            case ALL ->  JacksonProviderConfig.fieldNames.clear();
        }

        log.info("Getting All Daira for Wilaya {} from Database with detail {} ",
                wilyaCode,
                (JacksonProviderConfig.fieldNames.size()>0)? JacksonProviderConfig.fieldNames.toArray()[0]:"ALL");



        return dairaRepository.findAllByWilaya_WilayaCode(wilyaCode);
    }
}
