package org.example.pharmacy.controller;

import jakarta.validation.Valid;
import org.example.pharmacy.controller.dto.CreateDrugDto;
import org.example.pharmacy.controller.dto.CreateDrugResponseDto;
import org.example.pharmacy.controller.dto.GetDrugDto;
import org.example.pharmacy.infrastructure.entity.DrugEntity;
import org.example.pharmacy.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/drugs")
public class DrugController {

    private final DrugService drugService;

    @Autowired
    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping
    public List<GetDrugDto> getAllDrugs() {
        return drugService.getAll();
    }

    @GetMapping("/{id}")
    public GetDrugDto getDrugById(@PathVariable long id) {
        return drugService.getById(id);
    }

    @PostMapping
    public ResponseEntity<CreateDrugResponseDto> create(@Valid @RequestBody CreateDrugDto drug) {
        var newDrug = drugService.create(drug);
        return new ResponseEntity<>(newDrug, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DrugEntity> delete(@PathVariable long id) {
        drugService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
