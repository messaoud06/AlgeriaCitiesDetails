package dz.web.api.algeriacitiesdetails.service;

import dz.web.api.algeriacitiesdetails.config.JacksonProviderConfig;
import dz.web.api.algeriacitiesdetails.entity.Daira;
import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.helper.Utils;
import dz.web.api.algeriacitiesdetails.model.DairaDto;
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
    public Optional<DairaDto> getDairaByName(String wilayaid, Long dairaId, WilayaDetail detail) {

        Utils.updateJsonFilter(detail);

        log.info("Getting Daira {} Wilaya {} from Database with detail {} ",
                dairaId,
                wilayaid,
                (JacksonProviderConfig.fieldNames.isEmpty())? detail.name():"ALL"
        );

        return dairaRepository
                .findDairaByIdAndWilaya_WilayaCode(dairaId,wilayaid)
                .map(DairaDto::build);

    }

    public List<DairaDto> getAll(String wilyaCode,WilayaDetail detail) {

        Utils.updateJsonFilter(detail);


        log.info("Getting All Daira for Wilaya {} from Database with detail {} ",
                wilyaCode,
                (JacksonProviderConfig.fieldNames.isEmpty())? detail.name():"ALL");

        return dairaRepository
                .findAllByWilaya_WilayaCode(wilyaCode)
                .stream()
                .map(DairaDto::build)
                .toList();
    }

    public Optional<Daira> getDairaById(Long id) {

        return dairaRepository.findById(id);
    }
}
