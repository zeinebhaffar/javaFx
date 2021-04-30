/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;




import entité.medecin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Zeineb Haffar
 */
public class medecinservice {
    private Statement ste;
   private PreparedStatement pst;
   
   private ResultSet med;
   
   private Connection connection;
   
   public medecinservice(){
       connection=DataSource.getInstance().getcnx();
   }
   public void ajoutermedecin(medecin m){
   String req="insert into medecin (nommed, spécialité,région) values ('"+m.getNommed()+"','"+m.getSpécialité()+"','"+m.getRégion()+"')";
       try {
           ste=connection.createStatement();
           ste.executeUpdate(req);
       } catch (SQLException ex) {
           Logger.getLogger(medecinservice.class.getName()).log(Level.SEVERE, null, ex);
       }
}
   public List<medecin> readAll(){
       String req="select * from medecin";
       ObservableList<medecin> list = FXCollections.observableArrayList();
       //List<medecin> list=new ArrayList<>();
       
       try {
           ste=connection.createStatement();
           med=ste.executeQuery(req);
           
           while(med.next()){
               list.add(new medecin (med.getString("idmed"),med.getString("nommed"),med.getString("spécialité"),med.getString("région")));
           }
       } catch (SQLException ex) {
           Logger.getLogger(medecinservice.class.getName()).log(Level.SEVERE, null, ex);
       }
       return list;
   }
    
   public void Updatemedecinpst(String nommed , String spécialité ){
    
        String req="update medecin set nommed='" + nommed + "' WHERE spécialité='" + spécialité +"' ";
    
      try {
            ste=connection.createStatement();
         int   med=ste.executeUpdate(req);
            if ( med >0)
            {
                System.out.println("le medecin a été bien modifier");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
        }
  } 
   
    public void Deletemedecinpst(String nommed) {
        
          String req="DELETE From medecin WHERE nommed='" + nommed +"'";
    
      try {
            ste=connection.createStatement();
         int   med=ste.executeUpdate(req);
            if ( med >0)
            {
                System.out.println("le medecin est bien supprimé  ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(medecinservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public ArrayList<medecin> readmy(String mychar) {
        ArrayList<medecin> form = new ArrayList<>();
       
          String req="SELECT * FROM medecin WHERE région LIKE '%" + mychar + "%' ";
        try {
            ste=connection.createStatement();
              med=ste.executeQuery(req);
              while (med.next()) {
              form.add(new medecin( med.getString("nommed"), med.getString("région"), med.getString("Spécialité")) );
           
        }
        } catch (SQLException ex) {
            Logger.getLogger(medecinservice.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        return form;
    }
    
}

