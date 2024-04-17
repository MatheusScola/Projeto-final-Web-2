package tech.ada.school.domain.mappers;

import tech.ada.school.domain.dto.ProfessorDto;
import tech.ada.school.domain.entities.Professor;

public class ProfessorMapper {

    public static Professor toEntity(ProfessorDto dto) {
        return Professor
                .builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .build();
    }

    public static ProfessorDto toDto(Professor entity) {
        return new ProfessorDto(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                entity.getEmail()
        );
    }

}
