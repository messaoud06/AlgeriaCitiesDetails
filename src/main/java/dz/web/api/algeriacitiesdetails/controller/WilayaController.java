package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.model.WilayaDtoRecord;
import dz.web.api.algeriacitiesdetails.service.WilayaService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
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
    private final MeterRegistry meterRegistry;


    @GetMapping("/")
    ResponseEntity<?> getWilayas(@RequestParam(required = false,defaultValue = "DAIRA_ONLY") @NotNull WilayaDetail detail){

        log.info("Getting All Wilaya with detail {}", detail.name());

        Counter counter = Counter.builder("wilaya_all")
                .tag("title", "all_wilaya")
                .description("a number of requests to get all wilaya")
                .register(meterRegistry);
        counter.increment();

       return ResponseEntity.ok(wilayaService.getAllWilaya(detail));
    }

    @GetMapping(  {"/{wilayaId}","/{wilayaId}/"})
    ResponseEntity<?> gerWilayaById(@PathVariable String wilayaId,@RequestParam(required = false,defaultValue =  "DAIRA_ONLY") @NotNull WilayaDetail detail){

        Counter counter = Counter.builder("wilaya_byId")
                .tag("title", "wiliya_byId")
                .description("a number of requests to wilaya byId")
                .register(meterRegistry);
        counter.increment();

        return wilayaService.getWilayaById(wilayaId,detail)
                .map(wilaya -> {
                    log.info("Getting Wilaya {} with detail {}", wilayaId,detail);
                    return ResponseEntity
                            .ok()
                            .body(WilayaDtoRecord.build(wilaya));
                }).orElseGet(() ->{
                    log.warn("There no content for wilaya {} with detail {}",wilayaId,detail);
                    return ResponseEntity.notFound().build();
                });
    }
}
