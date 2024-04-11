package tech.ada.school.service;

import org.apache.tomcat.util.bcel.classfile.ArrayElementValue;
import org.springframework.stereotype.Service;
import tech.ada.school.domain.dto.ProfessorDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService implements IProfessorService {

    private final List<ProfessorDto> professores = new ArrayList<>();
    private int id = 1;

    @Override
    public ProfessorDto criarProfessor(ProfessorDto novoProfessor) {
        final ProfessorDto p = new ProfessorDto(
                id++,
                novoProfessor.getNome(),
                novoProfessor.getCpf(),
                novoProfessor.getEmail()
        );
        professores.add(p);
        return p;
    }

    @Override
    public List<ProfessorDto> listarProfessores() {
        return professores;
    }

    @Override
    public ProfessorDto buscarProfessor(int id) {
        return professores
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ProfessorDto atualizarProfessor(int id, ProfessorDto pedido) {
        final ProfessorDto professor = professores.stream()
                .filter(it -> it.getId() == id)
                .findFirst()
                .orElseGet(null);

        if (professor == null) {
            return null;
        }

        professores.remove(professor);

        final ProfessorDto p = new ProfessorDto(
                id,
                pedido.getNome(),
                pedido.getCpf(),
                pedido.getEmail()
        );
        professores.add(p);
        return p;
    }

    @Override
    public void removerProfessor(int id) {
        final ProfessorDto professor = buscarProfessor(id);
        professores.remove(professor);
    }
}
