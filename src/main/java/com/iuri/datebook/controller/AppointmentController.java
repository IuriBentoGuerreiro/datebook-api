package com.iuri.datebook.controller;

import com.iuri.datebook.dto.AppointmentRequest;
import com.iuri.datebook.dto.AppointmentResponse;
import com.iuri.datebook.model.Appointment;
import com.iuri.datebook.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public AppointmentResponse saveAppointment(@RequestBody AppointmentRequest appointmentRequest){
        return appointmentService.saveAppointment(appointmentRequest);
    }

    @GetMapping("/{id}")
    public AppointmentResponse getById(@PathVariable Long id){
        return AppointmentResponse.convert(appointmentService.getById(id));
    }

    @GetMapping
    public Page<Appointment> getAllAppointments(Pageable pageable){
        return appointmentService.getAllAppointments(pageable);
    }

    @PutMapping("/{id}")
    public AppointmentResponse update(@PathVariable Long id, @RequestBody AppointmentRequest appointmentRequest){
        return AppointmentResponse.convert(appointmentService.update(id, appointmentRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        appointmentService.delete(id);
    }

    @GetMapping("/completed")
    public Page<Appointment> getCompletedAppointments(Pageable pageable){
        return appointmentService.getCompletedAppointments(pageable);
    }

    @GetMapping("/pending")
    public Page<Appointment> getPendingAppointmens(Pageable pageable){
        return appointmentService.getPendingAppointments(pageable);
    }

    @PutMapping("/{id}/completed")
    public AppointmentResponse markAsCompleted(@PathVariable Long id){
        return appointmentService.markAsCompleted(id);
    }
}
