package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.service.CommuneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Messaoud GUERNOUTI on 10/30/2023
 */
@RestController
@RequestMapping("/wilaya/{wilayaId}/daira/{dairaId}/commune")
@RequiredArgsConstructor
@Log4j2
public class CommuneController {

    private final CommuneService communeService;


    @GetMapping("/")
    ResponseEntity<?> getAllCommuneByDairaId(@PathVariable String wilayaId,
                                             @PathVariable Long dairaId,
                                             @RequestParam(required = false,defaultValue = "ALL") WilayaDetail detail){

        log.info("Getting All Commune for Daira {} for WilayaCode {} with detail {}",dairaId,wilayaId, detail.name());
        return ResponseEntity.ok(communeService.getAllByDairaId(dairaId,wilayaId,detail));

    }

    @GetMapping({"/{communeId}","/{communeId}/"})
    ResponseEntity<?> getCommuneById(@PathVariable String wilayaId,
                                     @PathVariable Long dairaId,
                                     @PathVariable Long communeId){


        return communeService.getCommuneById(communeId,dairaId,wilayaId)
                .map(commune -> {
                    log.info("Getting Commune {}  Daira {} for WilayaCode {} with detail {}",communeId,dairaId, wilayaId);
                    return ResponseEntity.ok(commune);
                }).orElseGet(() ->{
                    log.warn("There no content for commune {} Daira {}  wilaya {} with detail {}",communeId,dairaId,wilayaId);
                    return ResponseEntity.notFound().build();
                });

    }
}
