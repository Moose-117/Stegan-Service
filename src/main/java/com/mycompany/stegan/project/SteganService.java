package com.mycompany.stegan.project;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Hex;

public class SteganService {

    public static void mergeSecret(File realImage, File secret) throws Exception {
        // below true flag tells OutputStream to append
        byte[] originalBytes = Files.readAllBytes(Paths.get(realImage.getAbsolutePath()));
        byte[] bytesToAppend = Files.readAllBytes(Paths.get(secret.getAbsolutePath()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(originalBytes);
        outputStream.write(bytesToAppend);
        byte c[] = outputStream.toByteArray();

        byte[] copiedFileArray = new byte[(int) (realImage.length() + secret.length())];
        InputStream is = null;

        FileOutputStream os = new FileOutputStream("output.jpg");
        os.write(c);

//        Files.write(realImage.toPath(), c);
    }

    public static String retrieveSecret(File modifiedImage) throws Exception {
        String segreto = "";
        try (InputStream inputStream = new FileInputStream(modifiedImage)) {
            byte[] buffer = new byte[2];
            byte[] pattern = {(byte) 0xFF, (byte) 0xD9}; // la sequenza di byte "1111111111011001"
            int bytesRead = 0;
            Boolean stringMatches = false;
            Boolean potenziale = false;
            StringBuilder stringBuilder = new StringBuilder();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                for (int i = 0; i < 2; i++) {
                    if (potenziale) {
                        if (buffer[i] == (byte) 0xD9) {
                            System.out.println("Match ok");
                            stringMatches = true;
                        } else {
                            potenziale = false;
                        }
                    }
                    if (buffer[i] == (byte) 0xFF) {
                        {
                            potenziale = true;
                        }
                    }
                    if (stringMatches) {
                        stringBuilder.append((char) buffer[i]);
                    }
                }
            }
            System.out.println("LA STRINGA SEGRETA E': " + stringBuilder.toString());
            segreto = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return segreto;
        }
    }
}
