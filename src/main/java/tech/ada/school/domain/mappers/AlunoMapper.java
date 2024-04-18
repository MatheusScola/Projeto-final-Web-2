package tech.ada.school.domain.mappers;

import tech.ada.school.domain.dto.AlunoDto;
import tech.ada.school.domain.entities.Aluno;

public class AlunoMapper {

    public static Aluno toEntity(AlunoDto dto) {
        return Aluno.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .turma(dto.getTurma())
                .build();
    }

    public static AlunoDto toDto(Aluno entity, String activity) {
        return new AlunoDto(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getTurma(),
                activity
        );
    }

}
