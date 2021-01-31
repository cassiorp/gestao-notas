package br.com.gomining.gestaonotas.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@NoArgsConstructor
@Data
public class NotaDTO {

    @NotNull
    @NotEmpty
    private String disciplina;

    @NotNull
    private Double notaTotal;

    @NotNull
    private LocalDate date;

}
