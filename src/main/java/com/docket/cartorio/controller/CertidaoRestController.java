package com.docket.cartorio.controller;

import com.docket.cartorio.model.Cartorio;
import com.docket.cartorio.model.Certidao;
import com.docket.cartorio.repository.CartorioRepository;
import com.docket.cartorio.repository.CertidaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/certidoes")
public class CertidaoRestController {

    @Autowired
    private CertidaoRepository repository;

    @Autowired
    private CartorioRepository cartorioRepository;

    @PostMapping("/{id}")
    public ResponseEntity criarCertidao(@PathVariable Long id ,@RequestBody Certidao novaCertidao) {

        Cartorio cartorio = cartorioRepository.findById(id).get();

        if(cartorio != null) {
            novaCertidao.setCartorio(cartorio);
            this.repository.save(novaCertidao);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity listarCertidoesCartorio(@PathVariable Long id) {
        List certidoes = repository.findByCartorioId(id);

        return certidoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(certidoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity alterarCertidao(@PathVariable Long id, @RequestBody Certidao certidaoAtualizada) {

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

    @DeleteMapping("/{idCertidao}/{id}")
    public ResponseEntity excluirCertidao(@PathVariable Long idCertidao,@PathVariable Long id) {
        if (this.repository.existsByIdCertidaoAndCartorio(idCertidao,id)) {
            this.repository.deletarCertidao(idCertidao, id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
