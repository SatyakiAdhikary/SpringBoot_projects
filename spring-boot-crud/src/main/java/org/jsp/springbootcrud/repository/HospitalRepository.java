package org.jsp.springbootcrud.repository;

import java.util.List;

import org.jsp.springbootcrud.dto.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
      
	
//	@Query("select h from Hospital h where h.name=?1")
	public List<Hospital> findByName(String name);
	
	@Query("select a.hospitals from Admin a where a.id=?1")
	public List<Hospital> findByAdminId(int admin_id);
	
	@Query("select a.hospitals from Admin a where a.phone=?1 and a.password=?2")
	public List<Hospital> verify(long phone,String password);
	
}
