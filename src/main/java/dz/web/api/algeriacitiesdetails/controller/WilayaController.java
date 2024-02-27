package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.helper.Utils;
import dz.web.api.algeriacitiesdetails.model.WilayaDtoRecord;
import dz.web.api.algeriacitiesdetails.service.PrayerTimesService;
import dz.web.api.algeriacitiesdetails.service.WilayaService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author Messaoud GUERNOUTI on 10/26/2023
 */

@Tag(
        name = "Wilaya details",
        description = "REST APIs in Algeria Cities to FETCH Wilaya details"
)
@RestController
@RequestMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Log4j2
public class WilayaController {

    private final WilayaService wilayaService;
    private final MeterRegistry meterRegistry;

    private final PrayerTimesService prayerTimesService;



    @Operation(
            summary = "Get All Wilaya details",
            description = "REST API to get all wilaya information"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            )
    }
    )
    @GetMapping("/wilayas")
    ResponseEntity<List<WilayaDtoRecord>> getWilayas(@RequestParam(required = false,defaultValue = "WILAYA_ONLY") WilayaDetail detail,
                                                     @RequestParam(required = false,defaultValue = "id") String sort_by){

        log.info("Getting All Wilaya with details {}", detail.name());

        Counter counter = Counter.builder("wilaya_all")
                .tag("title", "all_wilaya")
                .description("a number of requests to get all wilaya")
                .register(meterRegistry);
        counter.increment();

       return ResponseEntity.ok(wilayaService.getAllWilaya(detail,sort_by));
    }


    @Operation(
            summary = "Get Wilaya details by Code",
            description = "REST API to get wilaya information by their identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            )
    }
    )
    @GetMapping(  {"/wilaya/{wilayaId}","/wilaya/{wilayaId}/"})
    ResponseEntity<WilayaDtoRecord> gerWilayaById(@PathVariable String wilayaId,
                                                  @RequestParam(required = false,defaultValue =  "DAIRA_ONLY") @NotNull WilayaDetail detail,
                                                  @RequestParam(required = false) String date){

        //date = "22-12-2024";
        if(date==null){
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            date = today.format(formatter);
        }

        if(!Utils.isValidDate("dd-MM-yyyy",date)){
            return ResponseEntity.badRequest().build();
        }

        Counter counter = Counter.builder("wilaya_byId")
                .tag("title", "wiliya_byId")
                .description("a number of requests to wilaya byId")
                .register(meterRegistry);
        counter.increment();


        String finalDate = date;
        return wilayaService.getWilayaById(wilayaId,detail)
                .map(wilaya -> {
                    log.info("Getting Wilaya {} with detail {}", wilayaId,detail);
                    System.out.println(prayerTimesService.getPrayer(wilaya.WilayaNameFr(), finalDate).fajr());
                    return ResponseEntity
                            .ok()
                            .body(wilaya);
                }).orElseGet(() ->{
                    log.warn("There no content for wilaya {} with detail {}",wilayaId,detail);
                    return ResponseEntity.notFound().build();
                });
    }
}
