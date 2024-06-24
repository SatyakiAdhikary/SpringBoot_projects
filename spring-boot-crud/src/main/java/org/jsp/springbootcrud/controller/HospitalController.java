package org.jsp.springbootcrud.controller;

import java.util.List;

import org.jsp.springbootcrud.dto.Hospital;
import org.jsp.springbootcrud.dto.ResponseStructure;
import org.jsp.springbootcrud.repository.HospitalRepository;
import org.jsp.springbootcrud.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController 
{
	@Autowired
	private HospitalService hospitalService;
	
	@PostMapping("/{admin_id}")
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(@RequestBody Hospital hospital,
			@PathVariable int admin_id)
	{
	 return hospitalService.saveHospital(hospital, admin_id);
	}
	
	@PutMapping("/{admin_id}")
	public ResponseEntity<ResponseStructure<Hospital>> updateHospital(@RequestBody Hospital hospital,
			@PathVariable(name="admin_id")int admin_id )
	{
		return hospitalService.updateHospital(hospital,admin_id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Hospital>> findById(@PathVariable(name="id")int id)
	{
		return hospitalService.findById(id);
	}
	  
	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Hospital>>> findByName(@PathVariable(name="name") String name)
	{
		return hospitalService.findByName(name);
	}
	
	@GetMapping("find-by-adminid/{admin}")
	public ResponseEntity<ResponseStructure<List<Hospital>>> findbyAdminId(@PathVariable(name="admin")int admin_id)
	{
		return hospitalService.findbyAdminId(admin_id);
	}
	
	@PostMapping("/verify")
	public ResponseEntity<ResponseStructure<List<Hospital>>> verify(@RequestParam(name="phone")long phone,@RequestParam(name="password")String password)
	{
		return hospitalService.verify(phone, password);
	}
	
	
	@DeleteMapping("/{admin_id}")
	public ResponseEntity<ResponseStructure<String>> deleteAdmin(@PathVariable(name = "admin_id") int admin_id) {
		return hospitalService.delete(admin_id);
	}

}
