/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stegan.project;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.io.ByteArrayInputStream;

public class ReadBinaryFile {

    public static void readBinaryFile(Long imageSize) {

        Charset utf8 = StandardCharsets.UTF_8;
        List<String> list = Arrays.asList("Line 1", "Line 2");

              try {
            Long i;
            byte[] content = Files.readAllBytes(Paths.get("C:\\Users\\marco\\OneDrive\\Desktop\\crypted.jpg"));
            ByteArrayInputStream fileArray = new ByteArrayInputStream(content);
            fileArray.skipNBytes(imageSize);
//            byte[] trimmedContent = new byte[content.length];
            fileArray.readNBytes((int) (content.length - imageSize));
//           if(Utf8Check.checkUTF8(fileArray.readAllBytes())){
//               System.out.println("********************STRING CONTENT =" + fileArray);
//           }

            System.out.println(new String(content));

            // for binary
            //System.out.println(Arrays.toString(content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
