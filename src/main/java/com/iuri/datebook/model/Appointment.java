package com.iuri.datebook.model;

import com.iuri.datebook.dto.AppointmentRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "appointment")
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public static Appointment convert(AppointmentRequest appointmentRequest){
        return Appointment.builder()
                .description(appointmentRequest.getDescription())
                .startDate(LocalDate.now())
                .endDate(appointmentRequest.getEndDate())
                .build();
    }
}
