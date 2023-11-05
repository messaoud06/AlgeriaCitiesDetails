package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.service.DairaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author Messaoud GUERNOUTI on 10/30/2023
 */
@RestController
@RequestMapping("/wilaya/{wilayaId}/daira")
@RequiredArgsConstructor
@Log4j2
public class DairaController {

    private final DairaService dairaService;


    @GetMapping("/")
    ResponseEntity<?> getAll(@PathVariable String wilayaId,
                             @RequestParam(required = false,defaultValue = "ALL") WilayaDetail detail){

        log.info("Getting All Daira for WilayaCode {} with detail {}",wilayaId, detail.name());
        return ResponseEntity.ok(dairaService.getAll(wilayaId,detail));
    }


    @GetMapping({"/{dairaId}","/{dairaId}/"})
    ResponseEntity<?> getDairaByName(@PathVariable String wilayaId,
                                         @PathVariable Long dairaId,
                                         @RequestParam(required = false,defaultValue = "ALL") WilayaDetail detail){

        return dairaService.getDairaByName(wilayaId, dairaId,detail).map(daira -> {
            log.info("Getting  Daira {} for WilayaCode {} with detail {}",dairaId, wilayaId, detail.name());
              return ResponseEntity
                      .ok()
                      .body(daira);
        }).orElseGet(() ->{
            log.warn("There no content for Daira {}  wilaya {} with detail {}",dairaId,wilayaId,detail);
            return ResponseEntity.notFound().build();
        });

    }
}
