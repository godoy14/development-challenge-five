package com.godoy.medcloudchallenge.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.godoy.medcloudchallenge.api.dto.PatientCreateDTO;
import com.godoy.medcloudchallenge.api.dto.PatientDTO;
import com.godoy.medcloudchallenge.domain.model.Patient;
import com.godoy.medcloudchallenge.domain.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping
	public List<PatientDTO> listAll() {
		
		return patientService.findAll();
		
	}

	@GetMapping("/{patientId}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Patient> getPatientById(@PathVariable Long patientId) {
		
		return patientService.findById(patientId);
		
	}

	@GetMapping("/findByEmail")
	@ResponseStatus(HttpStatus.OK)
	public PatientDTO getPatientByEmail(@RequestParam String email) {

		return patientService.findByEmail(email);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PatientDTO adicionar(@RequestBody @Valid PatientCreateDTO patientCreateDTO) {
		
		return patientService.salvar(patientCreateDTO);
		
	}

}
