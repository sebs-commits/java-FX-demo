package com.example.comp228_db_javafx_demo;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField sIdField;
    @FXML
    private TextField sNameField;
    @FXML
    private TableView table;
    @FXML
    private TableColumn s_id_column;
    @FXML
    private TableColumn s_name_column;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void CreateTableAction(ActionEvent actionEvent)  throws SQLException {
        DBUtil.createTable("COMP228_M10");
    }

    public void DropTableAction(ActionEvent actionEvent) throws SQLException{
        DBUtil.dropTable("COMP228_M10");
    }

    public void DeleteAction(ActionEvent actionEvent) throws SQLException{
        Student student = (Student) table.getSelectionModel().getSelectedItem();
        DBUtil.deleteData("COMP228_M10", student.getId());
        populateData();
    }

    public void AddDataAction(ActionEvent actionEvent) throws SQLException{
        DBUtil.insertData("COMP228_M10", parseInt(sIdField.getText()), sNameField.getText());
        populateData();
    }
    public void populateData() throws SQLException{
        ResultSet rs = DBUtil.query("SELECT * FROM COMP228_M10");
        // creating a list of objects that we want to add to the table view
        ObservableList<Student> students = FXCollections.observableArrayList();
        // add objects one by one to the list
        while (rs.next()){
            Student student = new Student(rs.getInt("s_id"), rs.getString("s_name"));
            students.add(student);
        }
        // assign each attribute to each student class
        s_id_column.setCellValueFactory(new PropertyValueFactory("id")); // name of the student attribute
        s_name_column.setCellValueFactory(new PropertyValueFactory("name")); // name of the id attribute

        // sorting is optional
        // clear the table
        table.getItems().clear();
        // add all students
        table.getItems().addAll(students);
        // sort the table
        s_id_column.setSortType(TableColumn.SortType.ASCENDING);
        table.getSortOrder().add(s_id_column);
        table.sort();
    }
    public void initialize()throws SQLException{
        populateData();
    }
}