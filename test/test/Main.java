/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.etl.DaoEtl;
import java.text.ParseException;
import java.util.ArrayList;
import model.forums.Tags;

/**
 *
 * @author Shermaine
 */
public class Main {

   
    public static void main (String args[]) throws ParseException  {
        DaoEtl DAOEtl = new DaoEtl();
        ArrayList<Tags> x = new ArrayList<Tags> ();
      //  x = DAO.a;
        System.out.println(DAOEtl.checkIntegrated(2016));

    }
}
