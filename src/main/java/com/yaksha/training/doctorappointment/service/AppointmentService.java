package com.yaksha.training.doctorappointment.service;

import com.yaksha.training.doctorappointment.entity.Appointment;
import com.yaksha.training.doctorappointment.repository.AppointmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public Page<Appointment> searchAppointments(String theSearchName, String theSearchDate, Pageable pageable) {
        if ((theSearchName != null && theSearchName.trim().length() > 0) ||
                (theSearchDate != null && theSearchDate.trim().length() > 0)) {
            theSearchName = theSearchName != null && theSearchName.isEmpty() ? null : theSearchName;
            Date searchDate = theSearchDate != null && theSearchDate.isEmpty() ? null : Date.valueOf(theSearchDate);
            return appointmentRepository.findByPatientNameAndAppointmentDate(theSearchName, searchDate, pageable);
        } else {
            return appointmentRepository.findAll(pageable);
        }
    }

}
