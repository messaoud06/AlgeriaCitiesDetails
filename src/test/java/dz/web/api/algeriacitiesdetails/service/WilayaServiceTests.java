package dz.web.api.algeriacitiesdetails.service;

import dz.web.api.algeriacitiesdetails.entity.Daira;
import dz.web.api.algeriacitiesdetails.entity.Wilaya;
import dz.web.api.algeriacitiesdetails.enums.WilayaDetail;
import dz.web.api.algeriacitiesdetails.model.WilayaDtoRecord;
import dz.web.api.algeriacitiesdetails.repository.WilayaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

/**
 * @Author Messaoud GUERNOUTI on 3/14/2024
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WilayaServiceTests {

    @MockBean
    WilayaRepository wilayaRepository;

    @Autowired
    WilayaService wilayaService;

    private List<Wilaya> listWilaya= new ArrayList<>();

    private Wilaya wilaya;

    @BeforeEach
    public void setUp(){



        wilaya = new Wilaya(16L,"16","Alger","Alger",new HashSet<>());
        Wilaya wilaya1 = new Wilaya(1L,"01","Adrar","Adrar",new HashSet<>());

        listWilaya.add(wilaya);
        listWilaya.add(wilaya1);

    }

    @Test
    @DisplayName("Get All Wilaya")
    public void getAllWilaya(){

        Mockito.when(wilayaRepository.findAll()).thenReturn(listWilaya);
        assertEquals(wilayaService.getAllWilaya(WilayaDetail.WILAYA_ONLY,"id").size(),2,"List wilaya must be 2");

    }

    @Test
    @DisplayName("Get Success Wilaya By ID")
    public void getWilayaById(){

        Mockito.when(wilayaRepository.findWilayasByWilayaCode("16")).thenReturn(Optional.of(wilaya));
        Optional<WilayaDtoRecord> wilayaById = wilayaService.getWilayaById("16", WilayaDetail.WILAYA_ONLY);
        assertTrue(wilayaById.isPresent(),"Wilaya 16 must be present");
        assertEquals(wilayaById.get().WilayaNameFr(),"Alger","Must be Alger");
    }

    @Test
    @DisplayName("Get Not Found Wilaya By ID")
    public void getWilayaById_Failed(){
        Mockito.when(wilayaRepository.findWilayasByWilayaCode("16")).thenReturn(Optional.of(wilaya));
        Optional<WilayaDtoRecord> wilayaById = wilayaService.getWilayaById("01", WilayaDetail.WILAYA_ONLY);
        assertFalse(wilayaById.isPresent(),"Wilaya 16 must be not present");
    }


}
