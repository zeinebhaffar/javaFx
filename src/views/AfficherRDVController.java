/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.pdfjet.Letter;
import com.pdfjet.PDF;
import com.pdfjet.Page;
//import com.pdfjet.Paragraph;
import entité.RDV;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
//import javax.swing.text.Document;
import service.RDVservice;
import utils.DataSource;


/**
 * FXML Controller class
 *
 * @author Zeineb Haffar
 */
public class AfficherRDVController implements Initializable {

    @FXML
    private TableColumn<RDV, String> nom;
    @FXML
    private TableColumn<RDV, String> nommed;
    @FXML
    private TableColumn<RDV, String> date;
    @FXML
    private Button btn_afficher;
    @FXML
    private TableView<RDV> tableview;
    String query = null;
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    RDV RDV = null;
    int index = -1;
    ObservableList<RDV> RDVList = FXCollections.observableArrayList();
    ObservableList<RDV> oblist = FXCollections.observableArrayList();
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private TextField textid;
    @FXML
    private TextField textnom;
    private TextField textnommed;
    @FXML
    private Button btn_rechercher;
    @FXML
    private TextField chercher;
    @FXML
    private Button actualiser;
    @FXML
    private Button retour;
    @FXML
    private Button Trie;
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();


        actualiser.setOnAction(event->{
            try {
              
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/views/afficherRDV.fxml"));
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
    }

    @FXML
    private void getAffiche() throws SQLException {
        RDVList.clear();
        RDVservice rdv = new RDVservice();

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nommed.setCellValueFactory(new PropertyValueFactory<>("nommed"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableview.setItems((ObservableList<RDV>) rdv.readAll());
        //tableview.setItems(RDVList);
    }

    private void loadDate() {
        connection = DataSource.getInstance().getcnx();
        try {
            getAffiche();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRDVController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nommed.setCellValueFactory(new PropertyValueFactory<>("nommed"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    @FXML
    private void getselected(MouseEvent event) {
        index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

        }
        textid.setText(nommed.getCellData(index).toString());

        textnom.setText(nom.getCellData(index).toString());
    }

    @FXML
    private void delete(ActionEvent event) {
        String nom = textnom.getText();
        RDVservice rdv = new RDVservice();
        rdv.DeleteRDVpst(nom);
        JOptionPane.showMessageDialog(null, "Votre rendez-vous est bien supprimé ");
    }

    @FXML
    private void edit(ActionEvent event) {
//        RDVservice rdv=new RDVservice();
        String nom = textnom.getText();
        String id = textid.getText();
        RDVservice rdv = new RDVservice();
        rdv.UpdateRDVpst(nom, id);
        JOptionPane.showMessageDialog(null, "Cher patient,'" + nom + "'votre rendez vous est bien modifié");
    }

    @FXML
    private void readmy(ActionEvent event) {
         tableview.getItems().clear();
        String rch = chercher.getText();
         RDVservice us =new RDVservice() ;
         java.util.ArrayList<RDV> m1 = us.readmy(rch);
         for (int  i =0; i<m1.size(); i++)
         {
             oblist.add(new RDV(m1.get(i).getNom(),m1.get(i).getNommed(), m1.get(i).getDate() ));
     
     }
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nommed.setCellValueFactory(new PropertyValueFactory<>("nommed"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
          
          tableview.setItems(oblist);
        
        
    }
    
   

    @FXML
    private void trie(ActionEvent event) throws SQLException {
        RDVList.clear();
    String query= "SELECT * FROM RDV\n" +
           "ORDER BY date ;";
     ps=connection.prepareStatement(query);
     rs= ps.executeQuery();
       while(rs.next()){
  RDVList.add(new RDV(rs.getString("nom"),rs.getString("nommed"),rs.getString("date")));
            }
     tableview.setItems(RDVList);
    }

    @FXML
    private void mypdf(ActionEvent event) {
         Document document = new Document() {};
        try {
            String path = "";
            JFileChooser j = new JFileChooser();
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int x = j.showSaveDialog(null);
            if (x == JFileChooser.APPROVE_OPTION){
            path= j.getSelectedFile().getPath();
            }
            
            PdfWriter.getInstance(document ,new FileOutputStream(path+"/mypdf.pdf"));
            RDVservice service = new RDVservice();
            //PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Zeineb Haffar/Desktop/projet.pdf"));
            document.open();
            Paragraph ph1 = new Paragraph("Veuillez trouver votre liste des rendez-vous \n  ");
            Paragraph ph3 = new Paragraph("Bienvenue");
            PdfPTable table = new PdfPTable(3);
            
            PdfPCell cell;
           
             service.readid("Salma").forEach(e
                    -> {
                table.addCell(e.getNom ());
                 
		table.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(String.valueOf(e.getNommed()));
                
                table.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(String.valueOf(e.getDate()));
            });
                         document.add(ph3);
            document.add(ph1);
            document.add(table);
            document.addAuthor("projet");
        } catch (Exception e) {
            System.out.println(e);
        }
                    document.close();
    }
}    
    
    

    



