package com.yaksha.training.doctorappointment.boundary;

import com.yaksha.training.doctorappointment.entity.Appointment;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static com.yaksha.training.doctorappointment.utils.MasterData.getAppointment;
import static com.yaksha.training.doctorappointment.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.doctorappointment.utils.TestUtils.boundaryTestFile;
import static com.yaksha.training.doctorappointment.utils.TestUtils.currentTest;
import static com.yaksha.training.doctorappointment.utils.TestUtils.testReport;
import static com.yaksha.training.doctorappointment.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testPatientNameNotBlank() throws Exception {
        Appointment appointment = getAppointment();
        appointment.setPatientName("");
        Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPatientNameNotNull() throws Exception {
        Appointment appointment = getAppointment();
        appointment.setPatientName(null);
        Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPatientNameMinTwo() throws Exception {
        Appointment appointment = getAppointment();
        appointment.setPatientName(randomStringWithSize(1));
        Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPatientNameMaxFourty() throws Exception {
        Appointment appointment = getAppointment();
        appointment.setPatientName(randomStringWithSize(41));
        Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testAppointmentDateNotNull() throws Exception {
        Appointment appointment = getAppointment();
        appointment.setAppointmentDate(null);
        Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testAppointmentTimeNotNull() throws Exception {
        Appointment appointment = getAppointment();
        appointment.setAppointmentTime(null);
        Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }
}
