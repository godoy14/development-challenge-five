package com.godoy.medcloudchallenge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.godoy.medcloudchallenge.domain.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findByEmail(String email);
	
}
