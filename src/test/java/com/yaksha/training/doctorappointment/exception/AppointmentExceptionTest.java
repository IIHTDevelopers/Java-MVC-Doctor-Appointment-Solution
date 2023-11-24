package com.yaksha.training.doctorappointment.exception;

import com.yaksha.training.doctorappointment.controller.AppointmentController;
import com.yaksha.training.doctorappointment.entity.Appointment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.yaksha.training.doctorappointment.utils.MasterData.getAppointment;
import static com.yaksha.training.doctorappointment.utils.TestUtils.currentTest;
import static com.yaksha.training.doctorappointment.utils.TestUtils.exceptionTestFile;
import static com.yaksha.training.doctorappointment.utils.TestUtils.testReport;
import static com.yaksha.training.doctorappointment.utils.TestUtils.yakshaAssert;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AppointmentExceptionTest {

    @InjectMocks
    private AppointmentController appointmentController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionPatientNameNull() throws Exception {
        Appointment appointment = getAppointment();
        appointment.setPatientName(null);
        MvcResult result = this.mockMvc.perform(post("/saveAppointment")
                .flashAttr("appointment", appointment)).andReturn();
        yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
    }

    @Test
    public void testExceptionAppointmentDateNull() throws Exception {
        Appointment appointment = getAppointment();
        appointment.setAppointmentDate(null);
        MvcResult result = this.mockMvc.perform(post("/saveAppointment")
                .flashAttr("appointment", appointment)).andReturn();
        yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
    }

    @Test
    public void testExceptionAppointmentTimeNull() throws Exception {
        Appointment appointment = getAppointment();
        appointment.setAppointmentTime(null);
        MvcResult result = this.mockMvc.perform(post("/saveAppointment")
                .flashAttr("appointment", appointment)).andReturn();
        yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
    }

    @Test
    public void testExceptionPatientNameBlank() throws Exception {
        Appointment appointment = getAppointment();
        appointment.setPatientName("");
        MvcResult result = this.mockMvc.perform(post("/saveAppointment")
                .flashAttr("appointment", appointment)).andReturn();
        yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
    }

    @Test
    public void testExceptionAppointmentDateBlank() throws Exception {
        Appointment appointment = getAppointment();
        appointment.setAppointmentDate("");
        MvcResult result = this.mockMvc.perform(post("/saveAppointment")
                .flashAttr("appointment", appointment)).andReturn();
        yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
    }

    @Test
    public void testExceptionAppointmentTimeBlank() throws Exception {
        Appointment appointment = getAppointment();
        appointment.setAppointmentTime("");
        MvcResult result = this.mockMvc.perform(post("/saveAppointment")
                .flashAttr("appointment", appointment)).andReturn();
        yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
    }

}
