package com.yaksha.training.doctorappointment.controller;

import com.yaksha.training.doctorappointment.entity.Appointment;
import com.yaksha.training.doctorappointment.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
@RequestMapping(value = {"/appointment", "/"})
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = {"/list", "/"})
    public String listAppointments(Model theModel) {
        theModel.addAttribute("appointments", appointmentService.getAppointments());
        return "list-appointments";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        theModel.addAttribute("appointment", new Appointment());
        return "appointment-add";
    }

    @PostMapping("/saveAppointment")
    public String saveAppointment(@Valid @ModelAttribute("appointment") Appointment theAppointment, Model theModel) {
        appointmentService.saveAppointment(theAppointment);
        return "redirect:/appointment/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("appointmentId") Long appointmentId, Model theModel) {
        theModel.addAttribute("appointment", appointmentService.getAppointment(appointmentId));
        return "appointment-add";

    }

    @GetMapping("/showFormForDelete")
    public String showFormForDelete(@RequestParam("appointmentId") Long appointmentId, Model theModel) {
        appointmentService.deleteAppointment(appointmentService.getAppointment(appointmentId));
        return "redirect:/appointment/list";

    }
}
