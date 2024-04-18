package tech.ada.school.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunoDto {
    private int id;
    @NotBlank
    private String nome;

    private String cpf;

    private String email;

    @NotBlank
    private String turma;

    @Null
    private String activity;
}
