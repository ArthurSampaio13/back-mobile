package com.backEndMobile.backEndMobile.modules.servicos_saude.controller;

import com.backEndMobile.backEndMobile.modules.servicos_saude.DTO.ServicosSaudeRequest;
import com.backEndMobile.backEndMobile.modules.servicos_saude.DTO.ServicosSaudeResponse;
import com.backEndMobile.backEndMobile.modules.servicos_saude.services.ServicosSaudeServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/servicos-saude")
@Tag(name = "Serviços de Saúde", description = "API para manipulação de serviços de saúde")
public class ServicosSaudeController {

    private final ServicosSaudeServices servicosSaudeServices;

    public ServicosSaudeController(ServicosSaudeServices servicosSaudeServices) {
        this.servicosSaudeServices = servicosSaudeServices;
    }

    @PostMapping("/")
    public ResponseEntity<ServicosSaudeResponse> createServicosSaude(@RequestBody ServicosSaudeRequest servicosSaudeRequest) {
        ServicosSaudeResponse response = servicosSaudeServices.createServicosSaude(servicosSaudeRequest);
        URI uri = URI.create("/servicos-saude/" + response.nome());
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<ServicosSaudeResponse>> getAllServicosSaude() {
        List<ServicosSaudeResponse> response = servicosSaudeServices.getAllServicosSaude();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicosSaude(@PathVariable UUID id) {
        servicosSaudeServices.deleteServicosSaude(id);
        return ResponseEntity.noContent().build();
    }

}
