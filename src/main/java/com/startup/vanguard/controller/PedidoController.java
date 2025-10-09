package com.startup.vanguard.controller;

import com.startup.vanguard.dto.pedido.PedidoRequestDTO;
import com.startup.vanguard.dto.pedido.PedidoResponseDTO;
import com.startup.vanguard.service.CarrinhoService;
import com.startup.vanguard.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> getAll(){
        return ResponseEntity.ok().body(pedidoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> getById(@PathVariable Long id){
        return  ResponseEntity.ok().body(pedidoService.getByID(id));
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> insert(@RequestBody PedidoRequestDTO dto){
        var pedido = pedidoService.insertPedido(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedido.id())
                .toUri();

        return ResponseEntity.created(uri).body(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }
}
