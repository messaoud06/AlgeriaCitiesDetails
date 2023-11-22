package dz.web.api.algeriacitiesdetails.model;

import dz.web.api.algeriacitiesdetails.entity.Daira;
import dz.web.api.algeriacitiesdetails.entity.Wilaya;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Messaoud GUERNOUTI on 11/22/2023
 */
public record DairaDto(String dairaNameFr, String dairaNameAr, List<CommuneDto> communeDtoList) {


    public static DairaDto build(Daira daira){

        List<CommuneDto> communeDtoList = daira.getCommunes()
                .stream()
                .map(CommuneDto::build)
                .toList();
        return new DairaDto(daira.getDairaNameFr(), daira.getDairaNameAr(), communeDtoList);
    }
}
