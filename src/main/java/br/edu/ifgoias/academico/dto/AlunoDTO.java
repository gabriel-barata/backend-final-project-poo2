package br.edu.ifgoias.academico.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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

    @NotBlank(message = "O nome do aluno é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome do aluno deve ter entre 2 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O sexo do aluno é obrigatório.")
    @Size(max = 1, message = "O sexo deve ser 'M' ou 'F'.")
    private String sexo;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @PastOrPresent(message = "A data de nascimento não pode ser uma data futura.")
    private LocalDate dt_nasc;
}
