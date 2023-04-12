/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import edu.workshop.services.Evenement1CRUD;
import edu.worshop.interfaces.EvenementCRUD;
import edu.worshop.model.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author HOUYEM
 */
public class AjoutEvenementBackController implements Initializable {

    @FXML
    private DatePicker DateEvenementfx;
    @FXML
    private TextField TypeEvenementfx;
    @FXML
    private TextField LieuEvenementfx;
    @FXML
    private TextArea DescriptionEvenementfx;
    @FXML
    private TextField Even_picEvenementfx;
    @FXML
    private TextField TitreEvenementfx;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    
    private void AjouterEvenementBack(ActionEvent event) {
        Date date = java.sql.Date.valueOf(DateEvenementfx.getValue());
        String type = TypeEvenementfx.getText();
        String lieu = LieuEvenementfx.getText();
        String description = DescriptionEvenementfx.getText();
        String even_pic = Even_picEvenementfx.getText();
        String titre = TitreEvenementfx.getText();
        
               int minLength = 6;
 

if(date==null) {
   System.out.println("La date ne peut pas être vide");
}

        
        
        
        
         else if (type.isEmpty()) {
            showAlert("le type est vide ");
         }/*else if (type.matches("[a-zA-Z]+")) {
            showAlert("le type doit être alphabétique ");
         }*/else if (lieu.isEmpty()) {
            showAlert("Le lieu est vide ");
         }else if (description.isEmpty()) {
            showAlert("La description est vide ");
         }else if (
       description.length() < minLength) {
            showAlert("La description doit contenir au moins " + minLength + " caractères");
            
         }
    else if (even_pic.isEmpty()) {
            showAlert("L'image est vide ");
         }else if (titre.isEmpty()) {
            showAlert("Le titre est vide ");
         }else{
         
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("are you sure to add this event ?");
Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {


              Evenement E = new Evenement(date, type, lieu, description, even_pic, titre);
                    Evenement1CRUD event1 = new Evenement1CRUD();
                    event1.ajouterEvenement(E);
                    showAlert("Event added successfully");
    }
                 try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageEvenementBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
 
    
}}

    private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    }
