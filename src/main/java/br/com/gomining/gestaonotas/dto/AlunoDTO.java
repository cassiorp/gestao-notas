package br.com.gomining.gestaonotas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AlunoDTO {

    @NotNull
    @NotEmpty
    private String nome;

}
