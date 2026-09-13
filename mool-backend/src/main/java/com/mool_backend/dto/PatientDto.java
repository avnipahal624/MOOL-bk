package com.mool_backend.dto;

import java.util.List;

public record PatientDto(String name, int age, String preferredLanguage, List<String> interests) {}
