package br.edu.ifgoias.academico.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import br.edu.ifgoias.academico.entities.Curso;
import br.edu.ifgoias.academico.dto.CursoMapper;
import br.edu.ifgoias.academico.dto.CursoDTO;
import br.edu.ifgoias.academico.repositories.CursoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CursoService {

    @Autowired
    private CursoRepository cursoRep;

    @Autowired
    private CursoMapper cursoMapper;

    @Transactional
    public Page<CursoDTO> listaCurso(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        
        if (search == null || search.isEmpty()) {
            return cursoRep.findAll(pageable).map(cursoMapper::toDTO);
        } else {
            return cursoRep.findByNomeCursoContainingIgnoreCase(search, pageable)
                           .map(cursoMapper::toDTO);
        }
    }

    @Transactional
    public CursoDTO cursoById(Integer id) {
        return cursoRep.findById(id)
                      .map(cursoMapper::toDTO)
                      .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + id));
    }
    
    @Transactional
    public CursoDTO cursoInserir(CursoDTO cDTO) {
        
    	if (cDTO == null || cDTO.getNomeCurso() == null) {
            throw new IllegalArgumentException("Dados do curso inválidos");
        }
        Curso curso = cursoMapper.toEntity(cDTO);
        cursoRep.save(curso);
        return cursoMapper.toDTO(curso);
    }

    public CursoDTO cursoAlterar(Integer id, CursoDTO cursoDTO) {
        
    	Curso curso = cursoRep.findById(id)
        					  .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + id));
        curso.setNomeCurso(cursoDTO.getNomeCurso());
        cursoRep.save(curso);
        return cursoMapper.toDTO(curso);
    }

    public void cursoDeletar(Integer id) {
        Curso curso = cursoRep.findById(id)
        					  .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + id));
        cursoRep.delete(curso);
    }
}