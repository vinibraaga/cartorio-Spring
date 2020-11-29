
package com.docket.cartorio.controllers;

import com.docket.cartorio.entities.Cartorio;
import com.docket.cartorio.entities.Certidao;
import com.docket.cartorio.repositories.CartorioRepository;
import com.docket.cartorio.repositories.CertidaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/certidoes")
public class CertidaoController {

    @Autowired
    private CertidaoRepository repository;

    @Autowired
    private CartorioRepository cartorioRepository;

    //Página adicionar certidao
    @GetMapping("/adicionar/{id}")
    public String certidaoAdd(@PathVariable("id") Long id,Certidao certidao, Model model) {
        Cartorio cartorio = cartorioRepository.findById(id).get();
        model.addAttribute("cartorio",cartorio);
        return "addCertidao";
    }

    // Adicionar Certidao
    @PostMapping("adicionarCertidao/{id}")
    public String addCertidao(@PathVariable("id") Long id,Certidao novaCertidao, Model model) {
        Cartorio cartorio = cartorioRepository.findById(id).get();
        novaCertidao.setCartorio(cartorio);
        this.repository.save(novaCertidao);
        model.addAttribute("certidoes", novaCertidao);
        return "listaCertidoes";
    }

    //Página com lista de certidao do cartorio
    @GetMapping("listaCertidao/{id}")
    public String listarCertidao(@PathVariable("id") Long id,Model model) {
        List certidoes = repository.findByCartorioId(id);
        model.addAttribute("certidoes", certidoes);
        return "listaCertidoes";
    }

    //Lista de todas as certidoes
    @GetMapping("listaCertidao")
    public String listarCertidoes(Certidao certidao,Model model) {
        model.addAttribute("certidoes", repository.findAll());
        return "listaCertidoes";
    }


    //Visualização da certidao
    @GetMapping("editar/{idCertidao}")
    public String editarCertidao(@PathVariable("idCertidao") Long id, Model model) {
        Certidao certidao = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Certidao com id invalido:" + id));
        model.addAttribute("certidao", certidao);
        return "alterarCertidao";
    }

    //Atualização da certidao
    @PostMapping("atualizar/{idCertidao}")
    public String atualizarCertidao(@PathVariable("idCertidao") Long id, Certidao certidao, Model model) {
        Optional<Certidao> consultaExistente = this.repository.findById(id);
        Certidao certidaoEncontrada = consultaExistente.get();
        certidaoEncontrada.setNome(certidao.getNome());
        repository.save(certidaoEncontrada);
        model.addAttribute("certidoes", certidaoEncontrada);
        return "listaCertidoes";
    }

    //Excluir Certidao
    @GetMapping("deletar/{idCertidao}")
    public String deleteCertidao(@PathVariable("idCertidao") Long id, Model model) {
        Certidao certidao = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Certidao com id invalido:" + id));
        repository.delete(certidao);
        model.addAttribute("certidoes", repository.findAll());
        return "listaCertidoes";
    }


}
