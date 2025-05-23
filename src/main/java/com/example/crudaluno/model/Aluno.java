package com.example.crudaluno.model;

public class Aluno {
    private int id;
    private String nome;
    private int idade;
    private Curso curso;

    public Aluno() {}

    public Aluno(String nome, int idade, Curso curso) {
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
    }


    public Aluno(int id, String nome, int idade, Curso curso) {
        this(nome, idade, curso);
        this.id = id;
    }



    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}

