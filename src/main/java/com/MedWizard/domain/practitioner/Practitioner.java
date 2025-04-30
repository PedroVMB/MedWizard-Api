package com.MedWizard.domain.practitioner;

import com.MedWizard.domain.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "practitioners")
public class Practitioner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    private String licenseNumber;

    private String specialty;

    private String clinicName;

    @Deprecated
    public Practitioner() {}

    public Practitioner(User user, String licenseNumber, String specialty, String clinicName) {
        this.user = user;
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.clinicName = clinicName;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getClinicName() {
        return clinicName;
    }
}
