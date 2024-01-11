package com.yaksha.training.doctorappointment.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yaksha.training.doctorappointment.entity.Appointment;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterData {

    public static Appointment getAppointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setPatientName(randomStringWithSize(10));
        appointment.setAppointmentDate(Date.valueOf(LocalDate.now().toString()));
        appointment.setAppointmentTime(Time.valueOf("11:00:00"));
        return appointment;
    }

    public static List<Appointment> getAppointmentList(int size) {
        Long id = 0L;
        List<Appointment> appointments = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Appointment appointment = new Appointment();
            appointment.setId(1L);
            appointment.setPatientName(randomStringWithSize(10));
            appointment.setAppointmentDate(Date.valueOf(LocalDate.now().toString()));
            appointment.setAppointmentTime(Time.valueOf("11:00:00"));
            appointments.add(appointment);
        }
        return appointments;
    }


    private static Random rnd = new Random();

    public static String randomStringWithSize(int size) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + (String.valueOf(alphabet.charAt(rnd.nextInt(alphabet.length()))));
        }
        return s;
    }

    public static boolean randomBoolean() {
        String alphabet = "1234567890";
        Random rnd = new Random();
        return rnd.nextInt(alphabet.length()) % 2 == 0;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
