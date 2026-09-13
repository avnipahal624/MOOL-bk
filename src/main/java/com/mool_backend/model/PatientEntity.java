package com.mool_backend.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient_profile")
public class PatientEntity {

    @Id
    private Long id = 1L;

    private String name;
    private int age;
    private String preferredLanguage;

    @ElementCollection
    @CollectionTable(name = "patient_interests", joinColumns = @JoinColumn(name = "patient_id"))
    @Column(name = "interest")
    private List<String> interests = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String v) { this.preferredLanguage = v; }
    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }
}
