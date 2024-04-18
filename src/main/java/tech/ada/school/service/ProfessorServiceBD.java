package tech.ada.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tech.ada.school.domain.dto.ProfessorDto;
import tech.ada.school.domain.entities.Professor;
import tech.ada.school.domain.exception.DuplicateKeyException;
import tech.ada.school.domain.exception.NotFoundException;
import tech.ada.school.domain.mappers.ProfessorMapper;
import tech.ada.school.repositories.ProfessorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class ProfessorServiceBD implements IProfessorService {

    private final ProfessorRepository repository;

    @Override
    public ProfessorDto criarProfessor(ProfessorDto pedido) throws DuplicateKeyException {

        Professor p = ProfessorMapper.toEntity(pedido);

        final Optional<Professor> pt = repository.findByCpf(p.getCpf());
        boolean cpfPresente = pt.map(Professor::getCpf).filter(cpf -> cpf.equals(p.getCpf())).isPresent();
        if (cpfPresente) {
            throw new DuplicateKeyException(Professor.class, p.getCpf());
        }

        return ProfessorMapper.toDto(repository.save(p));

    }

    @Override
    public List<ProfessorDto> listarProfessores() {
        return repository
                .findAll()
                .stream()
                .map(ProfessorMapper::toDto)
                .toList();
    }

    @Override
    public ProfessorDto buscarProfessor(int id) throws NotFoundException {
        return ProfessorMapper.toDto(buscarProfessorPorId(id));
    }

    @Override
    public ProfessorDto atualizarProfessor(int id, ProfessorDto pedido) throws NotFoundException {

        Professor p = buscarProfessorPorId(id);

        p.setCpf(pedido.getCpf() == null ? p.getCpf() : pedido.getCpf());
        p.setNome(pedido.getNome() == null ? p.getNome() : pedido.getNome());
        p.setEmail(pedido.getEmail() == null ? p.getEmail() : pedido.getEmail());

        return ProfessorMapper.toDto(repository.save(p));

    }

    public Professor buscarProfessorPorId(int id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Professor.class, String.valueOf(id)));
    }

    @Override
    public void removerProfessor(int id) throws NotFoundException {

        final Professor p = buscarProfessorPorId(id);
        repository.deleteById(id);
        repository.delete(p);

    }

    @Override
    public ProfessorDto buscarPorCpf(String cpf) throws NotFoundException {
        return ProfessorMapper
                .toDto(repository
                        .findByCpf(cpf)
                        .orElseThrow(() -> new NotFoundException(Professor.class, cpf)));
    }
}
