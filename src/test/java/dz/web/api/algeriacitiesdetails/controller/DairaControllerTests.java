package dz.web.api.algeriacitiesdetails.controller;

import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.model.DairaDto;
import dz.web.api.algeriacitiesdetails.service.DairaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static reactor.core.publisher.Mono.when;

/**
 * @Author Messaoud GUERNOUTI on 3/11/2024
 */

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class DairaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DairaService dairaService;



    @Test
    @DisplayName("Get All Daira")
    public void getallDaira() throws Exception {

        DairaDto dairaDto_first = new DairaDto(1,"First","",new ArrayList<>());
        DairaDto dairaDto_second = new DairaDto(2,"Second","",new ArrayList<>());

        //doReturn(List.of(dairaDto_first,dairaDto_second)).when(dairaService).getAll(any(),any());
        Mockito.when(dairaService.getAll("16", WilayaDetail.DAIRA_ONLY)).thenReturn(Arrays.asList(dairaDto_first,dairaDto_second));

        mockMvc.perform(get("/wilaya/{wilayaId}/dairas/",16)
                            .queryParam("detail",WilayaDetail.DAIRA_ONLY.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].dairaNameFr", is("First")));
    }


    @Test
    @DisplayName("Get Daira By Id")
    public void getDairaByCode() throws Exception {

        DairaDto dairaDto_first = new DairaDto(203,"Cheraga","",new ArrayList<>());

        doReturn(Optional.of(dairaDto_first)).when(dairaService).getDairaByName(any(),any(),any());

        //when(dairaService.getDairaByName(any(),any(),any())).thenReturn(Optional.of(dairaDto_first));

        mockMvc.perform(get("/wilaya/{wilayaId}/dairas/{dairaId}",16,203)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists());
    }


}
