package br.edu.ifgoias.academico.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(exclude = "cursos")
@EqualsAndHashCode(exclude = "cursos")
@NoArgsConstructor
@AllArgsConstructor // Gera construtor com todos os campos (ignora cursos pois já está sendo inicializado)
@Entity
public class Aluno implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idaluno;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "sexo", nullable = false)
	private String sexo;
	
	@Column(name = "dt_nasc", nullable = false)
	private LocalDate dt_nasc;
	
	@ManyToMany(mappedBy = "alunos")
	private List<Curso> cursos = new ArrayList<>();
		
	public List<Curso> getCursos() {
		return cursos;
	}

	public void adicionarCurso (Curso c) {
		
		if (!cursos.contains(c)) {
			cursos.add(c);
			c.adicionarAluno(this);
		}		
	}
	
}
