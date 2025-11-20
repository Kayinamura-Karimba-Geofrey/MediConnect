package com.example.health_platform.modules.billing.repository;

import com.example.health_platform.modules.billing.model.Bill;
import com.example.health_platform.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByPatient(User patient);
}
