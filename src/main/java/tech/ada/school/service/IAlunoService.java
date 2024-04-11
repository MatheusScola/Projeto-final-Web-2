package tech.ada.school.service;

import tech.ada.school.domain.dto.AlunoDto;

import java.util.List;

public interface IAlunoService {
    AlunoDto criarAluno(AlunoDto pedido);

    List<AlunoDto> listarAlunos();

    List<AlunoDto> buscarTurma(String turma);

    AlunoDto buscarAluno(int id);

    AlunoDto atualizarAluno(int id, AlunoDto novoAluno);

    void removerAluno(int id);


}
