package com.epam.pmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.epam.pmt.entity.Group;

@Repository
@CrossOrigin
public interface GroupRepository extends JpaRepository<Group, Integer> {
	
	public Optional<Group> findByGroupName(String groupName);

}
