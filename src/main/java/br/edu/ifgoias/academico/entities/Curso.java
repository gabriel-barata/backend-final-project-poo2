package br.edu.ifgoias.academico.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter
@ToString(exclude = "alunos")
@EqualsAndHashCode(exclude = "alunos")
@NoArgsConstructor
@AllArgsConstructor // Gera construtor com idcurso e nomeCurso (ignora alunos pois já está sendo inicializado)
@Entity
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcurso")
	private Integer idCurso;
	
	@Column(name = "nomecurso", nullable = false)
	private String nomeCurso;
	
	@ManyToMany
	@JoinTable (name = "aluno_curso",
				joinColumns = @JoinColumn(name="idcurso"),
				inverseJoinColumns = @JoinColumn(name="idaluno") 
			   )
	private List<Aluno> alunos = new ArrayList<>();

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void adicionarAluno(Aluno aluno) {
		if( aluno != null && !alunos.contains(aluno) ) {
			this.alunos.add(aluno);
			//aluno.adicionarCurso(this);
		}
	}
}
