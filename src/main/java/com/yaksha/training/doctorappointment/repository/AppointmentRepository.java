package com.yaksha.training.doctorappointment.repository;

import com.yaksha.training.doctorappointment.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "Select a from Appointment a "
            + "where (:keyword IS NULL OR lower(a.patientName) like %:keyword%) "
            + "AND (:apptDate IS NULL OR a.appointmentDate = :apptDate) ")
    Page<Appointment> findByPatientNameAndAppointmentDate(@Param("keyword") String keyword,
                                                          @Param("apptDate") Date apptDate,
                                                          Pageable pageable);

    Page<Appointment> findAll(Pageable pageable);
}
