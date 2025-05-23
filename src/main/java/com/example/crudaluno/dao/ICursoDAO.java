package com.example.crudaluno.dao;

import com.example.crudaluno.model.Curso;
import com.example.crudaluno.model.Area;
import java.util.List;

public interface ICursoDAO {
    void create(Curso curso);
    void update(Curso curso);
    void delete(Long codigo);
    List<Curso> findAll();
    Curso findById(Long codigo);
    Curso findBySigla(String sigla);
    List<Curso> findByArea(Area area);
}