package tech.ada.school.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.school.domain.dto.ProfessorDto;
import tech.ada.school.domain.dto.exception.NotFoundException;
import tech.ada.school.service.IProfessorService;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorControler {

    private final IProfessorService service;

    @Autowired
    ProfessorControler(IProfessorService service) {this.service = service;}

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> lerProfessores() {
        return ResponseEntity.ok(service.listarProfessores());
    }

    @PostMapping
    public ResponseEntity<ProfessorDto> criarProfesor (
            @RequestBody @Valid ProfessorDto pedido
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarProfessor(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDto> atualizarProfessor (
        @PathVariable("id") int id,
        @RequestBody ProfessorDto pedido
    ) {
        final ProfessorDto p = service.atualizarProfessor(id, pedido);

        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> buscarProfessor(
            @PathVariable ("id") int id
    ) throws NotFoundException {

        return ResponseEntity.ok(service.buscarProfessor(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProfessor (
            @PathVariable("id") int id
    ) throws NotFoundException {
        service.removerProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
