package br.com.gomining.gestaonotas.model;


import br.com.gomining.gestaonotas.model.Enum.Situacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notas")
public class Nota {

    @Id
    private String id;

    private String disciplina;

    private String nomeAluno;

    private Double notaTotal;

    private Situacao situacao;

    private LocalDate date;

    @Builder
    public Nota(String disciplina, String nomeAluno, Double notaTotal, Situacao situacao, LocalDate date) {
        this.disciplina = disciplina;
        this.nomeAluno = nomeAluno;
        this.notaTotal = notaTotal;
        this.date = date;
        this.situacao = situacao;
    }

}
