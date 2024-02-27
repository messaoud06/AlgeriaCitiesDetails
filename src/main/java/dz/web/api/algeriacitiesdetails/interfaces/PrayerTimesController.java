package dz.web.api.algeriacitiesdetails.interfaces;

import dz.web.api.algeriacitiesdetails.model.PrayerTimes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Messaoud GUERNOUTI on 2/26/2024
 */
@RestController
@RequestMapping(value = "/prayer",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
public interface PrayerTimesController {

    @RequestMapping("/{id}")
    ResponseEntity<PrayerTimes> getPrayerTimes(@PathVariable(value = "id",required = true) String id, @RequestParam(value = "date",required = false) String date);
}
