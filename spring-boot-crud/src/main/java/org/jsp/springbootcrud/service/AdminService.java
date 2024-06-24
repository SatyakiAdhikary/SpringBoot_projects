package org.jsp.springbootcrud.service;

import java.sql.Struct;
import java.util.List;
import java.util.Optional;

import org.jsp.springbootcrud.dto.Admin;
import org.jsp.springbootcrud.dto.ResponseStructure;
import org.jsp.springbootcrud.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService 
{
	@Autowired
	private AdminRepository adminRepository;
	
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin)
	{
		ResponseStructure<Admin> structure=new ResponseStructure<>();
		structure.setMessage("Admin saved successfully");
		structure.setData(adminRepository.save(admin));
		structure.setStatuscode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
			}
	
	public ResponseEntity<ResponseStructure<Admin>> findById(int id)
	{
		ResponseStructure<Admin> structure=new ResponseStructure<>();
		Optional<Admin> recAdmin=adminRepository.findById(id);
		if(recAdmin.isPresent())
		{
			structure.setData(recAdmin.get());
			structure.setMessage("Admin found");
			structure.setStatuscode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
			
		}
		else 
		{
			structure.setMessage("admin not found");
			structure.setData(null);
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
		}
	}
	
	
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin) {
		Optional<Admin> recAdmin=adminRepository.findById(admin.getId());
		ResponseStructure<Admin> structure=new ResponseStructure<>();
		if(recAdmin.isPresent()) {
			Admin dbadmin=recAdmin.get();
			dbadmin.setEmail(admin.getEmail());
			dbadmin.setName(admin.getName());
			dbadmin.setPassword(admin.getPassword());
			dbadmin.setPhone(admin.getPhone());
			structure.setMessage("Admin updated");
			structure.setData(adminRepository.save(admin));
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
					
		}
		structure.setData(null);
		structure.setMessage("cannot update Admin as Id is invalid");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<List<Admin>>> findByName(String name) {
		ResponseStructure<List<Admin>> structure = new ResponseStructure<>();
		List<Admin> admins = adminRepository.findByName(name);
		structure.setData(admins);
		if (admins.isEmpty()) {
			structure.setMessage("No Admins with entered name");
		}
		structure.setMessage("List of Admins with entered name");
		structure.setStatuscode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<Admin>> verify(long phone,String password)
	{
		ResponseStructure<Admin> structure=new ResponseStructure<>();
		Optional<Admin> recAdmin=adminRepository.findByPhoneAndPassword(phone, password);
		if(recAdmin.isPresent())
		{
			structure.setData(recAdmin.get());
			structure.setMessage("Admin found");
			structure.setStatuscode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
			
		}
		else 
		{
			structure.setMessage("admin not found");
			structure.setData(null);
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
		}
	}
	
	public ResponseEntity<ResponseStructure<String>> delete(int id)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<Admin> recAdmins=adminRepository.findById(id);
		
		if (recAdmins.isPresent()) 
		{
			structure.setData("Admin Found");
			structure.setMessage("Admin Deleted");
			structure.setStatuscode(HttpStatus.OK.value());
			adminRepository.delete(recAdmins.get());
			
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		structure.setData("Admin Not Found");
		structure.setMessage("Cannot delete Admin as Id is Invalid");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);

	}

}
