package com.epam.pmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.epam.pmt.entity.User;

@Repository
@CrossOrigin
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUserName(String userName);

}
