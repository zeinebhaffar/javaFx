/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entité.RDV;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author Zeineb Haffar
 */
public class RDVservice {
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
       List<RDV> list=new ArrayList<>();
       
       try {
           ste=connection.createStatement();
           rs=ste.executeQuery(req);
           
           while(rs.next()){
               list.add(new RDV (rs.getInt("id"),rs.getString(2),rs.getString("nommed"),rs.getString("date")));
           }
       } catch (SQLException ex) {
           Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
       }
       return list;
   }
    public void UpdateRDVpst(){
    
        String req="update RDV set nom='roua' where nommed='sonia' ";
    
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
     public void DeleteRDVpst() {
        
          String req="DELETE From RDV WHERE nom='roua'";
    
      try {
            ste=connection.createStatement();
         int   rs=ste.executeUpdate(req);
            if ( rs >0)
            {
                System.out.println("le rendez-vous a tét bien supprimé  ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void RechercherRDV (String date){
         String req="SELECT * FROM RDV WHERE date ='"+date+"'";
         
             try {
             ste=connection.createStatement();
             rs=ste.executeQuery(req);
             rs.last();
             int nbrRow= rs.getRow();
             if (nbrRow!=0) {
               System.out.println("le rendez vous a été bien trouvé");
             }
             else{
                System.out.println("Rendez vous non trouvé "); 
             }
         
       } catch (SQLException ex) {
           Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
       }
             
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
                list.add(new RDV(rs.getInt("id"), rs.getString("nom"), rs.getString("nommed"), rs.getString("date")) );
            }
        } catch (SQLException ex) {
            Logger.getLogger(RDVservice.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;
   }
}
