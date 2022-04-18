package com.godoy.medcloudchallenge.api.dto;

import java.time.LocalDate;

import com.godoy.medcloudchallenge.domain.model.Patient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientCreateDTO {
	
	private String name;
	private String email;
	private String address;
	private LocalDate birthDate;
	
	public PatientCreateDTO() {
	}
	
	public PatientCreateDTO(String name, String email, String address, LocalDate birthDate) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.birthDate = birthDate;
	}
	
	public PatientCreateDTO(Patient patient) {
		super();
		this.name = patient.getName();
		this.email = patient.getEmail();
		this.address = patient.getAddress();
		this.birthDate = patient.getBirthDate();
	}

}
