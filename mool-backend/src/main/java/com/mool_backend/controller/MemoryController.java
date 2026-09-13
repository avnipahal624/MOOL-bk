package com.mool_backend.controller;

import com.mool_backend.dto.MemoryDto;
import com.mool_backend.dto.TrDto;
import com.mool_backend.model.MemoryEntity;
import com.mool_backend.repository.MemoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/memories")
public class MemoryController {

    private final MemoryRepository repo;

    public MemoryController(MemoryRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<MemoryDto> getAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    // Sensitive memories are filtered out server-side — the activity
    // generation pipeline should call THIS endpoint, never getAll().
    @GetMapping("/eligible")
    public List<MemoryDto> getEligible() {
        return repo.findAll().stream()
            .filter(m -> !m.isSensitive())
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    @PostMapping
    public MemoryDto create(@RequestBody MemoryDto dto) {
        MemoryEntity entity = new MemoryEntity();
        entity.setId(dto.id() != null && !dto.id().isBlank() ? dto.id() : UUID.randomUUID().toString());
        applyDto(entity, dto);
        return toDto(repo.save(entity));
    }

    @PutMapping("/{id}")
    public MemoryDto update(@PathVariable String id, @RequestBody MemoryDto dto) {
        MemoryEntity entity = repo.findById(id).orElseThrow(NoSuchElementException::new);
        applyDto(entity, dto);
        return toDto(repo.save(entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }

    private void applyDto(MemoryEntity entity, MemoryDto dto) {
        entity.setType(dto.type());
        if (dto.title() != null) {
            entity.setTitleEn(dto.title().en());
            entity.setTitleAs(dto.title().as());
            entity.setTitleBn(dto.title().bn());
        }
        if (dto.text() != null) {
            entity.setTextEn(dto.text().en());
            entity.setTextAs(dto.text().as());
            entity.setTextBn(dto.text().bn());
        }
        entity.setTags(dto.tags());
        entity.setSensitive(dto.sensitive());
        entity.setCreatedAt(dto.createdAt() != 0 ? dto.createdAt() : System.currentTimeMillis());
    }

    private MemoryDto toDto(MemoryEntity e) {
        return new MemoryDto(
            e.getId(),
            e.getType(),
            new TrDto(e.getTitleEn(), e.getTitleAs(), e.getTitleBn()),
            new TrDto(e.getTextEn(), e.getTextAs(), e.getTextBn()),
            e.getTags(),
            e.isSensitive(),
            e.getCreatedAt()
        );
    }
}
