package com.godoy.medcloudchallenge.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godoy.medcloudchallenge.api.dto.PatientCreateDTO;
import com.godoy.medcloudchallenge.api.dto.PatientDTO;
import com.godoy.medcloudchallenge.domain.exception.ConflictException;
import com.godoy.medcloudchallenge.domain.exception.DealException;
import com.godoy.medcloudchallenge.domain.model.Patient;
import com.godoy.medcloudchallenge.domain.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	public List<PatientDTO> findAll(){
		List<Patient> list = patientRepository.findAll();
		return list.stream().map(x -> new PatientDTO(x)).collect(Collectors.toList());
	}
	
	public Optional<Patient> findById(Long patientId) {
		Optional<Patient> patient = patientRepository.findById(patientId);

		if (patient.isEmpty()) {
			throw new DealException(String.format("Patient with id %d not found", patientId));
		}
		return patient;
	}
	
	public PatientDTO findByEmail(String email) {
		Patient patient = patientRepository.findByEmail(email);

		if (patient != null) {
			PatientDTO patientDto = new PatientDTO(patient);
			return patientDto;
		}
		throw new DealException(String.format("There is no patient register with the email: %s.", email));
	}
	
	public PatientDTO salvar(PatientCreateDTO patientCreateDTO) {
		
		Patient existPatient = patientRepository.findByEmail(patientCreateDTO.getEmail());
		
		if (existPatient != null) {
			throw new ConflictException("There is already a patient registered with this email!");
		}
		
		Patient patientObj = new Patient();
		patientObj.setName(patientCreateDTO.getName());
		patientObj.setAddress(patientCreateDTO.getAddress());
		patientObj.setBirthDate(patientCreateDTO.getBirthDate());
		patientObj.setEmail(patientCreateDTO.getEmail());
		
		patientObj = patientRepository.save(patientObj);
		
		return new PatientDTO(patientObj);
	}

}
