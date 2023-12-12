package dz.web.api.algeriacitiesdetails.service;

import dz.web.api.algeriacitiesdetails.config.JacksonProviderConfig;
import dz.web.api.algeriacitiesdetails.entity.Wilaya;
import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.model.WilayaDtoRecord;
import dz.web.api.algeriacitiesdetails.repository.WilayaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @Author Messaoud GUERNOUTI on 10/26/2023
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class WilayaService {

    private final WilayaRepository wilayaRepository;

    /**
     *
     * @param details
     * @return List Of WilayaDtoRecord
     */
    public List<WilayaDtoRecord> getAllWilaya(WilayaDetail details,String sort_by) {

        Comparator<Wilaya> comparing;

        JacksonProviderConfig.fieldNames.clear();
        switch (details){
            case WILAYA_ONLY -> JacksonProviderConfig.fieldNames.add("dairaDtoList");
            case DAIRA_ONLY -> JacksonProviderConfig.fieldNames.add("communes");
            case COMMUNE_ONLY -> JacksonProviderConfig.fieldNames.add("postDetails");
            case ALL ->  JacksonProviderConfig.fieldNames.clear();
        }

        if (sort_by.equalsIgnoreCase("id") || sort_by.equalsIgnoreCase("code")){
            comparing = Comparator.comparing(Wilaya::getId);
        }
        else {
            comparing = Comparator.comparing(Wilaya::getWilayaNameFr);
        }

        log.info("Getting All Wilaya from Database with detail {} ",(JacksonProviderConfig.fieldNames.isEmpty())? details.name():"ALL");

        return wilayaRepository.findAll().stream()
                .sorted(comparing)
                .map(WilayaDtoRecord::build)
                .toList();
    }

    /**
     *
     * @param wilayaId
     * @param details
     * @return
     */
    public Optional<WilayaDtoRecord> getWilayaById(String wilayaId, WilayaDetail details) {

        JacksonProviderConfig.fieldNames.clear();
        switch (details){
            case DAIRA_ONLY -> JacksonProviderConfig.fieldNames.add("communes");
            case COMMUNE_ONLY -> JacksonProviderConfig.fieldNames.add("postDetails");
            default ->  JacksonProviderConfig.fieldNames.clear();
        }

        log.info("Getting Wilaya {} from Database with detail {} ",
                wilayaId,
                (JacksonProviderConfig.fieldNames.isEmpty())? details.name():"ALL"
        );


        return wilayaRepository
                .findWilayasByWilayaCode(wilayaId)
                .map(WilayaDtoRecord::build);
    }

}
