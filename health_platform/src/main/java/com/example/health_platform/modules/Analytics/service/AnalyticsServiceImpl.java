package com.example.health_platform.modules.Analytics.service;

import com.example.health_platform.auth.model.Role;
import com.example.health_platform.auth.repository.UserRepository;
import com.example.health_platform.modules.Analytics.DTO.PlatformStatsDTO;
import com.example.health_platform.modules.billing.model.BillStatus;
import com.example.health_platform.modules.billing.repository.BillRepository;
import com.example.health_platform.modules.appointment.repository.AppointmentRepository;
import com.example.health_platform.modules.visit.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final VisitRepository visitRepository;
    private final BillRepository billRepository;

    public AnalyticsServiceImpl(UserRepository userRepository,
                                AppointmentRepository appointmentRepository,
                                VisitRepository visitRepository,
                                BillRepository billRepository) {

        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.visitRepository = visitRepository;
        this.billRepository = billRepository;
    }

    @Override
    public PlatformStatsDTO getPlatformStats() {
        PlatformStatsDTO stats = new PlatformStatsDTO();

        stats.setTotalUsers(userRepository.count());
        stats.setTotalDoctors(userRepository.countByRole(Role.DOCTOR));
        stats.setTotalPatients(userRepository.countByRole(Role.PATIENT));
        stats.setTotalAppointments(appointmentRepository.count());
        stats.setTotalVisits(visitRepository.count());

        return stats;
    }

    @Override
    public Double getTotalRevenue() {
        return billRepository.findAll()
                .stream()
                .filter(b -> b.getStatus() == BillStatus.PAID)
                .mapToDouble(b -> b.getAmount())
                .sum();
    }

    @Override
    public Map<String, Long> getDailyVisits() {
        Map<String, Long> map = new LinkedHashMap<>();

        LocalDate today = LocalDate.now();

        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            long count = visitRepository.countByCreatedAtBetween(
                    date.atStartOfDay(),
                    date.plusDays(1).atStartOfDay()
            );
            map.put(date.toString(), count);
        }

        return map;
    }

    @Override
    public List<Map<String, Object>> getTopDoctors() {
        List<Object[]> raw = appointmentRepository.countAppointmentsPerDoctor();

        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : raw) {
            Map<String, Object> map = new HashMap<>();
            map.put("doctorId", row[0]);
            map.put("doctorName", row[1]);
            map.put("appointments", row[2]);
            result.add(map);
        }

        return result;
    }
}
