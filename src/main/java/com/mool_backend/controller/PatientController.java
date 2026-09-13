package com.mool_backend.controller;

import com.mool_backend.dto.PatientDto;
import com.mool_backend.model.PatientEntity;
import com.mool_backend.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientRepository repo;

    public PatientController(PatientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public PatientDto get() {
        PatientEntity e = repo.findById(1L).orElseGet(() -> {
            PatientEntity fresh = new PatientEntity();
            fresh.setId(1L);
            fresh.setName("Mitali");
            fresh.setAge(72);
            fresh.setPreferredLanguage("as");
            fresh.setInterests(List.of("gardening", "music", "cooking", "family"));
            return repo.save(fresh);
        });
        return toDto(e);
    }

    @PutMapping
    public PatientDto update(@RequestBody PatientDto dto) {
        PatientEntity e = repo.findById(1L).orElse(new PatientEntity());
        e.setId(1L);
        e.setName(dto.name());
        e.setAge(dto.age());
        e.setPreferredLanguage(dto.preferredLanguage());
        e.setInterests(dto.interests());
        return toDto(repo.save(e));
    }

    private PatientDto toDto(PatientEntity e) {
        return new PatientDto(e.getName(), e.getAge(), e.getPreferredLanguage(), e.getInterests());
    }
}
