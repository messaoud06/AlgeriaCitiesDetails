package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.service.CommuneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Messaoud GUERNOUTI on 10/30/2023
 */
@RestController
@RequestMapping("/wilaya/{wilayaId}/daira/{dairaId}/commune")
@RequiredArgsConstructor
public class CommuneController {

    private final CommuneService communeService;


    @GetMapping("/")
    ResponseEntity<?> getAllCommuneByDairaId(@PathVariable String wilayaId,
                                             @PathVariable Long dairaId,
                                             @RequestParam(required = false,defaultValue = "ALL") WilayaDetail detail){

        return ResponseEntity.ok(communeService.getAllByDairaId(dairaId,wilayaId,detail));

    }

    @GetMapping({"/{communeId}","/{communeId}/"})
    ResponseEntity<?> getCommuneById(@PathVariable String wilayaId,
                                     @PathVariable Long dairaId,
                                     @PathVariable Long communeId){


        return communeService.getCommuneById(communeId,dairaId,wilayaId)
                .map(commune -> {
                    return ResponseEntity.ok(commune);
                })
                .orElse(ResponseEntity.notFound().build());

    }
}
