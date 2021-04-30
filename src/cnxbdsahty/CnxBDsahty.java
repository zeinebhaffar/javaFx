/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnxbdsahty;

import entité.RDV;
import entité.medecin;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import service.RDVservice;
import service.medecinservice;
import utils.DataSource;

/**
 *
 * @author Zeineb Haffar
 */
public class CnxBDsahty extends Application {
    
    private Stage primaryStage;
    private Parent parentPage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hello World");
        
        parentPage = FXMLLoader.load(getClass().getResource("/views/Accueil.fxml"));
        Scene scene = new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(evt -> {
//            System.out.println("Hello World!");
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //JavaMailUtil.sendMail("zeineb.haffar@esprit.tn");

        launch(args);
//        DataSource ds1= DataSource.getInstance();
//        DataSource ds2= DataSource.getInstance();
//        DataSource ds3= DataSource.getInstance();
        RDV r1=new RDV("Haifa","Arij","2021-07-02");
        RDVservice rs=new RDVservice();
       //rs.ajouterRDV(r1);
       //rs.UpdateRDVpst();
       //rs.DeleteRDVpst();
       //rs.readAll().forEach(e->System.out.println(e));
       //rs.RechercherRDV("2030-08-09");
       //rs.TriRDV().forEach(e->System.out.println(e));
       //rs.readmy("12/05/2021").forEach(e->System.out.println(e));
       
       medecin m1=new medecin("zeineb","Ophtalmoloque","Tunis");
       medecinservice med =new medecinservice();
       //med.ajoutermedecin(m1);
       //med.readAll().forEach(e->System.out.println(e));
       //med.Updatemedecinpst();
       //med.Deletemedecinpst();
       //med.Recherchermedecin("Lotfi Bouachir");
    }
    
}
