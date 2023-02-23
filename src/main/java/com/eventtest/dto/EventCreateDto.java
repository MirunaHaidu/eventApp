package com.eventtest.dto;

import com.eventtest.model.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventCreateDto {
    @NotBlank
    private String title;
    @NotNull
    private LocalDate date;
    @NotBlank
    @Length(min = 20, message = "Please insert minimum 20 characters")
    private String description;
    @NotBlank
    private User createdBy;


}
