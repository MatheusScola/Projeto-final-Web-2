package tech.ada.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tech.ada.school.domain.dto.AlunoDto;
import tech.ada.school.domain.entities.Aluno;
import tech.ada.school.domain.exception.DuplicateKeyException;
import tech.ada.school.domain.exception.NotFoundException;
import tech.ada.school.domain.mappers.AlunoMapper;
import tech.ada.school.external.FeignBoredApi;
import tech.ada.school.repositories.AlunoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class AlunoServiceBD implements  IAlunoService{

    private final AlunoRepository repository;
    private final FeignBoredApi boredApi;

    @Override
    public AlunoDto criarAluno(AlunoDto pedido) throws DuplicateKeyException {

        Aluno a = AlunoMapper.toEntity(pedido);

        final Optional<Aluno> at = repository.findByCpf(a.getCpf());
        boolean cpfPresente = at.map(Aluno::getCpf).filter(cpf -> cpf.equals(a.getCpf())).isPresent();
        if (cpfPresente) {
            throw new DuplicateKeyException(Aluno.class, a.getCpf());
        }

        return AlunoMapper.toDto(repository.save(a), boredApi.getActivity().activity());
    }

    @Override
    public List<AlunoDto> listarAlunos() {
        return repository
                .findAll()
                .stream()
                .map(aluno -> AlunoMapper.toDto(aluno, boredApi.getActivity().activity()))
                .toList();
    }

    @Override
    public List<AlunoDto> buscarTurma(String turma){
        return repository
                .findByTurma(turma)
                .stream()
                .map(aluno -> AlunoMapper.toDto(aluno, boredApi.getActivity().activity()))
                .toList();
    }

    @Override
    public AlunoDto buscarAluno(int id) throws NotFoundException {
        return AlunoMapper.toDto(buscarAlunoPorId(id), boredApi.getActivity().activity());
    }

    @Override
    public AlunoDto atualizarAluno(int id, AlunoDto novoAluno) throws NotFoundException {

        Aluno a = buscarAlunoPorId(id);

        a.setCpf(novoAluno.getCpf() == null ? a.getCpf() : novoAluno.getCpf());
        a.setNome(novoAluno.getNome() == null ? a.getNome() : novoAluno.getNome());
        a.setEmail(novoAluno.getEmail() == null ? a.getEmail() : novoAluno.getEmail());
        a.setTurma(novoAluno.getTurma() == null ? a.getTurma() : novoAluno.getTurma());

        return AlunoMapper.toDto(repository.save(a), boredApi.getActivity().activity());
    }

    @Override
    public void removerAluno(int id) throws NotFoundException {

        final Aluno a = buscarAlunoPorId(id);

        repository.deleteById(id);
        repository.delete(a);

    }

    public Aluno buscarAlunoPorId(int id) throws NotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(Aluno.class, String.valueOf(id)));
    }

    public AlunoDto buscarAlunoPorCpf(String cpf) throws NotFoundException {
        return AlunoMapper.toDto(repository
                .findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException(Aluno.class, cpf)), boredApi.getActivity().activity());
    }
}
