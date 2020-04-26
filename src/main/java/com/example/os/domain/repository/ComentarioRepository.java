package com.example.os.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.os.api.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
