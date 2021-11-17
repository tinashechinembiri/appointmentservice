package com.example.Websitepractice.appointment.service;

import com.example.Websitepractice.appointment.pogo.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<Appointment, String> {


}
