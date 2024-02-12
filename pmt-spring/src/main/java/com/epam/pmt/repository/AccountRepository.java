package com.epam.pmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.epam.pmt.entity.Account;
@Repository
@CrossOrigin
public interface AccountRepository extends JpaRepository<Account, Integer>{

}
