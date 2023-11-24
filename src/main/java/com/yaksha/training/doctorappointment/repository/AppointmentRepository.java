package com.yaksha.training.doctorappointment.repository;

import com.yaksha.training.doctorappointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
