package com.example.Websitepractice.Service;

import com.example.Websitepractice.appointment.pogo.Address;
import com.example.Websitepractice.appointment.pogo.Appointment;
import com.example.Websitepractice.appointment.pogo.MovieDetails;
import com.example.Websitepractice.appointment.pogo.Seats;
import com.example.Websitepractice.appointment.service.AppointmentServiceimpl;
import com.example.Websitepractice.appointment.service.AppointmentServiceres;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentServiceimplTest {
    private  static  final  String appointmentid = "74398";

    @InjectMocks
    private AppointmentServiceimpl appointmentServiceres;
    @Mock
    private MongoTemplate mongoTemplate;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.LENIENT);


    @Test
    public void test_create()
    {

        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setMovieid("19933");
        List<Seats> seat = new ArrayList<Seats>();
        Seats seats = new Seats();
        seats.setSeatid("b1");
        seat.add(seats);
        Appointment newappointment = new Appointment();
        newappointment.setId("12345");
        newappointment.setMoviedetails(movieDetails);
        newappointment.setAppointmentDate("2025-06-12T12:17:21");
        newappointment.setSeats(seat);
        Address address = new Address();
        address.setPostcode("nn2 675");
        newappointment.setAddress(address);
       // when(appointmentServiceres.)
        when(mongoTemplate.save(newappointment)).thenReturn(newappointment);
        String created = appointmentServiceres.createappointment(newappointment);
        assertEquals(created, "12345");

    }
    @Test
    public void testgetuserbyid()
    {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(appointmentid);
        when(mongoTemplate.findOne(new Query().addCriteria(Criteria.where("appointmentId").is(appointmentid)), Appointment.class)).thenReturn(appointment);
        Appointment user = appointmentServiceres.getuserbyappid(appointmentid);
        assertEquals(user.getAppointmentId(), appointmentid);
        assertNotNull(user);
    }
}
