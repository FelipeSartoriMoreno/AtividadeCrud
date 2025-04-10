package com.example.AtividadeCrud.services;

import com.example.AtividadeCrud.entities.Item_Magico;
import com.example.AtividadeCrud.entities.Personagem;
import com.example.AtividadeCrud.repositories.Item_MagicoRepository;
import com.example.AtividadeCrud.repositories.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Item_MagicoService {

    @Autowired
    private Item_MagicoRepository itemRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    public Item_Magico criarItem(Item_Magico item) {
        if (item.getForca() == 0 && item.getDefesa() == 0) {
            throw new IllegalArgumentException("O item deve ter pelo menos força ou defesa diferente de zero");
        }

        if (item.getTipo().equalsIgnoreCase("Arma") && item.getDefesa() != 0) {
            throw new IllegalArgumentException("Item do tipo Arma deve ter defesa igual a 0");
        }

        if (item.getTipo().equalsIgnoreCase("Armadura") && item.getForca() != 0) {
            throw new IllegalArgumentException("Item do tipo Armadura deve ter força igual a 0");
        }

        if (item.getForca() > 10 || item.getDefesa() > 10) {
            throw new IllegalArgumentException("Força e Defesa máximos são 10");
        }

        if (item.getTipo().equalsIgnoreCase("Amuleto") && item.getPersonagem() != null) {
            Personagem personagem = item.getPersonagem();
            long amuletos = personagem.getItensMagicos().stream()
                    .filter(i -> i.getTipo().equalsIgnoreCase("Amuleto")).count();

            if (amuletos >= 1) {
                throw new IllegalArgumentException("O personagem já possui um amuleto");
            }

            personagem.getItensMagicos().add(item);
            personagemRepository.save(personagem);
        }

        return itemRepository.save(item);
    }

    public List<Item_Magico> listarTodos() {
        return itemRepository.findAll();
    }

    public Item_Magico buscarPorId(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    public void remover(Long id) {
        itemRepository.deleteById(id);
    }
}
