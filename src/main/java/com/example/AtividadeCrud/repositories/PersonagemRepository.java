package com.example.AtividadeCrud.repositories;

import com.example.AtividadeCrud.entities.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

    Optional<Personagem> findByNomeAventureiro(String nomeAventureiro);

    List<Personagem> findByClasse(String classe);
}
