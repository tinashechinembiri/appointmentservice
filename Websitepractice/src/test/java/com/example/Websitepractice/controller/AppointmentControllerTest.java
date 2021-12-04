package com.example.Websitepractice.controller;

import com.example.Websitepractice.appointment.Exceptions.AppointmentCustomError;
import com.example.Websitepractice.appointment.Exceptions.AppointmentExceptions;
import com.example.Websitepractice.appointment.controller.AppoitmentController;
import com.example.Websitepractice.appointment.pogo.Appointment;
import com.example.Websitepractice.appointment.service.AppointmentServiceimpl;
import com.example.Websitepractice.appointment.service.AppointmentServiceres;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(AppoitmentController.class)
public class AppointmentControllerTest {
    private  static  final  String appointmentid = "74398";
    LocalDateTime today;
    @Mock
    private AppointmentServiceres appointmentServiceres;
    private static ObjectMapper mapper = new ObjectMapper();
    @InjectMocks
    private AppoitmentController appoitmentController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MongoTemplate mongoTemplate;


    @Before
    public void  setUp()
    {

        //appointmentServiceres = new AppointmentServiceimpl(mongoTemplate);
        appoitmentController  = new AppoitmentController( appointmentServiceres);
        today =  LocalDateTime.now().plusDays(1);
       // mockMvc = MockMvcBuilders.standaloneSetup(appoitmentController).build();
    }

    @Test
    public void testgetappointment()
    {
        // given
        String result = appointmentid;
        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentId(appointmentid);
        // when
        when(appointmentServiceres.getuserbyappid(appointmentid)).thenReturn(appointment1);
        ResponseEntity<Object> appointment = appoitmentController.getsingleappointment(appointmentid);
        //then
       // assertEquals(result, appointment);
        assertNotNull(appointment);

    }
    @Test
    public void testcreateappointment() throws Exception {
        //given
        String json = "{\"cinemastoreid\":\"786\",\"appointmentId\":\"74098\",\"seatid\":\"B1\",\"movieId\":\"M745\",\"quantity\":1,\"duration\":2,\"starttime\":\"12:00\",\"moviedetails\":{},\"orderref\":\"\",\"customerid\":\"89741\",\"address\":{\"streetName\":\"North\",\"town\":\"Greater London\",\"postcode\":\"UB7 9JB\",\"country\":\"England\",\"mobileNo\":\"01632960188\",\"phoneNo\":\"01632960196\"},\"cinemaAddress\":{\"streetName\":\"North\",\"town\":\"Greater London\",\"postCode\":\"UB7 9JB\",\"country\":\"England\",\"mobile\":\"01632960188\",\"phone\":\"01632960196\",\"county\":\"London\"},\"seats\":[{\"seatid\":\"B1\",\"seatNumber\":\"B12\"}],\"appointmentDate\":\"2025-06-12T12:17:21\"}";
        Appointment appointment =  appointmentSet(json);

        //when
        when(appointmentServiceres.getuserbyappid(appointment.getAppointmentId()))
                .thenReturn(null);
        when((appointmentServiceres).createappointment(appointment)).thenReturn(appointmentid);

        MockHttpServletResponse response = mockMvc
                        .perform(MockMvcRequestBuilders.post("/api/addappointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(appointment))
                        .accept(MediaType.APPLICATION_JSON ))
                        .andReturn().getResponse();
        //then

        assertNotNull(response.getContentAsString(), notNullValue());
        assertTrue(response.getContentAsString().contains("Appointment created"));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
    //https://www.baeldung.com/spring-mvc-test-exceptions
    //https://www.baeldung.com/mockito-behavior
    @Test
    public void testcreateappointment_fail() throws Exception {
        String json = "{\"cinemastoreid\":\"786\",\"appointmentId\":\"74098\",\"seatid\":\"B1\",\"movieId\":\"M745\",\"quantity\":1,\"duration\":2,\"starttime\":\"12:00\",\"moviedetails\":{},\"orderref\":\"\",\"customerid\":\"89741\",\"address\":{\"streetName\":\"North\",\"town\":\"Greater London\",\"postcode\":\"UB7 9JB\",\"country\":\"England\",\"mobileNo\":\"01632960188\",\"phoneNo\":\"01632960196\"},\"cinemaAddress\":{\"streetName\":\"North\",\"town\":\"Greater London\",\"postCode\":\"UB7 9JB\",\"country\":\"England\",\"mobile\":\"01632960188\",\"phone\":\"01632960196\",\"county\":\"London\"},\"seats\":[{\"seatid\":\"B1\",\"seatNumber\":\"B12\"}],\"appointmentDate\":\"2025-06-12T12:17:21\"}";
        Appointment appointment =  appointmentSet(json);
        when( appointmentServiceres.getuserbyappid(anyString()))
                .thenReturn(appointment);
        AppointmentExceptions thrown =
                assertThrows(AppointmentExceptions.class,
                        ()-> appoitmentController
                                .addappointment(json));
        assertTrue(thrown
                .getMessage()
                .contains("Appointment already exist"));

/*

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/addappointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(appointment))
                .accept(MediaType.APPLICATION_JSON ))
                .andExpect(status().isBadRequest());
                */

    }


    @Test
    public  void testUpdate() throws Exception {
        String json = "{\"cinemastoreid\":\"786\",\"appointmentId\":\"74098\",\"seatid\":\"B1\",\"movieId\":\"M745\",\"quantity\":1,\"duration\":2,\"starttime\":\"12:00\",\"moviedetails\":{},\"orderref\":\"\",\"customerid\":\"89741\",\"address\":{\"streetName\":\"North\",\"town\":\"Greater London\",\"postcode\":\"UB7 9JB\",\"country\":\"England\",\"mobileNo\":\"01632960188\",\"phoneNo\":\"01632960196\"},\"cinemaAddress\":{\"streetName\":\"North\",\"town\":\"Greater London\",\"postCode\":\"UB7 9JB\",\"country\":\"England\",\"mobile\":\"01632960188\",\"phone\":\"01632960196\",\"county\":\"London\"},\"seats\":[{\"seatid\":\"B1\",\"seatNumber\":\"B12\"}],\"appointmentDate\":\"2025-06-12T12:17:21\"}";
        Appointment appointment =  appointmentSet(json);
        appointment.setAppointmentDate("2025-06-12T12:17:21");
        when(appointmentServiceres.getuserbyappid(appointment.getAppointmentId())).thenReturn(appointment);

        when(appointmentServiceres.updateappointment(appointment)).thenReturn(appointment);
        ResponseEntity<Object> result = appoitmentController.updateappointment(json);
        assertThat(result.getStatusCode().is2xxSuccessful());
        assertTrue(result.getBody().toString().contains("Appointment Updated"));
    }
    private Appointment appointmentSet(String appointment)  {
        Appointment app;
        Gson gson = new Gson();
        app  = gson.fromJson(appointment, Appointment.class);
        return app;
    }

}
