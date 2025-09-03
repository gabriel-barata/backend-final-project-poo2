package br.edu.ifgoias.academico.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class AlunoDTO {

    private Integer idaluno;

    @NotNull(message = "O nome do aluno é obrigatório")
    @Size(max = 100, message = "O nome do aluno deve ter no máximo 100 caracteres")
    @NotBlank(message = "O nome do aluno é obrigatório")
    private String nome;

    @NotNull(message = "O sexo do aluno é obrigatório")
    private String sexo;

    @NotNull(message = "A data de nascimento do aluno é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dt_nasc;

}