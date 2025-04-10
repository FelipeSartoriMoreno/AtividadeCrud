package com.example.AtividadeCrud.controllers;

import com.example.AtividadeCrud.entities.Personagem;
import com.example.AtividadeCrud.services.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;


    @PostMapping
    public Personagem criarPersonagem(@RequestBody Personagem personagem) {
        personagem.setItensMagicos(new ArrayList<>()); // Garante que vem vazio
        return personagemService.criarPersonagem(personagem);
    }


    @GetMapping
    public List<Personagem> listarTodos() {
        return personagemService.listarTodos();
    }


    @GetMapping("/{id}")
    public Personagem buscarPorId(@PathVariable Long id) {
        return personagemService.buscarPorId(id);
    }


    @PutMapping("/{id}/nome-aventureiro")
    public Personagem atualizarNomeAventureiro(@PathVariable Long id, @RequestBody String novoNome) {
        return personagemService.atualizarNomeAventureiro(id, novoNome);
    }


    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        personagemService.remover(id);
    }


    @GetMapping("/{id}/forca-total")
    public int getForcaTotal(@PathVariable Long id) {
        return personagemService.getForcaTotal(id);
    }


    @GetMapping("/{id}/defesa-total")
    public int getDefesaTotal(@PathVariable Long id) {
        return personagemService.getDefesaTotal(id);
    }
}
