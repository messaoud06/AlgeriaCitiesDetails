package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.model.WilayaDto;
import dz.web.api.algeriacitiesdetails.service.WilayaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Messaoud GUERNOUTI on 10/26/2023
 */
@RestController
@RequestMapping(value = "/wilaya",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Log4j2
public class WilayaController {

    private final WilayaService wilayaService;


    @GetMapping("/")
    ResponseEntity<?> getWilayas(@RequestParam(required = false,defaultValue = "DAIRA_ONLY") WilayaDetail detail){

        log.info("Getting All Wilaya with detail {}", detail.name());

       return ResponseEntity.ok(wilayaService.getAllWilaya(detail));
    }

    @GetMapping(  {"/{wilayaId}","/{wilayaId}/"})
    ResponseEntity<?> gerWilayaById(@PathVariable String wilayaId,@RequestParam(required = false,defaultValue =  "DAIRA_ONLY") WilayaDetail detail){

        return wilayaService.getWilayaById(wilayaId,detail)
                .map(wilaya -> {
                    log.info("Getting Wilaya {} with detail {}", wilayaId,detail);
                    return ResponseEntity
                            .ok()
                            .body(WilayaDto.build(wilaya));
                }).orElseGet(() ->{
                    log.warn("There no content for wilaya {} with detail {}",wilayaId,detail);
                    return ResponseEntity.notFound().build();
                });
    }
}
