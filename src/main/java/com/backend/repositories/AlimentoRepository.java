package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.domain.Alimento;


@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long>{

	List<Alimento> findAllByColaboradorId(Long id_colab);
	

}
