package com.yaksha.training.doctorappointment.controller;

import com.yaksha.training.doctorappointment.entity.Appointment;
import com.yaksha.training.doctorappointment.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;


@Controller
@RequestMapping(value = {"/appointment", "/"})
public class AppointmentController {

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = {"/list", "/"})
    public String listAppointments(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                                   @RequestParam(value = "theSearchDate", required = false) String theSearchDate,
                                   @PageableDefault(size = 5) Pageable pageable,
                                   Model theModel) throws ParseException {
        Page<Appointment> appointments = appointmentService
                .searchAppointments(theSearchName, theSearchDate, pageable);
        theModel.addAttribute("appointments", appointments.getContent());
        theModel.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        theModel.addAttribute("theSearchDate", theSearchDate != null ? theSearchDate : "");
        theModel.addAttribute("totalPage", appointments.getTotalPages());
        theModel.addAttribute("page", pageable.getPageNumber());
        theModel.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
        return "list-appointments";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        theModel.addAttribute("appointment", new Appointment());
        return "appointment-add";
    }

    @PostMapping("/saveAppointment")
    public String saveAppointment(@Valid @ModelAttribute("appointment") Appointment theAppointment, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            return "appointment-add";
        }
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

    @RequestMapping("/search")
    public String searchAppointment(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                                    @RequestParam(value = "theSearchDate", required = false) String theSearchDate,
                                    Model theModel,
                                    Pageable pageable) {
        Page<Appointment> appointments = appointmentService.searchAppointments(theSearchName, theSearchDate, pageable);
        theModel.addAttribute("appointments", appointments.getContent());
        theModel.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        theModel.addAttribute("theSearchDate", theSearchDate != null ? theSearchDate : "");
        theModel.addAttribute("totalPage", appointments.getTotalPages());
        theModel.addAttribute("page", pageable.getPageNumber());
        theModel.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
        return "list-appointments";
    }
}
