package com.startup.vanguard.service;

import com.startup.vanguard.dto.pagamento.PagamentoPixRequestDTO;
import com.startup.vanguard.dto.pedido.PedidoRequestDTO;
import com.startup.vanguard.dto.pedido.PedidoResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PagamentoService {

    @Value("${mercado.pago.token}")
    private String MERCADO_PAGO_ACCESS_TOKEN;
    private final PedidoService pedidoService;

    public PagamentoService(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public Map<String, Object> criarPagamentoPix(PagamentoPixRequestDTO request) {
        PedidoRequestDTO pedidoRequest = new PedidoRequestDTO(
                request.pedido().idCarrinho(),
                request.pedido().idComprador(),
                request.pedido().enderecoEntrega()
        );
        PedidoResponseDTO pedido = pedidoService.criarPedido(pedidoRequest);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + MERCADO_PAGO_ACCESS_TOKEN);
        headers.set("X-Idempotency-Key", UUID.randomUUID().toString());

        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("transaction_amount", request.valor());
        paymentData.put("description", request.descricao());
        paymentData.put("payment_method_id", "pix");

        Map<String, Object> payer = new HashMap<>();
        payer.put("email", request.email());

        String[] nomePartes = request.nome().split(" ", 2);
        payer.put("first_name", nomePartes[0]);
        payer.put("last_name", nomePartes.length > 1 ? nomePartes[1] : nomePartes[0]);

        Map<String, Object> identification = new HashMap<>();
        identification.put("type", "CPF");
        identification.put("number", request.cpf().replaceAll("[^0-9]", ""));
        payer.put("identification", identification);

        paymentData.put("payer", payer);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(paymentData, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "https://api.mercadopago.com/v1/payments",
                HttpMethod.POST,
                entity,
                Map.class
        );

        String paymentStatus = response.getBody().get("status").toString();
        pedidoService.updateStatus(pedido.id(), paymentStatus);

        Map<String, Object> responseBody = response.getBody();
        responseBody.put("idPedido", pedido.id());

        return responseBody;
    }

    public void processarWebhook(Map<String, Object> body) {
        Map<String, Object> data = (Map<String, Object>) body.get("data");
        if (data == null || data.get("id") == null) {
            throw new IllegalArgumentException("Webhook sem ID de pagamento");
        }

        Long paymentId = Long.valueOf(data.get("id").toString());
        String status = consultarStatusPagamento(paymentId);

        System.out.println("🔎 Status atual do pagamento " + paymentId + ": " + status);

        switch (status) {
            case "approved":
                pedidoService.processarItensCarrinho(paymentId.toString());
                System.out.println("✅ Pagamento aprovado! Pedido atualizado.");
                break;

            case "rejected":
                pedidoService.pagamentoReprovado(paymentId.toString());
                System.out.println("❌ Pagamento rejeitado! Pedido marcado como falha.");
                break;

            case "pending":
                System.out.println("⏳ Pagamento pendente. Aguardando confirmação...");
                break;

            default:
                System.out.println("⚠️ Status não tratado: " + status);
        }
    }

    public String consultarStatusPagamento(Long paymentId) {

        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + MERCADO_PAGO_ACCESS_TOKEN);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = rest.exchange(
                "https://api.mercadopago.com/v1/payments/" + paymentId,
                HttpMethod.GET,
                entity,
                Map.class
        );

        Map<String, Object> responseBody = response.getBody();
        return responseBody.get("status").toString();
    }
}
