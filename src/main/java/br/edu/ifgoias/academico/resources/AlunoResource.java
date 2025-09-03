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

import br.edu.ifgoias.academico.dto.AlunoDTO;
import br.edu.ifgoias.academico.dto.PageDTO;
import br.edu.ifgoias.academico.services.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Gerenciamento de alunos")
public class AlunoResource {

    @Autowired
    private AlunoService servico;

    @GetMapping
    @Operation(summary = "Listar todos os alunos")
    public ResponseEntity<PageDTO<AlunoDTO>> listaAlunos(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size,
                                                         @RequestParam(defaultValue = "") String search) {

        Page<AlunoDTO> pageAlunos = servico.listaAluno(page, size, search);

        PageDTO<AlunoDTO> pageDTO = new PageDTO<>(
                pageAlunos.getContent(),
                pageAlunos.getTotalPages(),
                pageAlunos.getTotalElements(),
                pageAlunos.getSize(),
                pageAlunos.getNumber()
        );

        return ResponseEntity.status(HttpStatus.OK).body(pageDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluno por ID")
    public ResponseEntity<AlunoDTO> alunoById(@PathVariable Integer id) {
        try {
            AlunoDTO aluno = servico.alunoById(id);
            return ResponseEntity.status(HttpStatus.OK).body(aluno);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Criar um novo aluno")
    public ResponseEntity<AlunoDTO> alunoInserir(@Valid @RequestBody AlunoDTO a) {
        AlunoDTO alunoSalvo = servico.alunoInserir(a);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um aluno existente")
    public ResponseEntity<AlunoDTO> alunoAlterar(@PathVariable Integer id, @Valid @RequestBody AlunoDTO a) {
        try {
            AlunoDTO alunoAtualizado = servico.alunoAlterar(id, a);
            return ResponseEntity.status(HttpStatus.OK).body(alunoAtualizado);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um aluno")
    public ResponseEntity<Void> alunoDeletar(@PathVariable Integer id) {
        try {
            servico.alunoDeletar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
