package tech.ada.school.service;

import tech.ada.school.domain.dto.ProfessorDto;
import tech.ada.school.domain.dto.exception.NotFoundException;

import java.util.List;

public interface IProfessorService {
    ProfessorDto criarProfessor(ProfessorDto pedido);

    List<ProfessorDto> listarProfessores();

    ProfessorDto buscarProfessor(int id) throws NotFoundException;

    ProfessorDto atualizarProfessor(int id, ProfessorDto pedido);

    void removerProfessor(int id) throws NotFoundException;
}
