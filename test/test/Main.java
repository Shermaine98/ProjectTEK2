/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.accounts.Accounts;

/**
 *
 * @author Shermaine
 */
public class Main {

   
    public static void main (String args[])  {
        Accounts DAO = new Accounts();
        boolean x = DAO.emailAvailability("s");
        System.out.println(x);

    }
}
