package tech.ada.school.service;

import org.springframework.stereotype.Service;
import tech.ada.school.domain.dto.AlunoDto;

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
    public List<AlunoDto> buscarTurma(String turma) {
        return alunos
                .stream()
                .filter(it -> Objects.equals(it.getTurma(), turma))
                .toList();
    }

    @Override
    public AlunoDto buscarAluno(int id) {
        return alunos
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public AlunoDto atualizarAluno(int id, AlunoDto novoAluno) {
        final AlunoDto aluno = alunos
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);

        if (aluno == null) {
            return null;
        }
        alunos.remove(aluno);

        final AlunoDto a = new AlunoDto(id, novoAluno.getNome(), novoAluno.getTurma());
        alunos.add(a);
        return a;
    }

    @Override
    public void removerAluno(int id) {
        AlunoDto aluno = buscarAluno(id);
        alunos.remove(aluno);
    }
}
