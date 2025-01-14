package com.iuri.datebook.dto;

import com.iuri.datebook.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

    private Long id;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public static AppointmentResponse convert(Appointment appointment){
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .description(appointment.getDescription())
                .startDate(appointment.getStartDate())
                .endDate(appointment.getEndDate())
                .build();
    }
}
