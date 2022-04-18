package com.godoy.medcloudchallenge.api.dto;

import java.time.LocalDate;

import com.godoy.medcloudchallenge.domain.model.Patient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {
	
	private Long id;
	private String name;
	private String email;
	private String address;
	private LocalDate birthDate;
	
	public PatientDTO() {
	}
	
	public PatientDTO(Long id, String name, String email, String address, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.birthDate = birthDate;
	}
	
	public PatientDTO(Patient patient) {
		super();
		this.id = patient.getId();
		this.name = patient.getName();
		this.email = patient.getEmail();
		this.address = patient.getAddress();
		this.birthDate = patient.getBirthDate();
	}

}
