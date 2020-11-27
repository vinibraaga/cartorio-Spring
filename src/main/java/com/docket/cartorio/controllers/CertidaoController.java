package com.docket.cartorio.controllers;

import com.docket.cartorio.entities.Certidao;
import com.docket.cartorio.repositories.CertidaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/certidoes")
public class CertidaoController {

    @Autowired
    private CertidaoRepository repository;

    @PostMapping
    public ResponseEntity criarCertidao(@RequestBody Certidao novaCertidao) {
        this.repository.save(novaCertidao);

        return ResponseEntity.created(null).build();
    }

    @GetMapping
    public ResponseEntity listarCertidoes() {
        if (this.repository.count() > 0) {
            return ResponseEntity.ok(this.repository.findAll());
        } else {
            return  ResponseEntity.noContent().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity alterarCertidao(@PathVariable Integer id, @RequestBody Certidao certidaoAtualizada) {

        Optional<Certidao> consultaExistente = this.repository.findById(id);

        if (consultaExistente.isPresent()) {
            Certidao certidaoEncontrada = consultaExistente.get();

            certidaoEncontrada.setNome(certidaoAtualizada.getNome());

            this.repository.save(certidaoEncontrada);

            return  ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirCertidao(@PathVariable Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
