package com.backEndMobile.backEndMobile.modules.medicamentos.controller;

import com.backEndMobile.backEndMobile.modules.medicamentos.DTO.MedicamentoRequest;
import com.backEndMobile.backEndMobile.modules.medicamentos.DTO.MedicamentoResponse;
import com.backEndMobile.backEndMobile.modules.medicamentos.services.MedicamentoServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    private final MedicamentoServices medicamentoServices;

    public MedicamentoController(MedicamentoServices medicamentoServices) {
        this.medicamentoServices = medicamentoServices;
    }

    @PostMapping("/")
    public ResponseEntity<MedicamentoResponse> createMedicamento(@RequestBody MedicamentoRequest medicamentoRequest) {
        MedicamentoResponse response = medicamentoServices.createMedicamento(medicamentoRequest);
        URI uri = URI.create("/medicamentos/" + response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("create-all")
    public ResponseEntity<List<MedicamentoResponse>> createAllMedicamento(@RequestBody List<MedicamentoRequest> medicamentoRequest) {
        List<MedicamentoResponse> response = medicamentoServices.createAllMedicamento(medicamentoRequest);
        URI uri = URI.create("/medicamentos/" + response.get(0).id());
        return ResponseEntity.created(uri).body(response);
    }



    @GetMapping("/")
    public ResponseEntity<List<MedicamentoResponse>> getAllMedicamento() {
        List<MedicamentoResponse> response = medicamentoServices.getAllMedicamento();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicamento(@PathVariable Long id) {
        medicamentoServices.deleteMedicamento(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoRequest> updateMedicamento(@RequestBody MedicamentoRequest medicamentoRequest, @PathVariable Long id) {
        MedicamentoRequest response = medicamentoServices.updateMedicamento(medicamentoRequest, id);
        return ResponseEntity.ok().body(response);
    }
}
