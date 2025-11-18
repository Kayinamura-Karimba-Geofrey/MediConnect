package com.example.health_platform.modules.pharmarcy.repository;




import com.example.health_platform.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.health_platform.modules.pharmarcy.model.PharmacyOrder;


import java.util.List;

public interface PharmacyOrderRepository extends JpaRepository<PharmacyOrder, Long> {

    List<PharmacyOrder> findByPatient(User patient);
}
