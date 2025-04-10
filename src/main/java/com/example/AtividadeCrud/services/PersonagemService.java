package com.example.AtividadeCrud.services;

import com.example.AtividadeCrud.entities.Item_Magico;
import com.example.AtividadeCrud.entities.Personagem;
import com.example.AtividadeCrud.repositories.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public Personagem criarPersonagem(Personagem personagem) {
        int total = personagem.getForca() + personagem.getDefesa();
        if (total > 10) {
            throw new IllegalArgumentException("A soma de força e defesa deve ser no máximo 10");
        }
        return personagemRepository.save(personagem);
    }

    public List<Personagem> listarTodos() {
        return personagemRepository.findAll();
    }

    public Personagem buscarPorId(Long id) {
        return personagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
    }

    public Personagem atualizarNomeAventureiro(Long id, String novoNome) {
        Personagem personagem = buscarPorId(id);
        personagem.setNomeAventureiro(novoNome);
        return personagemRepository.save(personagem);
    }

    public void remover(Long id) {
        personagemRepository.deleteById(id);
    }

    public int getForcaTotal(Long id) {
        Personagem personagem = buscarPorId(id);
        int total = personagem.getForca();
        for (Item_Magico item : personagem.getItensMagicos()) {
            total += item.getForca();
        }
        return total;
    }

    public int getDefesaTotal(Long id) {
        Personagem personagem = buscarPorId(id);
        int total = personagem.getDefesa();
        for (Item_Magico item : personagem.getItensMagicos()) {
            total += item.getDefesa();
        }
        return total;
    }
}
