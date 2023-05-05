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
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
abstract class ClientGUI extends JPanel implements ActionListener {

    public static JFrame frame = new JFrame("ClientGUI");
    private static JButton btnImportaImmagine = new JButton("importa immagine");
    private static JButton btnImportaSegreto = new JButton("importa segreto");
    private static JButton btnCamuffaSegreto = new JButton("camuffaSegreto");
    private static JButton btnEstraiSegreto = new JButton("estraiSegreto");
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
        add(btnEstraiSegreto, BorderLayout.CENTER);
        add(btnImportaImmagine, BorderLayout.WEST);
        add(btnImportaSegreto, BorderLayout.EAST);
        add(btnCamuffaSegreto, BorderLayout.WEST);

        btnImportaImmagine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fileChooser.showOpenDialog(frame);
                add(fileChooser);
                String imagePath = null;
                if ((fileChooser.getSelectedFile().getAbsolutePath()) != null) {
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
                if (null != fileChooser.getSelectedFile().getAbsolutePath()) {
                    secretPath = fileChooser.getSelectedFile().getAbsolutePath();
                    secretFile = new File(secretPath);
                }
                System.out.println("secret path = " + secretPath);
            }
        });

        btnCamuffaSegreto.addActionListener(new ActionListener() {
            String secret = "";

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SteganService.mergeSecret(realFile, secretFile);
                } catch (Exception ex) {
                    Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnEstraiSegreto.addActionListener(new ActionListener() {
            String secret = "";

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    secret = SteganService.retrieveSecret(realFile);
                } catch (Exception ex) {
                    Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(frame.getComponent(0), "Segreto = \n" + secret);
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
