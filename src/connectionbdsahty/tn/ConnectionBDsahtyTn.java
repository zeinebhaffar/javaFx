/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionbdsahty.tn;

import entité.RDV;
import service.RDVservice;
import utils.DataSource;

/**
 *
 * @author Zeineb Haffar
 */
public class ConnectionBDsahtyTn {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //DataSource ds1 = DataSource.getInstance();
        //DataSource ds2 = DataSource.getInstance();
        //DataSource ds3 = DataSource.getInstance();
        RDV r1=new RDV("zakariya","rania","2030-08-09");
        RDVservice rs=new RDVservice();
       //rs.ajouterRDV(r1);
        //rs.ajouterRDVpst(r1);
       //rs.UpdateRDVpst();
       //rs.DeleteRDVpst();
       //rs.readAll().forEach(e->System.out.println(e));
       //rs.RechercherRDV("2025-08-09");
       rs.TriRDV().forEach(e->System.out.println(e));
    }
    
    
}
