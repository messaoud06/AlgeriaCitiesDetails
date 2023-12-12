package dz.web.api.algeriacitiesdetails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import dz.web.api.algeriacitiesdetails.config.JacksonProviderConfig;
import dz.web.api.algeriacitiesdetails.entity.Commune;
import dz.web.api.algeriacitiesdetails.entity.Daira;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @Author Messaoud GUERNOUTI on 11/22/2023
 */
@Schema(name = "Commune",
        description = "Commune information"
)
public record CommuneDto(String communeNameFr,
                         String communeNameAr,
                         @JsonProperty(JacksonProviderConfig.JSON_FILTER_POST)
                         List<PostDetailDto> postDetailDtoList) {

    public static CommuneDto build(Commune commune){

        List<PostDetailDto> postDetailDto = commune.getPostDetails()
                .stream()
                .map(PostDetailDto::build)
                .toList();
        return new CommuneDto(commune.getCommuneNameFr(), commune.getCommuneNameAr(), postDetailDto);
    }
}
