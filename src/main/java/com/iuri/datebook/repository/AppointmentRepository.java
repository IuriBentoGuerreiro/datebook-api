package com.iuri.datebook.repository;

import com.iuri.datebook.model.Appointment;
import com.iuri.datebook.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Page<Appointment> findByStatusAndUserUsername(boolean status, Pageable pageable, String username);

    Page<Appointment> findByUser(User user, Pageable pageable);
}
