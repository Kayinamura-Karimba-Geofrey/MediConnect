package com.example.health_platform.modules.insurance.model;



import com.example.health_platform.auth.model.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "insurance")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String providerName;
    private String insuranceNumber;
    private String coverageDetails;

    @Builder.Default
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

