package com.example.health_platform.modules.notification.repository;



import com.example.health_platform.modules.notification.model.Notification;
import com.example.health_platform.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserOrderByTimeStampDesc(User user);
}
