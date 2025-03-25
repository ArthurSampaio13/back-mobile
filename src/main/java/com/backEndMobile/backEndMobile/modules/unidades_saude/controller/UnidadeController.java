package com.backEndMobile.backEndMobile.modules.unidades_saude.controller;


import com.backEndMobile.backEndMobile.modules.unidades_saude.DTO.UnidadeRequest;
import com.backEndMobile.backEndMobile.modules.unidades_saude.DTO.UnidadeResponse;
import com.backEndMobile.backEndMobile.modules.unidades_saude.services.UnidadeServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/unidades")
@RestController
@Tag(name = "Unidades de Saúde")
public class UnidadeController {

    private final UnidadeServices unidadeServices;

    public UnidadeController(UnidadeServices unidadeServices) {
        this.unidadeServices = unidadeServices;
    }

    @GetMapping("/")
    public ResponseEntity<List<UnidadeResponse>> getAllUnidades() {
        List<UnidadeResponse> unidades = unidadeServices.getAllUnidades();
        return ResponseEntity.ok().body(unidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponse> getUnidade(@PathVariable UUID id) {
        UnidadeResponse unidade = unidadeServices.getUnidade(id);
        return ResponseEntity.ok().body(unidade);
    }

    @PostMapping("/")
    public ResponseEntity<UnidadeResponse> createUnidade(@RequestBody UnidadeRequest unidadeRequest) {
        UnidadeResponse unidade = unidadeServices.createUnidade(unidadeRequest);
        URI uri = URI.create("/unidades/" + unidade.nome());
        return ResponseEntity.created(uri).body(unidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnidade(@PathVariable UUID id) {
        unidadeServices.deleteUnidade(id);
        return ResponseEntity.ok().build();
    }
}
