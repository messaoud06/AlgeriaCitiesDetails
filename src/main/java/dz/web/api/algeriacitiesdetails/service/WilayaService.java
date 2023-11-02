package dz.web.api.algeriacitiesdetails.service;

import dz.web.api.algeriacitiesdetails.config.JacksonProviderConfig;
import dz.web.api.algeriacitiesdetails.entity.Wilaya;
import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.model.WilayaDto;
import dz.web.api.algeriacitiesdetails.repository.WilayaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author Messaoud GUERNOUTI on 10/26/2023
 */
@Service
@RequiredArgsConstructor
public class WilayaService {

    private final WilayaRepository wilayaRepository;

    public List<WilayaDto> getAllWilaya(WilayaDetail details) {

        JacksonProviderConfig.fieldNames.clear();
        switch (details){
            case WILAYA_ONLY -> JacksonProviderConfig.fieldNames.add("dairaList");
            case DAIRA_ONLY -> JacksonProviderConfig.fieldNames.add("communes");
            case COMMUNE_ONLY -> JacksonProviderConfig.fieldNames.add("postDetails");
            case ALL ->  JacksonProviderConfig.fieldNames.clear();
        }
        return wilayaRepository.findAll().stream()
                .sorted(Comparator.comparing(Wilaya::getId))
                .map(wilaya -> WilayaDto.build(wilaya))
                .collect(Collectors.toList());

    }

    public Optional<Wilaya> getWilayaById(String wilayaId, WilayaDetail details) {

        JacksonProviderConfig.fieldNames.clear();
        switch (details){
            case DAIRA_ONLY -> JacksonProviderConfig.fieldNames.add("communes");
            case COMMUNE_ONLY -> JacksonProviderConfig.fieldNames.add("postDetails");
            case ALL ->  JacksonProviderConfig.fieldNames.clear();
        }

        return wilayaRepository.findWilayasByWilayaCode(wilayaId);
    }

}
