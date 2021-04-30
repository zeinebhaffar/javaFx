/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import entité.medecin;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.medecinservice;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Zeineb Haffar
 */
public class AffichermedecinController implements Initializable {

    @FXML
    private Button btn_afficherlistemed;
    @FXML
    private Button btn_modifiermed;
    @FXML
    private Button btn_supprimermed;
    @FXML
    private TextField textspécialité;
    @FXML
    private TextField textnommed;
    @FXML
    private TableView<medecin> tableviewmed;
    @FXML
    private TextField cherchermed;
    @FXML
    private Button btn_recherchermed;
    String query= null ;
    Connection connection= null ;
    PreparedStatement ps = null;
    ResultSet rs=null;
    medecin medecin = null;
    int index =-1 ;
    ObservableList<medecin> medecinList=FXCollections.observableArrayList();
    ObservableList<medecin> oblist=FXCollections.observableArrayList();
    @FXML
    private TableColumn<medecin, String> spécialité;
    @FXML
    private TableColumn<medecin, String> région;
    @FXML
    private TableColumn<medecin, String> nommed;
    @FXML
    private Button actualiser;
    @FXML
    private Button retour;
    @FXML
    private Button Réserver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
        actualiser.setOnAction(event->{
            try {
              
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/views/affichermedecin.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AffichermedecinController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AffichermedecinController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    });
        
        Réserver.setOnAction(event ->{
        try {
            Parent page   = FXMLLoader.load(getClass().getResource("/views/ajouterRDV.fxml"));
            Scene scene = new Scene(page );
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        }   catch (IOException ex) {
                Logger.getLogger(AffichermedecinController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    });
        
    }    

    @FXML
    private void getafficher() throws SQLException {
        medecinList.clear();
        medecinservice med=new medecinservice();
        nommed.setCellValueFactory(new PropertyValueFactory<>("nommed"));
        spécialité.setCellValueFactory(new PropertyValueFactory<>("spécialité"));
        région.setCellValueFactory(new PropertyValueFactory<>("région"));
        tableviewmed.setItems((ObservableList<medecin>) med.readAll()); 
        
    }
    private void loadDate() {
         connection=DataSource.getInstance().getcnx();
      try {
          getafficher();
    } catch (SQLException ex) {
        Logger.getLogger(AfficherRDVController.class.getName()).log(Level.SEVERE, null, ex);
   }
   nommed.setCellValueFactory(new PropertyValueFactory<>("nommed"));
   spécialité.setCellValueFactory(new PropertyValueFactory<>("spécialité"));
   région.setCellValueFactory(new PropertyValueFactory<>("région"));
  
    
  
    
    
}
    @FXML
    private void getselectedmed(MouseEvent event) {
         index = tableviewmed.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
   }
    textspécialité.setText(spécialité.getCellData(index).toString());

  
    textnommed.setText(nommed.getCellData(index).toString());
    }

    @FXML
    private void modifier(ActionEvent event) {
        String spécialité = textspécialité.getText();
        String nommed = textnommed.getText();
           medecinservice med =new medecinservice() ;
           med.Updatemedecinpst(nommed ,spécialité );
           JOptionPane.showMessageDialog(null, "Le medecin'" + nommed + "' est modifié avec succés"  );
    }
    

    @FXML
    private void supprimer(ActionEvent event) {
        String nommed = textnommed.getText();
           medecinservice med =new medecinservice() ;
           med.Deletemedecinpst(nommed);
           JOptionPane.showMessageDialog(null, "Le medecin '" + nommed + "' est bien supprimé");
    }


    @FXML
    private void recherche(ActionEvent event) {
               tableviewmed.getItems().clear();
        String rch = cherchermed.getText();
         medecinservice us =new medecinservice() ;
         java.util.ArrayList<medecin> m1 = us.readmy(rch);
         for (int  i =0; i<m1.size(); i++)
         {
             oblist.add(new medecin(m1.get(i).getNommed(),m1.get(i).getRégion(), m1.get(i).getSpécialité() ));
     
     }
         nommed.setCellValueFactory(new PropertyValueFactory<>("nommed"));
         région.setCellValueFactory(new PropertyValueFactory<>("région"));
         spécialité.setCellValueFactory(new PropertyValueFactory<>("spécialité"));
          
          tableviewmed.setItems(oblist);
        
        
    
    }
 
    }

  

    
