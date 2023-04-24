/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stegan.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import com.mycompany.stegan.project.SteganService;

/**
 *
 * @author marco
 */
abstract class ClientGUI extends JPanel implements ActionListener {

    public static JFrame frame = new JFrame("ClientGUI");

    private static JButton btnImportaImmagine = new JButton("importa immagine");
    private static JButton btnImportaSegreto = new JButton("importa segreto");

    private static JButton btnElabora = new JButton("Elabora");
    private JFileChooser fileChooser;

    private File realFile = null;
    private File secretFile = null;
    
    public ClientGUI() throws MalformedURLException {
        setLayout(new FlowLayout());
        // set up a file picker component

        // access JFileChooser class directly
        fileChooser = new JFileChooser();

        // add the component to the frame
        add(btnElabora, BorderLayout.CENTER);
        add(btnImportaImmagine, BorderLayout.WEST);
        add(btnImportaSegreto, BorderLayout.EAST);

        btnImportaImmagine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fileChooser.showOpenDialog(frame);
                add(fileChooser);
                String imagePath = null;
                if((fileChooser.getSelectedFile().getAbsolutePath()) != null){
                imagePath = fileChooser.getSelectedFile().getAbsolutePath();
                realFile = new File(imagePath);
                }
                System.out.println("Image path = " + imagePath);
            }
        });
        btnImportaSegreto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(frame);
                add(fileChooser);
                
                String secretPath = null;
                if(null != fileChooser.getSelectedFile().getAbsolutePath()){
                secretPath = fileChooser.getSelectedFile().getAbsolutePath();
                secretFile = new File(secretPath);
                }
                System.out.println("secret path = " + secretPath);
            }
        });
        btnElabora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    SteganService.retrieveSecret(realFile, secretFile);
                } catch (Exception ex) {
                    Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public static void createAndShowGUI() throws MalformedURLException {

        frame.setMinimumSize(new Dimension(500, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ClientGUI() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });

        frame.setSize(500, 400);
        frame.setVisible(true);
    }
}
