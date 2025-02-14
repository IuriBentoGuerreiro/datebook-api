package com.iuri.datebook.controller;

import com.iuri.datebook.dto.AppointmentRequest;
import com.iuri.datebook.dto.AppointmentResponse;
import com.iuri.datebook.model.Appointment;
import com.iuri.datebook.model.User;
import com.iuri.datebook.service.AppointmentService;
import com.iuri.datebook.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @PostMapping
    public AppointmentResponse saveAppointment
            (@RequestBody AppointmentRequest appointmentRequest, Authentication authentication){
        return appointmentService.saveAppointment(appointmentRequest, authentication);
    }

    @GetMapping("/{id}")
    public AppointmentResponse getById(@PathVariable Long id){
        return AppointmentResponse.convert(appointmentService.getById(id));
    }

    @PutMapping("/{id}")
    public AppointmentResponse update(@PathVariable Long id, @RequestBody AppointmentRequest appointmentRequest){
        return AppointmentResponse.convert(appointmentService.update(id, appointmentRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        appointmentService.delete(id);
    }

    @PutMapping("/{id}/completed")
    public AppointmentResponse markAsCompleted(@PathVariable Long id){
        return appointmentService.markAsCompleted(id);
    }

    @GetMapping("/completed")
    public ResponseEntity<Page<Appointment>> getCompletedAppointments(Pageable pageable, Authentication authentication){
        String username = authentication.getName();
        Page<Appointment> listCompleted = appointmentService.getCompletedAppointments(username, pageable);
        return ResponseEntity.ok(listCompleted);
    }

    @GetMapping("/pending")
    public ResponseEntity<Page<Appointment>> getPendingAppointmens(Pageable pageable, Authentication authentication){
        String username = authentication.getName();
        Page<Appointment> listPending = appointmentService.getPendingAppointments(username, pageable);
        return ResponseEntity.ok(listPending);
    }

    @GetMapping
    public ResponseEntity<Page<Appointment>> getAppointmentsUser(Authentication authentication, Pageable pageable){
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        Page<Appointment> appointmentList = appointmentService.getAppointmentsUser(user, pageable);
        return ResponseEntity.ok(appointmentList);
    }
}
