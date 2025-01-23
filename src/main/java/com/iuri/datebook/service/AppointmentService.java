package com.iuri.datebook.service;

import com.iuri.datebook.dto.AppointmentRequest;
import com.iuri.datebook.dto.AppointmentResponse;
import com.iuri.datebook.model.Appointment;
import com.iuri.datebook.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public AppointmentResponse saveAppointment(AppointmentRequest appointmentRequest){
        Appointment appointment = new Appointment();
        appointment.setDescription(appointmentRequest.getDescription());
        appointment.setStartDate(LocalDate.now());
        appointment.setEndDate(appointmentRequest.getEndDate());
        appointment.setStatus(false);

        return AppointmentResponse.convert(appointmentRepository.save(appointment));
    }

    public Appointment getById(Long id){
        return appointmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found")
        );
    }

    public List<AppointmentResponse> listAll(){
        return appointmentRepository.findAll().stream()
                .map(AppointmentResponse::convert).toList();
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

    public List<AppointmentResponse> getCompletedAppointments(){
        return appointmentRepository.findByStatus(true).stream()
                .map(AppointmentResponse::convert).toList();
    }

    public AppointmentResponse markAsCompleted(Long id){
        var appointment = getById(id);
        appointment.setStatus(true);
        appointment.setEndDate(LocalDate.now());
        appointmentRepository.save(appointment);
        return AppointmentResponse.convert(appointment);
    }

    public List<AppointmentResponse> getPendingAppointments(){
        return appointmentRepository.findByStatus(false).stream()
                .map(AppointmentResponse::convert).toList();
    }
}
