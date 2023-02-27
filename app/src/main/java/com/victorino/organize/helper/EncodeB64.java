package com.victorino.organize.helper;


import android.util.Base64;

public class EncodeB64 {
    public static String encodeMail(String email){
        return Base64.encodeToString(email.getBytes(),Base64.DEFAULT).replaceAll("(\\n|\\r)","");
    }
    public  static String decodeMail(String encoded){
        return new String(Base64.decode(encoded,Base64.DEFAULT));
    }
}
