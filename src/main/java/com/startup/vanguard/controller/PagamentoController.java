package com.startup.vanguard.controller;

import com.startup.vanguard.dto.pagamento.PagamentoPixRequestDTO;
import com.startup.vanguard.service.PagamentoService;
import com.startup.vanguard.service.PedidoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import lombok.Data;
import java.util.*;

@RestController
@RequestMapping("/api/pagamento")
@CrossOrigin(origins = "*")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/criar-pix")
    public ResponseEntity<?> criarPagamentoPix(@RequestBody PagamentoPixRequestDTO request) {
        try {
            var response = pagamentoService.criarPagamentoPix(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erro ao processar pagamento");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> receberWebhook(@RequestBody Map<String, Object> body) {
        try {
            pagamentoService.processarWebhook(body);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao processar webhook");
        }
    }
}

