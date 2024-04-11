package tech.ada.school.service;

import org.springframework.stereotype.Service;
import tech.ada.school.domain.dto.AlunoDto;
import tech.ada.school.domain.dto.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AlunoService implements IAlunoService{

    private final List<AlunoDto> alunos = new ArrayList<>();

    private int id = 1;


    @Override
    public AlunoDto criarAluno(AlunoDto pedido) {
        final AlunoDto aluno = new AlunoDto(
                id++,
                pedido.getNome(),
                pedido.getTurma()
        );
        alunos.add(aluno);
        return aluno;
    }

    @Override
    public List<AlunoDto> listarAlunos() {
        return alunos;
    }

    @Override
    public List<AlunoDto> buscarTurma(String turma) throws NotFoundException {

        List<AlunoDto> alunosPorTurma = alunos
                .stream()
                .filter(it -> Objects.equals(it.getTurma(), turma))
                .toList();

        if (alunosPorTurma.isEmpty()) {
            throw new NotFoundException(AlunoDto.class, turma);
        }
        return alunosPorTurma;
    }

    @Override
    public AlunoDto buscarAluno(int id) throws NotFoundException {
        return alunos
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(AlunoDto.class, String.valueOf(id)));
    }

    @Override
    public AlunoDto atualizarAluno(int id, AlunoDto novoAluno) throws NotFoundException {
        final AlunoDto aluno = alunos
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);

        if (aluno == null) {
            throw new NotFoundException(AlunoDto.class, String.valueOf(id));
        }
        alunos.remove(aluno);

        final AlunoDto a = new AlunoDto(id, novoAluno.getNome(), novoAluno.getTurma());
        alunos.add(a);
        return a;
    }

    @Override
    public void removerAluno(int id) throws NotFoundException {
        AlunoDto aluno = buscarAluno(id);
        alunos.remove(aluno);
    }
}
