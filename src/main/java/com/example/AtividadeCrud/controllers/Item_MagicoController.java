package com.example.AtividadeCrud.controllers;

import com.example.AtividadeCrud.entities.Item_Magico;
import com.example.AtividadeCrud.services.Item_MagicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-magicos")
public class Item_MagicoController {

    @Autowired
    private Item_MagicoService itemService;

    @PostMapping
    public Item_Magico criarItem(@RequestBody Item_Magico item) {
        return itemService.criarItem(item);
    }

    @GetMapping
    public List<Item_Magico> listarTodos() {
        return itemService.listarTodos();
    }

    @GetMapping("/{id}")
    public Item_Magico buscarPorId(@PathVariable Long id) {
        return itemService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void removerItem(@PathVariable Long id) {
        itemService.remover(id);
    }
}
