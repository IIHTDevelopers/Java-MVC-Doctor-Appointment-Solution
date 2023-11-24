package com.yaksha.training.doctorappointment.service;

import com.yaksha.training.doctorappointment.entity.Appointment;
import com.yaksha.training.doctorappointment.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment saveAppointment(Appointment theAppointment) {
        return appointmentRepository.save(theAppointment);
    }

    public Appointment getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).get();
    }

    public boolean deleteAppointment(Appointment appointment) {
        appointmentRepository.delete(appointment);
        return true;
    }
}
