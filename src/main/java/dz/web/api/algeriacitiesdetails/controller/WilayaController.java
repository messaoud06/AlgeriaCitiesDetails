package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.model.WilayaDto;
import dz.web.api.algeriacitiesdetails.service.WilayaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Messaoud GUERNOUTI on 10/26/2023
 */
@RestController
@RequestMapping("/wilaya")
@RequiredArgsConstructor
public class WilayaController {

    private final WilayaService wilayaService;



    @GetMapping("/")
    ResponseEntity<?> getWilayas(@RequestParam(required = false,defaultValue = "DAIRA_ONLY") WilayaDetail detail){


      //  Pageable paging = PageRequest.of(pageNbr, pageSize, Sort.by("id"));
       // return ResponseEntity.ok(wilayaService.getAllWilaya1(details,paging));

       return ResponseEntity.ok(wilayaService.getAllWilaya(detail));
    }

    @GetMapping(  {"/{wilayaId}","/{wilayaId}/"})
    ResponseEntity<?> gerWilayaById(@PathVariable String wilayaId,@RequestParam(required = false,defaultValue =  "DAIRA_ONLY") WilayaDetail detail){

        return wilayaService.getWilayaById(wilayaId,detail)
                .map(wilaya -> {
                    return ResponseEntity
                            .ok()
                            .body(WilayaDto.build(wilaya));
                }).orElse(ResponseEntity.notFound().build());
    }
}
