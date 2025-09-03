package br.edu.ifgoias.academico.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import br.edu.ifgoias.academico.entities.Aluno;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoDTO toDTO(Aluno aluno);

    @Mapping(target = "cursos", ignore = true)
    Aluno toEntity(AlunoDTO alunoDTO);
}
