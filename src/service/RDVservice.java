/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import static com.itextpdf.kernel.pdf.PdfName.r;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import entité.RDV;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class RDVservice {
    
//   RDVservice rdvs = new RDVservice();
   private Statement ste;
   private PreparedStatement pst;
   
   private ResultSet rs;
   
   private Connection connection;
   
   public RDVservice(){
       connection=DataSource.getInstance().getcnx();
   }
   public void ajouterRDV(RDV r){
   String req="insert into RDV (nom, nommed, date) values ('"+r.getNom()+"','"+r.getNommed()+"','"+r.getDate()+"')";
       try {
           ste=connection.createStatement();
           ste.executeUpdate(req);
       } catch (SQLException ex) {
           Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
       }
}
   public void ajouterRDVpst(RDV r){
       String req="insert into RDV (nom,nommed,date)values(?,?,?)";
       try {
           pst=connection.prepareStatement(req);
           pst.setString(1,r.getNom());
           pst.setString(2,r.getNommed());
           pst.setString(3,r.getDate());
           
           pst.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   public List<RDV> readAll(){
       String req="select * from RDV";
       ObservableList<RDV> list = FXCollections.observableArrayList();
      // List<RDV> list=new ArrayList<>();
       
       try {
           ste=connection.createStatement();
           rs=ste.executeQuery(req);
           
           while(rs.next()){
               list.add(new RDV (rs.getString("id"),rs.getString("nom"),rs.getString("nommed"),rs.getString("date")));
           }
       } catch (SQLException ex) {
           Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
       }
       return list;
   }
    public void UpdateRDVpst(String nom, String id){
    
        String req="update RDV set nom='" + nom + "' WHERE id='" + id +"' ";
    
      try {
            ste=connection.createStatement();
         int   rs=ste.executeUpdate(req);
            if ( rs >0)
            {
                System.out.println("le rendez-vous a été bien modifier");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
        }
  } 
     public void DeleteRDVpst(String nom) {
        
          String req="DELETE From RDV WHERE nom='" + nom +"'";
    
      try {
            ste=connection.createStatement();
         int   rs=ste.executeUpdate(req);
            if ( rs >0)
            {
                System.out.println("le rendez-vous a été bien supprimé  ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public ArrayList<RDV> readmy(String mychar) {
        ArrayList<RDV> form = new ArrayList<>();
       
          String req="SELECT * FROM RDV WHERE nom LIKE '%" + mychar + "%' ";
        try {
            ste=connection.createStatement();
              rs=ste.executeQuery(req);
              while (rs.next()) {
              form.add(new RDV( rs.getString("nom"), rs.getString("nommed"), rs.getString("date")) );
           
        }
        } catch (SQLException ex) {
            Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        return form;
    }
     public List<RDV> readid(String id){
       String req="select * from RDV WHERE nom = '" + id + "'  ";
       ObservableList<RDV> list = FXCollections.observableArrayList();
      // List<RDV> list=new ArrayList<>();
       
       try {
           ste=connection.createStatement();
           rs=ste.executeQuery(req);
           
           while(rs.next()){
               list.add(new RDV (rs.getString("id"),rs.getString("nom"),rs.getString("nommed"),rs.getString("date")));
           }
       } catch (SQLException ex) {
           Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
       }
       return list;
   }
    
     public List<RDV> TriRDV()
   {
       String req="SELECT * FROM RDV\n" +
"ORDER BY date DESC;";
       List<RDV> list= new ArrayList<>();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
                list.add(new RDV(rs.getString("id"), rs.getString("nom"), rs.getString("nommed"), rs.getString("date")) );
            }
        } catch (SQLException ex) {
            Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;
   }
}
