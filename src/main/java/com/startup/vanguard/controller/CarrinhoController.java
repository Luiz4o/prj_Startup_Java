package com.startup.vanguard.controller;

import com.startup.vanguard.dto.carrinho.CarrinhoItemResquestDTO;
import com.startup.vanguard.dto.carrinho.CarrinhoRequestDTO;
import com.startup.vanguard.dto.carrinho.CarrinhoResponseDTO;
import com.startup.vanguard.dto.carrinho.CarrinhoUpdateDTO;
import com.startup.vanguard.service.CarrinhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    public ResponseEntity<List<CarrinhoResponseDTO>> findAll() {
        return ResponseEntity.ok(carrinhoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoResponseDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok(carrinhoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CarrinhoResponseDTO> insertCarrinho(@RequestBody CarrinhoRequestDTO carrinhoRequestDTO) {
        var carrinho = carrinhoService.create(carrinhoRequestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(carrinho.id())
                .toUri();
        return ResponseEntity.created(uri).body(carrinho);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrinhoResponseDTO> updateCarrinho(@PathVariable long id,
                                                              @RequestBody CarrinhoUpdateDTO carrinhoUpdateDTO) {
        var carrinhoAtualizado = carrinhoService.update(id, carrinhoUpdateDTO);
        return ResponseEntity.ok(carrinhoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrinho(@PathVariable long id) {
        carrinhoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<CarrinhoResponseDTO> insertItemCarrinho(@RequestBody CarrinhoItemResquestDTO dto){
        var carrinhoAtualizado = carrinhoService.InsertItem(dto);
        return  ResponseEntity.ok().body(carrinhoAtualizado);
    }
}
