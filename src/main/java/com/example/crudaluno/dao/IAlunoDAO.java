package com.example.crudaluno.dao;
import com.example.crudaluno.model.Aluno;
import java.util.List;

public interface IAlunoDAO {
    void inserir(Aluno aluno);
    List<Aluno> listar();
    void atualizar(Aluno aluno);
    void excluir(int id);
}
