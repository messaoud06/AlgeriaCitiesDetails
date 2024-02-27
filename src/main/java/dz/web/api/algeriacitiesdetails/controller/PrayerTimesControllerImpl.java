package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.helper.Utils;
import dz.web.api.algeriacitiesdetails.interfaces.PrayerTimesController;
import dz.web.api.algeriacitiesdetails.model.PrayerTimes;
import dz.web.api.algeriacitiesdetails.model.WilayaDtoRecord;
import dz.web.api.algeriacitiesdetails.service.CommuneService;
import dz.web.api.algeriacitiesdetails.service.DairaService;
import dz.web.api.algeriacitiesdetails.service.PrayerTimesService;
import dz.web.api.algeriacitiesdetails.service.WilayaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @Author Messaoud GUERNOUTI on 2/26/2024
 */
@RequiredArgsConstructor
@Log4j2
@RestController
public class PrayerTimesControllerImpl implements PrayerTimesController {

    private final PrayerTimesService prayerTimesService;
    private final WilayaService wilayaService;
    private final DairaService dairaService;
    private final CommuneService communeService;
    @Override
    public ResponseEntity<PrayerTimes> getPrayerTimes(String id, String date) {

        if(date==null){
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            date = today.format(formatter);
        }

        if(!Utils.isValidDate("dd-MM-yyyy",date)){
            return ResponseEntity.badRequest().build();
        }


        String nameById = Utils.getNameById(id, wilayaService, dairaService, communeService);

        if(nameById==null){
            return ResponseEntity.badRequest().build();
        }

        PrayerTimes prayer = prayerTimesService.getPrayer(nameById, date);
        return ResponseEntity.ok(prayer);
    }
}
