/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stegan.project;

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author marco
 */
public class main {

    public static void main(String args[]) //static method  
    {
        
        
        try {
            ClientGUI.createAndShowGUI();
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
