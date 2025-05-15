package com.example.crudaluno.view;

import com.example.crudaluno.dao.AlunoDAO;
import com.example.crudaluno.model.Aluno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TelaAluno {

    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final TableView<Aluno> tabela = new TableView<>();
    private final ObservableList<Aluno> dados = FXCollections.observableArrayList();

    public void start(Stage stage) {

        TextField nomeField = new TextField();
        TextField idadeField = new TextField();
        TextField cursoField = new TextField();
        Button salvarBtn = new Button("Salvar");
        Button atualizarBtn = new Button("Atualizar");
        Button deletarBtn = new Button("Excluir");


        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);
        form.add(new Label("Nome:"), 0, 0);
        form.add(nomeField, 1, 0);
        form.add(new Label("Idade:"), 0, 1);
        form.add(idadeField, 1, 1);
        form.add(new Label("Curso:"), 0, 2);
        form.add(cursoField, 1, 2);
        form.add(salvarBtn, 0, 3);
        form.add(atualizarBtn, 1, 3);
        form.add(deletarBtn, 2, 3);


        TableColumn<Aluno, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getId()).asObject());

        TableColumn<Aluno, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNome()));

        TableColumn<Aluno, Integer> colIdade = new TableColumn<>("Idade");
        colIdade.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getIdade()).asObject());

        TableColumn<Aluno, String> colCurso = new TableColumn<>("Curso");
        colCurso.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCurso()));

        tabela.getColumns().addAll(colId, colNome, colIdade, colCurso);
        tabela.setItems(dados);
        atualizarTabela();


        tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                nomeField.setText(newSel.getNome());
                idadeField.setText(String.valueOf(newSel.getIdade()));
                cursoField.setText(newSel.getCurso());
            }
        });


        salvarBtn.setOnAction(e -> {
            try {
                Aluno aluno = new Aluno(
                        nomeField.getText(),
                        Integer.parseInt(idadeField.getText()),
                        cursoField.getText()
                );
                alunoDAO.inserir(aluno);
                limparCampos(nomeField, idadeField, cursoField);
                atualizarTabela();
            } catch (Exception ex) {
                alertarErro("Erro ao inserir aluno: " + ex.getMessage());
            }
        });

        atualizarBtn.setOnAction(e -> {
            Aluno selecionado = tabela.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                try {
                    selecionado.setNome(nomeField.getText());
                    selecionado.setIdade(Integer.parseInt(idadeField.getText()));
                    selecionado.setCurso(cursoField.getText());
                    alunoDAO.atualizar(selecionado);
                    limparCampos(nomeField, idadeField, cursoField);
                    atualizarTabela();
                } catch (Exception ex) {
                    alertarErro("Erro ao atualizar: " + ex.getMessage());
                }
            }
        });

        deletarBtn.setOnAction(e -> {
            Aluno selecionado = tabela.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                alunoDAO.excluir(selecionado.getId());
                limparCampos(nomeField, idadeField, cursoField);
                atualizarTabela();
            }
        });

        VBox layout = new VBox(10, form, tabela);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout, 600, 400);

        stage.setTitle("Gerenciamento de Alunos");
        stage.setScene(scene);
        stage.show();
    }

    private void atualizarTabela() {
        dados.setAll(alunoDAO.listar());
    }

    private void limparCampos(TextField nome, TextField idade, TextField curso) {
        nome.clear();
        idade.clear();
        curso.clear();
    }

    private void alertarErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
