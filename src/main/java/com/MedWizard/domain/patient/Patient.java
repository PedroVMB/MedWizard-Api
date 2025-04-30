package com.MedWizard.domain.patient;

import com.MedWizard.domain.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    private String healthInsurance;

    private String emergencyContact;

    @Deprecated
    public Patient() {}

    public Patient(User user, String healthInsurance, String emergencyContact) {
        this.user = user;
        this.healthInsurance = healthInsurance;
        this.emergencyContact = emergencyContact;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }
}
