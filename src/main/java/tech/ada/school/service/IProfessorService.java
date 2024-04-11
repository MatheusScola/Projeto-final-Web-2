package tech.ada.school.service;

import tech.ada.school.domain.dto.ProfessorDto;

import java.util.List;

public interface IProfessorService {
    ProfessorDto criarProfessor(ProfessorDto pedido);

    List<ProfessorDto> listarProfessores();

    ProfessorDto buscarProfessor(int id);

    ProfessorDto atualizarProfessor(int id, ProfessorDto pedido);

    void removerProfessor(int id);
}
