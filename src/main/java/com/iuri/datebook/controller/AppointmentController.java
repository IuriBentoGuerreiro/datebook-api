package com.iuri.datebook.controller;

import com.iuri.datebook.dto.AppointmentRequest;
import com.iuri.datebook.dto.AppointmentResponse;
import com.iuri.datebook.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<AppointmentResponse> listAll(){
        return appointmentService.listAll();
    }

    @PutMapping("/{id}")
    public AppointmentResponse update(@PathVariable Long id, @RequestBody AppointmentRequest appointmentRequest){
        return AppointmentResponse.convert(appointmentService.update(id, appointmentRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        appointmentService.delete(id);
    }

    @GetMapping("completed")
    public List<AppointmentResponse> getCompletedAppointments(){
        return appointmentService.getCompletedAppointments();
    }

    @GetMapping("pending")
    public List<AppointmentResponse> getPendingAppointmens(){
        return appointmentService.getPendingAppointments();
    }

    @PutMapping("/{id}/completed")
    public AppointmentResponse markAsCompleted(@PathVariable Long id){
        return appointmentService.markAsCompleted(id);
    }
}
