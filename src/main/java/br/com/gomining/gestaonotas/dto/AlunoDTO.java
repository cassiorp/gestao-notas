package br.com.gomining.gestaonotas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class AlunoDTO {

    @NotNull
    @NotEmpty
    private String nome;

    public AlunoDTO(String nome) {
        this.nome = nome;
    }
}
