package com.docket.cartorio.controller;

import com.docket.cartorio.model.Cartorio;
import com.docket.cartorio.repository.CartorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/cartorios")
public class CartorioRestController {

    @Autowired
    private CartorioRepository repository;

    @PostMapping
    public ResponseEntity criarCartorio(@RequestBody Cartorio novoCartorio) {
        this.repository.save(novoCartorio);

        return ResponseEntity.created(null).build();
    }

    @GetMapping
    public ResponseEntity listarCartorios() {
        if (this.repository.count() > 0) {
            return ResponseEntity.ok(this.repository.findAll());
        } else {
            return  ResponseEntity.noContent().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity alterarCartorio(@PathVariable Long id, @RequestBody Cartorio cartorioAtualizado) {

        Optional <Cartorio> consultaExistente = this.repository.findById(id);

        if (consultaExistente.isPresent()) {
            Cartorio cartorioEncontrado = consultaExistente.get();

            cartorioEncontrado.setNome(cartorioAtualizado.getNome());
            cartorioEncontrado.setEndereco(cartorioAtualizado.getEndereco());

            this.repository.save(cartorioEncontrado);

            return  ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirCartorio(@PathVariable Long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
