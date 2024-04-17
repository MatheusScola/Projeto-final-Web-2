package tech.ada.school.view;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.school.domain.dto.AlunoDto;
import tech.ada.school.domain.exception.NotFoundException;
import tech.ada.school.service.IAlunoService;


import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final IAlunoService service;

    @Autowired
    AlunoController(IAlunoService service) {this.service = service;}

    @GetMapping
    public ResponseEntity<List<AlunoDto>> lerAlunos(
            @RequestParam(required = false) String turma
    ) throws NotFoundException {
        if (turma == null) {
            return ResponseEntity.ok(service.listarAlunos());
        } else {
            return ResponseEntity.ok(service.buscarTurma(turma));
        }
    }

    @PostMapping
    public ResponseEntity<AlunoDto> criarAluno(
            @RequestBody @Valid AlunoDto pedido
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarAluno(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDto> atualizarAluno(
            @PathVariable("id") int id,
            @RequestBody AlunoDto pedido
    ) throws NotFoundException {
        final AlunoDto a = service.atualizarAluno(id, pedido);
        if (a == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(a);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> buscarAluno (
            @PathVariable("id") int id
    ) throws NotFoundException {
        return ResponseEntity.ok(service.buscarAluno(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAluno(
            @PathVariable("id") int id
    ) throws NotFoundException {
        service.removerAluno(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<AlunoDto> buscarPorCpf(
            @PathParam("cpf") String cpf
    ) throws NotFoundException {
        return ResponseEntity.ok(service.buscarAlunoPorCpf(cpf));
    }
}