/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stegan.project;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 *
 * @author marco
 */
public class Utf8Check {
    
    public static boolean checkUTF8(byte[] barr){

        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        ByteBuffer buf = ByteBuffer.wrap(barr);

        try {
            decoder.decode(buf);

        }
        catch(CharacterCodingException e){
            return false;
        }

        return true;
    }
    
}
