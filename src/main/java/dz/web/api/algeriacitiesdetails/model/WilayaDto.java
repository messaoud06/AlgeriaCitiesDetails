package dz.web.api.algeriacitiesdetails.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import dz.web.api.algeriacitiesdetails.entity.Daira;
import dz.web.api.algeriacitiesdetails.entity.Wilaya;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Messaoud GUERNOUTI on 10/26/2023
 */
@Setter
@Getter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("detailsFilter")
public class WilayaDto {

    private String wilayaCode;

    private String wilayaNameFr;

    private String wilayaNameAr;

    private List<Daira> dairaList;



    public static WilayaDto build(Wilaya wilaya){
        return new WilayaDto(wilaya.getWilayaCode(),wilaya.getWilayaNameFr(),wilaya.getWilayaNameAr(),new ArrayList<>(wilaya.getDairaSet()));
    }
}
