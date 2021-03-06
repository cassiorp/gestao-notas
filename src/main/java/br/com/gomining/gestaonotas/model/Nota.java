package br.com.gomining.gestaonotas.model;


import br.com.gomining.gestaonotas.model.Enum.Situacao;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@ToString
@NoArgsConstructor
@Document(collection = "notas")
public class Nota {

    @Id
    @Getter
    private String id;

    @Getter @Setter
    private String disciplina;

    @Getter @Setter
    private String nomeAluno;

    @Getter @Setter
    private Double notaTotal;

    @Getter @Setter
    private Situacao situacao;

    @Getter @Setter
    private LocalDate date;

    @Builder
    public Nota(String id, String disciplina, String nomeAluno, Double notaTotal, Situacao situacao, LocalDate date) {
        this.id = id;
        this.disciplina = disciplina;
        this.nomeAluno = nomeAluno;
        this.notaTotal = notaTotal;
        this.situacao = situacao;
        this.date = date;
    }

}
