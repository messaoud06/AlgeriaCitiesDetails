package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.entity.Wilaya;
import dz.web.api.algeriacitiesdetails.model.WilayaDtoRecord;
import dz.web.api.algeriacitiesdetails.service.WilayaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @Author Messaoud GUERNOUTI on 11/9/2023
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WilayaControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    WilayaService wilayaService;


    @Test
    @DisplayName("Get All Wilaya Success")
    void testAllWilayaSuccess() throws Exception {

        WilayaDtoRecord wilayaDto = new WilayaDtoRecord("01","Test","",null);
        WilayaDtoRecord wilayaDto1 = new WilayaDtoRecord("02","Test","",null);
        doReturn(List.of(wilayaDto,wilayaDto1)).when(wilayaService).getAllWilaya(any(), any());


        mockMvc.perform(get("/wilayas/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .queryParam("detail","DAIRA_ONLY")
                       )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


    }


    @Test
    @DisplayName("Get WilayaByCode Success")
    void testWilayaByCodeSuccess() throws Exception {

        WilayaDtoRecord wilayaResult = new WilayaDtoRecord("16","Alger","الجزائر",new ArrayList<>());

        doReturn(Optional.of(wilayaResult)).when(wilayaService).getWilayaById(any(),any());

        mockMvc.perform(get("/wilaya/{wilayaId}/",16)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .queryParam("detail","DAIRA_ONLY")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.wilayaCode",is("16")));
    }

    @Test
    @DisplayName("Get WilayaByCode Not Found")
    void testWilayaByCodeNotFound() throws Exception {

        doReturn(Optional.empty()).when(wilayaService).getWilayaById(any(),any());

        mockMvc.perform(get("/wilaya/{wilayaId}/",16)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .queryParam("detail","DAIRA_ONLY")
                )
                .andExpect(status().isNotFound());
    }

}
