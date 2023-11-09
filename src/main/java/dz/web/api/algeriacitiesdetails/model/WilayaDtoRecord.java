package dz.web.api.algeriacitiesdetails.model;

import dz.web.api.algeriacitiesdetails.entity.Daira;
import dz.web.api.algeriacitiesdetails.entity.Wilaya;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Messaoud GUERNOUTI on 11/9/2023
 */
public record WilayaDtoRecord(String wilayaCode, String WilayaNameFr, String wilayaNameAR, List<Daira> dairaList) {

    public static WilayaDtoRecord build(Wilaya wilaya){
        return new WilayaDtoRecord(wilaya.getWilayaCode(),wilaya.getWilayaNameFr(),wilaya.getWilayaNameAr(),new ArrayList<>(wilaya.getDairaSet()));
    }

}
