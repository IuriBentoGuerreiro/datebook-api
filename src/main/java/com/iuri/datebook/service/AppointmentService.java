package com.iuri.datebook.service;

import com.iuri.datebook.dto.AppointmentRequest;
import com.iuri.datebook.dto.AppointmentResponse;
import com.iuri.datebook.model.Appointment;
import com.iuri.datebook.model.User;
import com.iuri.datebook.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserService userService;

    public AppointmentResponse saveAppointment(AppointmentRequest appointmentRequest, Authentication authentication){
        Appointment appointment = new Appointment();
        appointment.setDescription(appointmentRequest.getDescription());
        appointment.setStartDate(LocalDate.now());
        appointment.setEndDate(appointmentRequest.getEndDate());
        appointment.setStatus(false);
        appointment.setUser(userService.findByUsername(authentication.getName()));

        return AppointmentResponse.convert(appointmentRepository.save(appointment));
    }

    public Appointment getById(Long id){
        return appointmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found")
        );
    }

    public Page<Appointment> getAllAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    public Appointment update(Long id, AppointmentRequest appointmentRequest){
        var appointmentSave = getById(id);
        BeanUtils.copyProperties(appointmentRequest, appointmentSave, "id");
        return appointmentRepository.save(appointmentSave);
    }

    public void delete(Long id){
        var appointment = getById(id);
        appointmentRepository.delete(appointment);
    }

    public AppointmentResponse markAsCompleted(Long id){
        var appointment = getById(id);
        appointment.setStatus(true);
        appointment.setEndDate(LocalDate.now());
        appointmentRepository.save(appointment);
        return AppointmentResponse.convert(appointment);
    }

    public Page<Appointment> getCompletedAppointments(String username, Pageable pageable){
        return appointmentRepository.findByStatusAndUserUsername(true, pageable, username);
    }

    public Page<Appointment> getPendingAppointments(String username, Pageable pageable){
        return appointmentRepository.findByStatusAndUserUsername(false, pageable, username);
    }

    public Page<Appointment> getAppointmentsUser (User user, Pageable pageable){
        return appointmentRepository.findByUser(user, pageable);
    }
}
