package dz.web.api.algeriacitiesdetails.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import dz.web.api.algeriacitiesdetails.config.JacksonProviderConfig;
import dz.web.api.algeriacitiesdetails.entity.Daira;
import dz.web.api.algeriacitiesdetails.entity.Wilaya;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Messaoud GUERNOUTI on 11/22/2023
 */
@Schema(name = "Daira",
        description = "Daira information"
)
@JsonFilter("detailsFilter")
public record DairaDto( long id,
                       String dairaNameFr,
                       String dairaNameAr,
                       @JsonProperty(JacksonProviderConfig.JSON_FILTER_COMMUNE)
                       List<CommuneDto> communeDtoList) {


    public static DairaDto build(Daira daira){

        List<CommuneDto> communeDtoList = daira.getCommunes()
                .stream()
                .map(CommuneDto::build)
                .toList();
        return new DairaDto(daira.getId(), daira.getDairaNameFr(), daira.getDairaNameAr(), communeDtoList);
    }
}
