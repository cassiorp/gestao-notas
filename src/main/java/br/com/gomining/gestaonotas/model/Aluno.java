package br.com.gomining.gestaonotas.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "alunos")
@NoArgsConstructor
@ToString
public class Aluno {

    @Id
    @Getter @Setter
    private String id;

    @Getter @Setter
    private String nome;

    @Getter
    private List<Nota> boletim = new ArrayList<>();

    @Builder
    public Aluno(String id, String nome, List<Nota> boletim) {
        this.id = id;
        this.nome = nome;
        this.boletim = new ArrayList<>();
    }

    public void setBoletim(Nota nota) {
        this.boletim.add(nota);
    }

}
