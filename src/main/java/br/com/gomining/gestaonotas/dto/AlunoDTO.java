package br.com.gomining.gestaonotas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AlunoDTO {

    private String nome;

    public AlunoDTO(String nome) {
        this.nome = nome;
    }
}
