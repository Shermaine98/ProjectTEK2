/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.forums.ForumDAO;
import java.text.ParseException;
import java.util.ArrayList;
import model.forums.Tags;

/**
 *
 * @author Shermaine
 */
public class Main {

   
    public static void main (String args[]) throws ParseException  {
        ForumDAO DAO = new ForumDAO();
        ArrayList<Tags> x = new ArrayList<Tags> ();
      //  x = DAO.a;
        System.out.println(x.get(0).getTag());

    }
}
