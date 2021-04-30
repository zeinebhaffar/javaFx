/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entité.RDV;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import service.RDVservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * FXML Controller class
 *
 * @author Zeineb Haffar
 */
public class AjouterRDVController implements Initializable {

    @FXML
    private Button btn_enregistrer;
    @FXML
    private TextField nommed;
    @FXML
    private TextField nom;
//    @FXML
//    private TextField id;
    @FXML
    private DatePicker date;
    @FXML
    private Button retour;
    @FXML
    private Button actualiser;
    
    @FXML
    private Button btnDate;
    @FXML
    private Label lblDate;

   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO Integer.parseInt(id.getText()),
        btn_enregistrer.setOnAction(event -> {
        
//         try {
             RDV r = new RDV(nom.getText(), nommed.getText(),((TextField)date.getEditor()).getText());   
                RDVservice rs = new RDVservice();
                rs.ajouterRDV(r);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("RDV réservé avec succés!");
                alert.show();
            try {
                sendMail("zeineb.haffar@esprit.tn");
            } catch (MessagingException ex) {
                Logger.getLogger(AjouterRDVController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AjouterRDVController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
           actualiser.setOnAction(event->{
            try {
              
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/views/ajouterRDV.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterRDVController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AjouterRDVController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    });
    }    
    public static void sendMail(String recepient) throws SQLException,MessagingException,Exception {
        System.err.println("Preparing to send email");
    Properties properties = new Properties();
    
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable" ,"true");
    properties.put("mail.smtp.host" ,"smtp.gmail.com");
    properties.put("mail.smtp.port" ,"587");

    String myAccountEmail = "zeineb.haffar@esprit.tn";
    String password = "203JFT1625";
    
    Session session =Session.getDefaultInstance(properties,new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
        }
});
        Message message = prepareMessage(session, myAccountEmail, recepient) ;  
        
        Transport.send(message);
        System.err.println("Message send succesfully");
     
    }

    private static Message prepareMessage(Session session, String myAccountEmail,String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Rendez-vous sahty.tn");
            message.setText("Bonjour, \n votre rendez vous est réservé avec succés!");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(AjouterRDVController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private void onDate(ActionEvent event) {
        LocalDate lDate= date.getValue();
        lblDate.setText(formatDate(lDate.toString()));
    }
    
    public String formatDate (String Date){
        SimpleDateFormat sdf = null;
        Date d =null;
        try{
            sdf = new SimpleDateFormat("YY-MM-dd");
            d = sdf.parse(Date);
            sdf.applyPattern("EEEE d MMM YYYY");
    }catch(ParseException e){
        System.out.println(e);
    }
        return sdf.format(d);
        
    }
}
    

