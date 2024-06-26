package tech.ada.school.service;

import tech.ada.school.domain.dto.AlunoDto;
import tech.ada.school.domain.exception.DuplicateKeyException;
import tech.ada.school.domain.exception.NotFoundException;

import java.util.List;

public interface IAlunoService {
    AlunoDto criarAluno(AlunoDto pedido) throws DuplicateKeyException;

    List<AlunoDto> listarAlunos();

    List<AlunoDto> buscarTurma(String turma);

    AlunoDto buscarAluno(int id) throws NotFoundException;

    AlunoDto atualizarAluno(int id, AlunoDto novoAluno) throws NotFoundException;

    void removerAluno(int id) throws NotFoundException;

    AlunoDto buscarAlunoPorCpf(String cpf) throws NotFoundException;
}
