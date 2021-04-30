
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import entité.medecin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.RDVservice;
import service.medecinservice;

/**
 * FXML Controller class
 *
 * @author Zeineb Haffar
 */
public class AjoutermedecinController implements Initializable {

    @FXML
    private TextField nommedecin;
    @FXML
    private TextField spécialité;
    @FXML
    private TextField région;
    @FXML
    private Button btn_addmedecin;
    @FXML
    private Button actualiser;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_addmedecin.setOnAction(event -> {
        
//         try {
             medecin m = new medecin(nommedecin.getText(), spécialité.getText(),région.getText());   
                medecinservice med = new medecinservice();
                med.ajoutermedecin(m);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("medecin ajouté avec succés!");
                 alert.show();
                //pu.setText("");
//            } catch (SQLException ex) {
//                Logger.getLogger(AjouterRDVController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        });
        
        actualiser.setOnAction(event->{
            try {
              
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/views/ajoutermedecin.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjoutermedecinController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        retour.setOnAction(event ->{
        try {
            Parent page   = FXMLLoader.load(getClass().getResource("/views/Accueil.fxml"));
            Scene scene = new Scene(page );
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        }   catch (IOException ex) {
                Logger.getLogger(AjoutermedecinController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    });
        
    }
    
}
