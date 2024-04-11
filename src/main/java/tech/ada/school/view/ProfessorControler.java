package tech.ada.school.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ada.school.domain.dto.ProfessorDto;
import tech.ada.school.service.IProfessorService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorControler {

    private final IProfessorService service;

    @Autowired
    ProfessorControler(IProfessorService service) {this.service = service;}

    @GetMapping
    public List<ProfessorDto> lerProfessores() {
        return service.listarProfessores();
    }

    @PostMapping
    public ProfessorDto criarProfesor (
            @RequestBody @Valid ProfessorDto pedido
    ) {
        return service.criarProfessor(pedido);
    }

    @PutMapping("/{id}")
    public ProfessorDto atualizarProfessor (
        @PathVariable("id") int id,
        @RequestBody ProfessorDto pedido
    ) {
        return service.atualizarProfessor(id, pedido);
    }

    @GetMapping("/{id}")
    public ProfessorDto buscarProfessor(
            @PathVariable ("id") int id
    ) {
        return service.buscarProfessor(id);
    }

    @DeleteMapping("/{id}")
    public void removerProfessor (
            @PathVariable("id") int id
    ) {
        service.removerProfessor(id);
    }
}
