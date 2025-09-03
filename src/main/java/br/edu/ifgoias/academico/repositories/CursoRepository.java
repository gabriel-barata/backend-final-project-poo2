package br.edu.ifgoias.academico.repositories;

import br.edu.ifgoias.academico.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
	
    Page<Curso> findByNomeCursoContainingIgnoreCase(String nomeCurso, Pageable pageable);
    
}