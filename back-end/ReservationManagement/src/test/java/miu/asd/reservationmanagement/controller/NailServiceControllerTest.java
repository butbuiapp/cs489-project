package miu.asd.reservationmanagement.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import miu.asd.reservationmanagement.common.Constant;
import miu.asd.reservationmanagement.common.ServiceStatusEnum;
import miu.asd.reservationmanagement.config.JwtFilter;
import miu.asd.reservationmanagement.config.JwtFilterMockConfig;
import miu.asd.reservationmanagement.model.NailService;
import miu.asd.reservationmanagement.service.NailServiceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

@WebMvcTest(value = NailServiceController.class)
class NailServiceControllerTest {
    @MockBean
    private JwtFilter jwtFilter;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NailServiceService nailServiceService;


    @BeforeEach
    void setUp() throws ServletException, IOException {

    }

    @AfterEach
    void tearDown() {
    }

//    @Test
    void createService() throws Exception {
        // input
        NailService input = NailService.builder()
                .name("Manicure")
                .price(20d)
                .duration(30)
                .description("Nail treatment")
                .build();

        // output
        NailService expected = NailService.builder()
                .id(1)
                .name("Manicure")
                .price(20d)
                .duration(30)
                .description("Nail treatment")
                .status(ServiceStatusEnum.ACTIVE)
                .build();
        // mock
        Mockito.when(nailServiceService.saveService(input)).thenReturn(expected);

        mockMvc.perform(
                MockMvcRequestBuilders.post(Constant.SERVICE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(input)))
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(new Gson().toJson(expected)));

    }

    @Test
    void updateService() {
    }

    @Test
    void getAllServices() {
    }

    @Test
    void getServiceById() {
    }

    @Test
    void deleteService() {
    }
}