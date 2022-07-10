package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.domain.Colaborador;

@Repository


public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{

	
}
