package com.example.AtividadeCrud.repositories;

import com.example.AtividadeCrud.entities.Item_Magico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Item_MagicoRepository extends JpaRepository<Item_Magico, Long> {

    List<Item_Magico> findByPersonagemId(Long personagemId);

    Optional<Item_Magico> findByTipoAndPersonagemId(String tipo, Long personagemId);
}
