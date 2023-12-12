package dz.web.api.algeriacitiesdetails.service;

import dz.web.api.algeriacitiesdetails.config.JacksonProviderConfig;
import dz.web.api.algeriacitiesdetails.entity.Commune;
import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.helper.Utils;
import dz.web.api.algeriacitiesdetails.model.CommuneDto;
import dz.web.api.algeriacitiesdetails.repository.CommuneRepository;
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
public class CommuneService {

    private final CommuneRepository communeRepository;

    public List<CommuneDto> getAllByDairaId(Long dairaId, String wilayaid, WilayaDetail detail) {

        Utils.updateJsonFilter(detail);

        log.info("Getting All Commune Daira {} for Wilaya {} from Database with detail {} ",
                dairaId,
                wilayaid,
                (JacksonProviderConfig.fieldNames.isEmpty())? detail.name():"ALL");

        return communeRepository
                .findAllByDaira_IdAndDaira_Wilaya_WilayaCode(dairaId,wilayaid)
                .stream()
                .map(CommuneDto::build)
                .toList();
    }

    public Optional<CommuneDto> getCommuneById(Long communeId, Long dairaId, String wilayaid) {

        JacksonProviderConfig.fieldNames.clear();

        log.info("Getting Commune {} Daira {} Wilaya {} from Database ",
                communeId,
                dairaId,
                wilayaid
        );

        return communeRepository
                .findCommuneByIdAndDaira_IdAndDaira_Wilaya_WilayaCode(communeId,dairaId,wilayaid)
                .map(CommuneDto::build);
    }
}
