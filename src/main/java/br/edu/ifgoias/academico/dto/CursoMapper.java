package br.edu.ifgoias.academico.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.ifgoias.academico.entities.Curso;

@Mapper(componentModel = "spring")
public interface CursoMapper {
	
	CursoDTO toDTO(Curso curso);
	
	@Mapping(target = "alunos", ignore = true)
	Curso toEntity(CursoDTO cursoDTO);
}
