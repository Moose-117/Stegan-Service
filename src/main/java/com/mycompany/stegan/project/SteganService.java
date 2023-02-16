package com.mycompany.stegan.project;

import java.io.*;

public class SteganService {

    public static void steganService(String secretPath, String imagePath, Long imageSize) throws Exception {

        // ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\Users\\marco\\OneDrive\\Desktop\" && copy /B \"C:\\Users\\marco\\OneDrive\\Desktop\\eso1907a.jpg\" + \"C:\\Users\\marco\\OneDrive\\Desktop\\wrapper.rar\" crypted.jpg");
        // create param string
        
        
        String params = "\"cmd.exe\", " + "\"/c\"," + " \"cd \"C:/Users/marco/OneDrive/Desktop/" && " + "copy /B \"" + imagePath + "/" + "/" + secretPath + "/"" + " BBBBB.jpg"";
        
        System.out.println(params);
        ProcessBuilder builder = new ProcessBuilder(params);

        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
         
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
        }
        
        ReadBinaryFile.readBinaryFile(imageSize);
    }
}
