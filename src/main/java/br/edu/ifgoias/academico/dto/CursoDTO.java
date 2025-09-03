package br.edu.ifgoias.academico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {
	
	private Integer idCurso;
	
	@NotNull(message = "O nome do curso é obrigatório")
	@Size(max = 100, message = "O nome do curso deve ter no máximo 100 caracteres")
	@NotBlank(message = "O nome do curso é obrigatório")
	private String nomeCurso;
}
