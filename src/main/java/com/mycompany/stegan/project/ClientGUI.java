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

    String imagePath = null;
    String secretPath = null;
    Long fileSize = 0L;

    public ClientGUI() throws MalformedURLException {
        setLayout(new FlowLayout());
        // set up a file picker component

        // access JFileChooser class directly
        fileChooser = new JFileChooser();

        // add the component to the frame
        add(btnElabora, BorderLayout.CENTER);
        add(btnImportaImmagine, BorderLayout.WEST);
        add(btnImportaSegreto, BorderLayout.CENTER);

        btnImportaImmagine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fileChooser.showOpenDialog(frame);
                add(fileChooser);
                imagePath = fileChooser.getSelectedFile().getAbsolutePath();
                File file = new File(imagePath);

                float fileSize = file.length();
                System.out.println("real file path = " + imagePath);
            }
        });
        btnImportaSegreto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(frame);
                add(fileChooser);
                secretPath = fileChooser.getSelectedFile().getAbsolutePath();

                System.out.println("secret path = " + secretPath);
            }
        });
        btnElabora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    SteganService.steganService(secretPath, imagePath, fileSize);
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
