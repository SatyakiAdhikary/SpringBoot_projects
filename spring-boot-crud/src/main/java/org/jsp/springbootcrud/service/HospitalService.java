package org.jsp.springbootcrud.service;

import java.util.List;
import java.util.Optional;

import org.jsp.springbootcrud.dto.Admin;
import org.jsp.springbootcrud.dto.Hospital;
import org.jsp.springbootcrud.dto.ResponseStructure;
import org.jsp.springbootcrud.repository.AdminRepository;
import org.jsp.springbootcrud.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class HospitalService 
{
	@Autowired
	private HospitalRepository hospitalRepository;
	@Autowired
	private AdminRepository adminRepository;

	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(Hospital hospital,int admin_id)
	{
		ResponseStructure<Hospital> structure=new ResponseStructure<>();
		Optional<Admin> recAdmin=adminRepository.findById(admin_id);
		if(recAdmin.isPresent())
		{
			Admin dbAdmin=recAdmin.get();
			dbAdmin.getHospitals().add(hospital);
			hospital.setAdmin(dbAdmin);
			adminRepository.save(dbAdmin);
			structure.setData(hospitalRepository.save(hospital));
			structure.setMessage("Hospital added successfully");
			structure.setStatuscode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);


		}
		else
		{
			structure.setMessage("Cannot add Hospital as id is inavlid");
			structure.setData(null);
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
		}
	}


	public ResponseEntity<ResponseStructure<Hospital>> updateHospital(Hospital hospital,int admin_id)
	{
		Optional<Admin> recAdmin = adminRepository.findById(admin_id);
		ResponseStructure<Hospital> structure=new ResponseStructure<>();
		Optional<Hospital> recHospial=hospitalRepository.findById(hospital.getId());
		if(recHospial.isPresent())
		{
			Admin dbAdmin = recAdmin.get();
			Hospital dbHospital=recHospial.get();
			dbAdmin.getHospitals().add(hospital);
			hospital.setAdmin(dbAdmin);
			dbHospital.setFounder(hospital.getFounder());
			dbHospital.setGstNumber(hospital.getGstNumber());
			dbHospital.setName(hospital.getName());
			dbHospital.setYearOfEstablishment(hospital.getYearOfEstablishment());
			structure.setMessage("Hospital Updated");
			structure.setData(hospitalRepository.save(hospital));
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);


		}
		else
		{
			structure.setData(null);
			structure.setMessage("cannot Update Hospital as id is inavild");
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
		}
	}


	public ResponseEntity<ResponseStructure<Hospital>> findById(int id)
	{
		ResponseStructure<Hospital> structure=new ResponseStructure<>();
		Optional<Hospital> recHospital=hospitalRepository.findById(id);
		if(recHospital.isPresent())
		{
			structure.setData(recHospital.get());
			structure.setMessage("Hospital Found");
			structure.setStatuscode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		else
		{
			structure.setMessage("Hospital not found");
			structure.setData(null);
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);

		}
	}


	public ResponseEntity<ResponseStructure<List<Hospital>>> findByName(String name)
	{
		ResponseStructure<List<Hospital>> structure=new ResponseStructure<>();
		List<Hospital> hospitals=hospitalRepository.findByName(name);
		if(hospitals.isEmpty())
		{
			structure.setMessage("No Hospitals with entered name");
			structure.setData(null);
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
		}
		else
		{
			structure.setMessage("List of hospitals with enterde name");
			structure.setData(hospitals);
			structure.setStatuscode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
	}
	
	
	public ResponseEntity<ResponseStructure<List<Hospital>>> findbyAdminId(int admin_id)
	{
		ResponseStructure<List<Hospital>> structure=new ResponseStructure<>();
		List<Hospital> hospitals=hospitalRepository.findByAdminId(admin_id);
		if(hospitals.isEmpty())
		{
			structure.setMessage("No hospital found as id is invalid");
			structure.setData(null);
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
		}
		else
		{
			structure.setMessage("Admin found");
			structure.setData(hospitals);
			structure.setStatuscode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		
	}
	
	public ResponseEntity<ResponseStructure<List<Hospital>>> verify(long phone,String password)
	{
		ResponseStructure<List<Hospital>> structure=new ResponseStructure<>();
		List<Hospital> hospitals=hospitalRepository.verify(phone, password);
		if(hospitals.isEmpty())
		{
			structure.setMessage("No hospital found as admin number and password is invalid");
			structure.setData(null);
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(structure);
		}
		else
		{
			structure.setMessage("Admin found");
			structure.setData(hospitals);
			structure.setStatuscode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK.value()).body(structure);
		}
	}
	
	
	public ResponseEntity<ResponseStructure<String>> delete(int admin_id)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<Hospital> recHospital=hospitalRepository.findById(admin_id);
		
		if (recHospital.isPresent()) 
		{
			structure.setData("Hospital Found");
			structure.setMessage("Hospital Deleted");
			structure.setStatuscode(HttpStatus.OK.value());
			hospitalRepository.delete(recHospital.get());
			
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		structure.setData("Hospital Not Found");
		structure.setMessage("Cannot delete Hospital  as Admin Id is Invalid");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);

	}

	
	
	
	



}
