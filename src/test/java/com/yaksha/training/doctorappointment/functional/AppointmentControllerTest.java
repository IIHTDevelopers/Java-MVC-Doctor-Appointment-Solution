package com.yaksha.training.doctorappointment.functional;

import com.yaksha.training.doctorappointment.controller.AppointmentController;
import com.yaksha.training.doctorappointment.entity.Appointment;
import com.yaksha.training.doctorappointment.service.AppointmentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.yaksha.training.doctorappointment.utils.MasterData.getAppointment;
import static com.yaksha.training.doctorappointment.utils.MasterData.getAppointmentList;
import static com.yaksha.training.doctorappointment.utils.TestUtils.businessTestFile;
import static com.yaksha.training.doctorappointment.utils.TestUtils.currentTest;
import static com.yaksha.training.doctorappointment.utils.TestUtils.testReport;
import static com.yaksha.training.doctorappointment.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

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
    public void testControllerListAppointmentsHome() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        when(appointmentService.getAppointments()).thenReturn(getAppointmentList(5));
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-appointments"), businessTestFile);
    }

    @Test
    public void testControllerListAppointments() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/list")).andReturn();
        when(appointmentService.getAppointments()).thenReturn(getAppointmentList(5));
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-appointments"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForAdd() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/showFormForAdd")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("appointment-add"), businessTestFile);
    }

    @Test
    public void testControllerSaveAppointment() throws Exception {
        Appointment appointment = getAppointment();
        MvcResult result = this.mockMvc.perform(post("/saveAppointment")
                .flashAttr("appointment", appointment)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/appointment/list"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForUpdate() throws Exception {
        Appointment appointment = getAppointment();
        when(appointmentService.getAppointment(appointment.getId())).thenReturn(appointment);
        MvcResult result = this.mockMvc.perform(get("/showFormForUpdate")
                .param("appointmentId", appointment.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("appointment-add"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForDeleteAppointment() throws Exception {
        Appointment appointment = getAppointment();
        MvcResult result = this.mockMvc.perform(get("/showFormForDelete")
                .param("appointmentId", appointment.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/appointment/list"), businessTestFile);
    }
}
