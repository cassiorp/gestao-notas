package br.com.gomining.gestaonotas.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Builder
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
