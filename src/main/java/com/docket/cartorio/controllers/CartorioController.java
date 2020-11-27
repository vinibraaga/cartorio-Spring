package com.docket.cartorio.controllers;

import com.docket.cartorio.entities.Cartorio;
import com.docket.cartorio.repositories.CartorioRepository;
import com.docket.cartorio.repositories.CertidaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cartorios")
public class CartorioController {

    @Autowired
    private CartorioRepository repository;

    @Autowired
    private CertidaoRepository certidaoRepository;

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
    public ResponseEntity alterarCartorio(@PathVariable Integer id, @RequestBody Cartorio cartorioAtualizado) {

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
    public ResponseEntity excluirCartorio(@PathVariable Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
