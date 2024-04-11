package tech.ada.school.service;

import ch.qos.logback.classic.pattern.NopThrowableInformationConverter;
import tech.ada.school.domain.dto.AlunoDto;
import tech.ada.school.domain.dto.exception.NotFoundException;

import java.util.List;

public interface IAlunoService {
    AlunoDto criarAluno(AlunoDto pedido);

    List<AlunoDto> listarAlunos();

    List<AlunoDto> buscarTurma(String turma) throws NotFoundException;

    AlunoDto buscarAluno(int id) throws NotFoundException;

    AlunoDto atualizarAluno(int id, AlunoDto novoAluno) throws NotFoundException;

    void removerAluno(int id) throws NotFoundException;


}
