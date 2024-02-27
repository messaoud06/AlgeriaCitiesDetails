package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.exception.GenericException;
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
import org.springframework.http.HttpStatus;
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


        PrayerTimes prayer = prayerTimesService.getPrayer(
                Utils.getNameById(id, wilayaService, dairaService, communeService),
                Utils.validatePrayerDate(date));

        return ResponseEntity.ok(prayer);
    }
}
