package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.entity.Commune;
import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.model.CommuneDto;
import dz.web.api.algeriacitiesdetails.service.CommuneService;
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
        name = "Commune details",
        description = "REST APIs in Algeria Cities to FETCH Commune details"
)
@RestController
@RequestMapping(value = "/wilaya/{wilayaId}/daira/{dairaId}/communes",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Log4j2
public class CommuneController {

    private final CommuneService communeService;

    @Operation(
            summary = "Get All Commune details",
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
    ResponseEntity<List<CommuneDto>> getAllCommuneByDairaId(@PathVariable String wilayaId,
                                                            @PathVariable Long dairaId,
                                                            @RequestParam(required = false,defaultValue = "ALL") @NotNull WilayaDetail detail){

        log.info("Getting All Commune for Daira {} for WilayaCode {} with detail {}",dairaId,wilayaId, detail.name());
        return ResponseEntity.ok(communeService.getAllByDairaId(dairaId,wilayaId,detail));

    }



    @Operation(
            summary = "Get Commune details by Code",
            description = "REST API to get commune information by their identifier"
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
    @GetMapping({"/{communeId}","/{communeId}/"})
    ResponseEntity<CommuneDto> getCommuneById(@PathVariable String wilayaId,
                                     @PathVariable Long dairaId,
                                     @PathVariable Long communeId){


        return communeService.getCommuneById(communeId,dairaId,wilayaId)
                .map(communeDto -> {
                    log.info("Getting Commune {}  Daira {} for WilayaCode {}",communeId,dairaId, wilayaId);
                    return ResponseEntity.ok(communeDto);
                }).orElseGet(() ->{
                    log.warn("There no content for commune {} Daira {}  wilaya {}",communeId,dairaId,wilayaId);
                    return ResponseEntity.notFound().build();
                });

    }
}
