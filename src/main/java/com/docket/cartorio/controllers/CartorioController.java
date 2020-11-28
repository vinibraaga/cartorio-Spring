
package com.docket.cartorio.controllers;

import com.docket.cartorio.entities.Cartorio;
import com.docket.cartorio.repositories.CartorioRepository;
import com.docket.cartorio.repositories.CertidaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/cartorios")
public class CartorioController {

    @Autowired
    private CartorioRepository repository;

    //Redireciona para adicionar Cartorio
    @GetMapping("adicionar")
    public String redAddCartorio(Cartorio cartorio) {
        return "addCartorio";
    }

    //lista de Cartorios
    @GetMapping("lista")
    public String litarCartorios(Model model) {
        model.addAttribute("cartorios", repository.findAll());
        return "listaCartorios";
    }

    // Adicionar Cartorio
    @PostMapping("add")
    public String addCartorio(Cartorio novoCartorio, BindingResult result) {
        if (result.hasErrors()) {
            return "addCartorio";
        }
        this.repository.save(novoCartorio);

        return "redirect:lista";
    }

    //Visualização cartório
    @GetMapping("editar/{id}")
    public String editarCartorio(@PathVariable("id") Integer id, Model model) {
        Cartorio cartorio = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cartorio com id invalido:" + id));
        model.addAttribute("cartorio", cartorio);
        return "alterarCartorio";
    }

    //Atualização cartorio
    @PostMapping("atualizar/{id}")
    public String atualizarCartorio(@PathVariable("id") Integer id, Cartorio cartorio, BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            cartorio.setId(id);
            return "alterarCartorio";
        }
        repository.save(cartorio);
        model.addAttribute("cartorios", repository.findAll());
        return "listaCartorios";
    }

    //Exclusão cartorio
    @GetMapping("deletar/{id}")
    public String deletarCartorio(@PathVariable("id") Integer id, Model model) {
        Cartorio cartorio = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cartorio com id invalido:" + id));
        repository.delete(cartorio);
        model.addAttribute("cartorios", repository.findAll());
        return "listaCartorios";
    }


}
