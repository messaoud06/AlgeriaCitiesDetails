package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.model.WilayaDtoRecord;
import dz.web.api.algeriacitiesdetails.service.WilayaService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Messaoud GUERNOUTI on 10/26/2023
 */

@Tag(
        name = "Wilaya details",
        description = "REST APIs in Algeria Cities to FETCH Wilaya details"
)
@RestController
@RequestMapping(value = "/wilaya",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Log4j2
public class WilayaController {

    private final WilayaService wilayaService;
    private final MeterRegistry meterRegistry;


    @GetMapping("/")
    ResponseEntity<List<WilayaDtoRecord>> getWilayas(@RequestParam(required = false,defaultValue = "DAIRA_ONLY") WilayaDetail detail){

        log.info("Getting All Wilaya with details {}", detail.name());

        Counter counter = Counter.builder("wilaya_all")
                .tag("title", "all_wilaya")
                .description("a number of requests to get all wilaya")
                .register(meterRegistry);
        counter.increment();

       return ResponseEntity.ok(wilayaService.getAllWilaya(detail));
    }

    @GetMapping(  {"/{wilayaId}","/{wilayaId}/"})
    ResponseEntity<WilayaDtoRecord> gerWilayaById(@PathVariable String wilayaId,@RequestParam(required = false,defaultValue =  "DAIRA_ONLY") @NotNull WilayaDetail detail){

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
                            .body(wilaya);
                }).orElseGet(() ->{
                    log.warn("There no content for wilaya {} with detail {}",wilayaId,detail);
                    return ResponseEntity.notFound().build();
                });
    }
}
