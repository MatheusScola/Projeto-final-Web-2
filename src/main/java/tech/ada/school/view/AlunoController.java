package tech.ada.school.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ada.school.domain.dto.AlunoDto;
import tech.ada.school.service.IAlunoService;


import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private final IAlunoService service;

    @Autowired
    AlunoController(IAlunoService service) {this.service = service;}

    @GetMapping
    public List<AlunoDto> lerProfessores(
            @RequestParam(required = false) String turma
    ) {
        if (turma == null) {
            return service.listarAlunos();
        } else {
            return service.buscarTurma(turma);
        }
    }

    @PostMapping
    public AlunoDto criarAluno(
            @RequestBody @Valid AlunoDto pedido
    ) {
        return service.criarAluno(pedido);
    }

    @PutMapping("/{id}")
    public AlunoDto atualizarAluno(
            @PathVariable("id") int id,
            @RequestBody AlunoDto pedido
    ) {
        final AlunoDto a = service.atualizarAluno(id, pedido);
        return a;
    }

    @GetMapping("/{id}")
    public AlunoDto buscarAluno (
            @PathVariable("id") int id
    ) {
        final AlunoDto a = service.buscarAluno(id);

        return a;
    }

    @DeleteMapping("/{id}")
    public void removerAluno(
            @PathVariable("id") int id
    ) {
        service.removerAluno(id);
    }
}
