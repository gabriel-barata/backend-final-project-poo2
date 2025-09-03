package br.edu.ifgoias.academico.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifgoias.academico.dto.CursoDTO;
import br.edu.ifgoias.academico.dto.PageDTO;
import br.edu.ifgoias.academico.services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "Gerenciamento de cursos")
public class CursoResource {

	@Autowired
	private CursoService servico;

	@GetMapping
	@Operation(summary = "Listar todos os cursos")
	public ResponseEntity<PageDTO<CursoDTO>> listaCursos(@RequestParam(defaultValue = "0") int page,
													     @RequestParam(defaultValue = "10") int size,
													     @RequestParam(defaultValue = "") String search) {

		Page<CursoDTO> pageCursos = servico.listaCurso(page, size, search);

		PageDTO<CursoDTO> pageDTO = new PageDTO<>(
				pageCursos.getContent(), 
				pageCursos.getTotalPages(),
				pageCursos.getTotalElements(), 
				pageCursos.getSize(), 
				pageCursos.getNumber()
		);

		return ResponseEntity.status(HttpStatus.OK).body(pageDTO);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar curso por ID")
	public ResponseEntity<CursoDTO> cursoById(@PathVariable Integer id) {
		try {
			CursoDTO curso = servico.cursoById(id);
			return ResponseEntity.status(HttpStatus.OK).body(curso);
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@PostMapping
	@Operation(summary = "Criar um novo curso")
	public ResponseEntity<CursoDTO> cursoInserir(@Valid @RequestBody CursoDTO c) {
		CursoDTO cursoSalvo = servico.cursoInserir(c);
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um curso existente")
	public ResponseEntity<CursoDTO> cursoAlterar(@PathVariable Integer id, @Valid @RequestBody CursoDTO c) {
		try {
			CursoDTO cursoAtualizado = servico.cursoAlterar(id, c);
			return ResponseEntity.status(HttpStatus.OK).body(cursoAtualizado);
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir um curso")
	public ResponseEntity<String> cursoDeletar(@PathVariable Integer id) {
		try {
			servico.cursoDeletar(id);
			return ResponseEntity.status(HttpStatus.OK).body("{\"idCurso\":" + id + "}");
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
}