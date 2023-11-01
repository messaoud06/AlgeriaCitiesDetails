package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.service.DairaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author Messaoud GUERNOUTI on 10/30/2023
 */
@RestController
@RequestMapping("/wilaya/{wilayaId}/daira")
@RequiredArgsConstructor
public class DairaController {

    private final DairaService dairaService;


    @GetMapping("/")
    ResponseEntity<?> getAll(@PathVariable String wilayaId,
                             @RequestParam(required = false,defaultValue = "ALL") WilayaDetail detail){

        return ResponseEntity.ok(dairaService.getAll(wilayaId,detail));
    }


    @GetMapping({"/{dairaId}","/{dairaId}/"})
    ResponseEntity<?> getDairaByName(@PathVariable String wilayaId,
                                         @PathVariable Long dairaId,
                                         @RequestParam(required = false,defaultValue = "ALL") WilayaDetail detail){

        return dairaService.getDairaByName(wilayaId, dairaId,detail).map(daira -> {
              return ResponseEntity
                      .ok()
                      .body(daira);
          }).orElse(ResponseEntity.notFound().build());

    }
}
