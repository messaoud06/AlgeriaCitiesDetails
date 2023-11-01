package dz.web.api.algeriacitiesdetails.service;

import dz.web.api.algeriacitiesdetails.config.JacksonProviderConfig;
import dz.web.api.algeriacitiesdetails.entity.Commune;
import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.repository.CommuneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Messaoud GUERNOUTI on 10/30/2023
 */
@Service
@RequiredArgsConstructor
public class CommuneService {

    private final CommuneRepository communeRepository;

    public List<Commune> getAllByDairaId(Long dairaId, String wilayaid, WilayaDetail detail) {

        JacksonProviderConfig.fieldNames.clear();
        switch (detail){
            case COMMUNE_ONLY -> JacksonProviderConfig.fieldNames.add("postDetails");
            case ALL ->  JacksonProviderConfig.fieldNames.clear();
        }


        return communeRepository.findAllByDaira_IdAndDaira_Wilaya_WilayaCode(dairaId,wilayaid);
    }

    public Optional<Commune> getCommuneById(Long communeId, Long dairaId, String wilayaid) {
        JacksonProviderConfig.fieldNames.clear();

        return communeRepository.findCommuneByIdAndDaira_IdAndDaira_Wilaya_WilayaCode(communeId,dairaId,wilayaid);
    }
}
