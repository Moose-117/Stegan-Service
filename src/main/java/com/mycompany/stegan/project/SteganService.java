package com.mycompany.stegan.project;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class SteganService {

    public static void mergeSecret(File realImage, File secret) throws Exception {

        // below true flag tells OutputStream to append
        byte[] originalBytes = Files.readAllBytes(Paths.get(realImage.getAbsolutePath()));
        byte[] bytesToAppend = Files.readAllBytes(Paths.get(secret.getAbsolutePath()));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(originalBytes);
        outputStream.write(bytesToAppend);
        byte c[] = outputStream.toByteArray();

        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        ByteBuffer buf = ByteBuffer.wrap(c);

//        System.out.println(decoder.decode(buf));

        // CREATE IMAGE
//        BufferedImage imag = ImageIO.read(new ByteArrayInputStream(c));
        
//       imag.getData()
//        ImageIO.write(imag, "jpg", new File(System.getProperty("user.dir"), "meged.jpg"));

        Files.write(realImage.toPath(), c);
    }

    public static void retrieveSecret(File realImage, File modifiedImage) throws Exception {

        BufferedReader realBufferedImage = null;
        BufferedReader modifiedBufferedImage = null;
        String sCurrentLine;
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        realBufferedImage = new BufferedReader(new FileReader(realImage.getAbsolutePath()));
        modifiedBufferedImage = new BufferedReader(new FileReader(modifiedImage.getAbsolutePath()));
        while ((sCurrentLine = realBufferedImage.readLine()) != null) {
            list1.add(sCurrentLine);
        }
        while ((sCurrentLine = modifiedBufferedImage.readLine()) != null) {
            list2.add(sCurrentLine);
        }
        List<String> tmpList = new ArrayList<String>(list1);
        tmpList.removeAll(list2);
        System.out.println("Secret is:");
        tmpList = list2;
        tmpList.removeAll(list1);
        for (int i = 0; i < tmpList.size(); i++) {
            System.out.println(tmpList.get(i).toString()); //content from test2.txt which is not there in test.txt
        }
    }
}
