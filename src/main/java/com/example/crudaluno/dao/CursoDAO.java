package com.example.crudaluno.dao;

import com.example.crudaluno.config.ConnectionFactory;
import com.example.crudaluno.model.Area;
import com.example.crudaluno.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO implements ICursoDAO {
    @Override
    public void create(Curso curso) {
        String sql = "INSERT INTO curso (nome, sigla, area) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getSigla());
            stmt.setString(3, curso.getArea().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Curso curso) {
        String sql = "UPDATE curso SET nome=?, sigla=?, area=? WHERE codigo=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getSigla());
            stmt.setString(3, curso.getArea().name());
            stmt.setLong(4, curso.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long codigo) {
        String sql = "DELETE FROM curso WHERE codigo=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Curso> findAll() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getLong("codigo"),
                        rs.getString("nome"),
                        rs.getString("sigla"),
                        Area.valueOf(rs.getString("area"))
                );
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public Curso findById(Long codigo) {
        String sql = "SELECT * FROM curso WHERE codigo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Curso(
                        rs.getLong("codigo"),
                        rs.getString("nome"),
                        rs.getString("sigla"),
                        Area.valueOf(rs.getString("area"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Curso findBySigla(String sigla) {
        String sql = "SELECT * FROM curso WHERE sigla = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sigla);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Curso(
                        rs.getLong("codigo"),
                        rs.getString("nome"),
                        rs.getString("sigla"),
                        Area.valueOf(rs.getString("area"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Curso> findByArea(Area area) {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso WHERE area = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, area.name());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getLong("codigo"),
                        rs.getString("nome"),
                        rs.getString("sigla"),
                        Area.valueOf(rs.getString("area"))
                );
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }
}
