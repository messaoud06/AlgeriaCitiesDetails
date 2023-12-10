package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.model.DairaDto;
import dz.web.api.algeriacitiesdetails.service.DairaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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

import java.util.List;

/**
 * @Author Messaoud GUERNOUTI on 10/30/2023
 */
@Tag(
        name = "Daira details",
        description = "REST APIs in Algeria Cities to FETCH Daira details"
)
@RestController
@RequestMapping(value = "/wilaya/{wilayaId}/daira",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Log4j2
public class DairaController {

    private final DairaService dairaService;


    @Operation(
            summary = "Get All Daira details",
            description = "REST API to get all daira information"
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
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal Error !")
                    )
            )
    }
    )
    @GetMapping("/")
    ResponseEntity<List<DairaDto>> getAll(@PathVariable String wilayaId,
                                          @RequestParam(required = false,defaultValue = "ALL") @NotNull WilayaDetail detail){

        log.info("Getting All Daira for WilayaCode {} with detail {}",wilayaId, detail.name());
        return ResponseEntity.ok(dairaService.getAll(wilayaId,detail));
    }



    @Operation(
            summary = "Get Daira details by Code",
            description = "REST API to get daira information by their identifier"
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
    @GetMapping({"/{dairaId}","/{dairaId}/"})
    ResponseEntity<DairaDto> getDairaByName(@PathVariable String wilayaId,
                                         @PathVariable Long dairaId,
                                         @RequestParam(required = false,defaultValue = "ALL") @NotNull WilayaDetail detail){

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
