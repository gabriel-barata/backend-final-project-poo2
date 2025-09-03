package br.edu.ifgoias.academico.repositories;

import br.edu.ifgoias.academico.entities.Aluno;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
	
	 Page<Aluno> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
