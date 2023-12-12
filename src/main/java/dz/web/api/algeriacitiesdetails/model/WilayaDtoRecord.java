package dz.web.api.algeriacitiesdetails.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dz.web.api.algeriacitiesdetails.config.JacksonProviderConfig;
import dz.web.api.algeriacitiesdetails.entity.Wilaya;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

/**
 * @Author Messaoud GUERNOUTI on 11/9/2023
 */
@Schema(name = "Wilaya",
        description = "Wilaya information"
)
@JsonFilter("detailsFilter")
@JsonPropertyOrder({"wilayaCode","WilayaNameFr","wilayaNameAR",JacksonProviderConfig.JSON_FILTER_DAIRA})
public record WilayaDtoRecord(
                            @NotEmpty(message = "Wilaya Code can not be a null or empty")
                            @Pattern(regexp="(^$|[0-9]{2})",message = "Wilaya Code must be 2 digits")
                            @Schema(
                                    description = "Administrative Wilaya Code ", example = "16"
                            )
                            String wilayaCode,

                            @NotEmpty(message = "Wilaya Name can not be a null or empty")
                            @Schema(
                                    description = "Administrative Wilaya name [French] ", example = "Alger"
                            )
                            String WilayaNameFr,

                            @NotEmpty(message = "Wilaya Name can not be a null or empty")
                            @Schema(
                                    description = "Administrative Wilaya name [Arabic] ", example = "الجزائر"
                            )
                            String wilayaNameAR,

                            @NotEmpty(message = "List od Daira can not be a null or empty")
                            @Schema(

                                    description = "List of Administrative Daira in selected Wilaya"
                            )
                            @JsonProperty(JacksonProviderConfig.JSON_FILTER_DAIRA)
                            List<DairaDto> dairaList) {

    /**
     *
     * @param wilaya
     * @return
     */
    public static WilayaDtoRecord build(Wilaya wilaya){

        List<DairaDto> dairaDtoList = wilaya.getDairaSet()
                .stream()
                .map(DairaDto::build)
                .toList();

        return new WilayaDtoRecord(wilaya.getWilayaCode(),wilaya.getWilayaNameFr(),wilaya.getWilayaNameAr(),dairaDtoList);
    }

}
