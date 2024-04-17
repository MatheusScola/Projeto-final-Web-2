package tech.ada.school.service;

import tech.ada.school.domain.dto.ProfessorDto;
import tech.ada.school.domain.exception.NotFoundException;

import java.util.List;

public interface IProfessorService {
    ProfessorDto criarProfessor(ProfessorDto pedido);

    List<ProfessorDto> listarProfessores();

    ProfessorDto buscarProfessor(int id) throws NotFoundException;

    ProfessorDto atualizarProfessor(int id, ProfessorDto pedido) throws NotFoundException;

    void removerProfessor(int id) throws NotFoundException;

    ProfessorDto buscarPorCpf(String cpf) throws NotFoundException;
}
