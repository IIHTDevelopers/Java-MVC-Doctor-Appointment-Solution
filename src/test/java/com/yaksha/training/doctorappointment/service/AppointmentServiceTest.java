package com.yaksha.training.doctorappointment.service;

import com.yaksha.training.doctorappointment.entity.Appointment;
import com.yaksha.training.doctorappointment.repository.AppointmentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.yaksha.training.doctorappointment.utils.MasterData.asJsonString;
import static com.yaksha.training.doctorappointment.utils.MasterData.getAppointment;
import static com.yaksha.training.doctorappointment.utils.MasterData.getAppointmentList;
import static com.yaksha.training.doctorappointment.utils.TestUtils.businessTestFile;
import static com.yaksha.training.doctorappointment.utils.TestUtils.currentTest;
import static com.yaksha.training.doctorappointment.utils.TestUtils.testReport;
import static com.yaksha.training.doctorappointment.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testServiceGetAppointments() throws Exception {
        List<Appointment> actual = getAppointmentList(5);
        when(appointmentRepository.findAll()).thenReturn(actual);
        List<Appointment> expected = appointmentService.getAppointments();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSaveAppointment() throws Exception {
        Appointment actual = getAppointment();
        when(appointmentRepository.save(actual)).thenReturn(actual);
        Appointment expected = appointmentService.saveAppointment(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceGetAppointment() throws Exception {
        Appointment actual = getAppointment();
        when(appointmentRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        Appointment expected = appointmentService.getAppointment(actual.getId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }


    @Test
    public void testServiceDeleteAppointment() throws Exception {
        Appointment actual = getAppointment();
        boolean expected = appointmentService.deleteAppointment(actual);
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }


}
