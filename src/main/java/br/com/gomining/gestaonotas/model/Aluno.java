package br.com.gomining.gestaonotas.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "alunos")

@NoArgsConstructor
public class Aluno {

    @Id
    @Getter
    private String id;

    @Getter @Setter
    private String nome;

    @Getter
    private List<Nota> notas = new ArrayList<>();

    @Builder
    public Aluno(String id, String nome, List<Nota> notas) {
        this.id = id;
        this.nome = nome;
        this.notas = new ArrayList<>();
    }

    public void setNotas(Nota nota) {
        this.notas.add(nota);
    }
}
