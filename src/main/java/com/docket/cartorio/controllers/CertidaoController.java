
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
    public String certidaoAdd(@PathVariable("id") Integer id,Certidao certidao, Model model) {
        Cartorio cartorio = cartorioRepository.findById(id).get();
        certidao.setCartorio(cartorio);
        model.addAttribute("cartorio",cartorio);
        model.addAttribute("certidao", certidao);
        return "addCertidao";
    }

    // Adicionar Certidao
    @PostMapping("adicionarCertidao/{id}")
    public String addCertidao(@PathVariable("id") Integer id,Certidao novaCertidao, BindingResult result, Model model) {
        Cartorio cartorio = cartorioRepository.findById(id).get();
        novaCertidao.setCartorio(cartorio);
        this.repository.save(novaCertidao);
        model.addAttribute("cartorio",cartorio);
        model.addAttribute("certidoes", repository.findAll());
        return "listaCertidoes";
    }

    //Página com lista de certidao
    @GetMapping("listaCertidao/{id}")
    public String listarCertidao(@PathVariable("id") Integer id,Model model) {
        List certidoes = repository.findByCartorioId(id);
        model.addAttribute("certidoes", certidoes);
        return "listaCertidoes";
    }


    //Visualização certidao
    @GetMapping("editar/{idCertidao}")
    public String showUpdateForm(@PathVariable("idCertidao") Integer id, Model model) {
        Certidao certidao = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Certidao com id invalido:" + id));
        model.addAttribute("certidao", certidao);
        return "alterarCertidao";
    }

    //Atualização certidao
    @PostMapping("atualizar/{idCertidao}")
    public String atualizarCertidao(@PathVariable("idCertidao") Integer id, Certidao certidao, BindingResult result,
                                 Model model) {
        Optional<Certidao> consultaExistente = this.repository.findById(id);
        Certidao certidaoEncontrada = consultaExistente.get();
        certidaoEncontrada.setNome(certidao.getNome());
        repository.save(certidaoEncontrada);
        model.addAttribute("certidoes", repository.findAll());
        return "listaCertidoes";
    }

    //Excluir Certidao
    @GetMapping("deletar/{idCertidao}")
    public String deleteCertidao(@PathVariable("idCertidao") Integer id, Model model) {
        Certidao certidao = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Certidao com id invalido:" + id));
        repository.delete(certidao);
        model.addAttribute("certidoes", repository.findAll());
        return "listaCertidoes";
    }


}
