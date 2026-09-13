package com.mool_backend.dto;

import java.util.List;

public record MemoryDto(
    String id,
    String type,
    TrDto title,
    TrDto text,
    List<String> tags,
    boolean sensitive,
    long createdAt
) {}
