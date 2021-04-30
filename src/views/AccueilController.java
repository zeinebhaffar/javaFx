/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zeineb Haffar
 */
public class AccueilController implements Initializable {
/**
     * Initializes the controller class.
     */
    private AnchorPane rootLayout;
    private Stage primaryStage;
     
    @FXML
    private Button btn_display; 
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_affmed;
    @FXML
    private Button btn_addmedecin;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_add.setOnAction(event -> {
            try {
            
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/ajouterRDV.fxml"));
                Scene scene = new Scene (page1);
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btn_display.setOnAction(event -> {
            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/views/afficherRDV.fxml"));
                Scene scene = new Scene (page2);
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show(); 
                
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
        
        btn_addmedecin.setOnAction(event -> {
            try {
                Parent page3 = FXMLLoader.load(getClass().getResource("/views/ajoutermedecin.fxml"));
                Scene scene = new Scene (page3);
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show(); 
                
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
         btn_affmed.setOnAction(event -> {
            try {
                Parent page4 = FXMLLoader.load(getClass().getResource("/views/affichermedecin.fxml"));
                Scene scene = new Scene (page4);
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show(); 
                
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
         
         
         
    }
           
    
}
