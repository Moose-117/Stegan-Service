package com.mycompany.stegan.project;
 
import com.mycompany.stegan.project.FileTypeFilter;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class JFilePicker extends JPanel {
    private String textFieldLabel;
    private String buttonLabel;
     
    private JLabel label;
    private JTextField textField;
    private JButton button;
     
    private JFileChooser fileChooser;
     
    private int mode;
    public static final int MODE_OPEN = 1;
    public static final int MODE_SAVE = 2;
     
    public JFilePicker(String textFieldLabel, String buttonLabel) {
        this.textFieldLabel = textFieldLabel;
        this.buttonLabel = buttonLabel;
         
        fileChooser = new JFileChooser();
         
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
 
        // creates the GUI
        label = new JLabel(textFieldLabel);
         
        textField = new JTextField(30);
        button = new JButton(buttonLabel);
         
        add(label);
        add(textField);
        add(button);
         
    }
     
    private String buttonActionPerformed(ActionEvent evt) {
        if (mode == MODE_OPEN) {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                 System.out.println("file path is" + fileChooser.getSelectedFile().getAbsolutePath());
                return fileChooser.getSelectedFile().getAbsolutePath();
            }
        } else if (mode == MODE_SAVE) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                 System.out.println("file path is" + fileChooser.getSelectedFile().getAbsolutePath());
                return fileChooser.getSelectedFile().getAbsolutePath();
            }
        }
        return null;
    }
 
    public void addFileTypeFilter(String extension, String description) {
        FileTypeFilter filter = new FileTypeFilter(extension, description);
        fileChooser.addChoosableFileFilter(filter);
    }
     
    public void setMode(int mode) {
        this.mode = mode;
    }
     
    public String getSelectedFilePath() {
        return textField.getText();
    }
     
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }

    String buttonActionPerformed() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}