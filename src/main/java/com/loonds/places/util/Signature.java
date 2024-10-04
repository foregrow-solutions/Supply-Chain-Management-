package com.loonds.places.util;

import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.Mac;

import javax.crypto.spec.SecretKeySpec;
import java.security.SignatureException;


public class Signature {
    private static final String HMAC_SHA256_ALGORITHM= "HmacSHA256";

    public static String calculateRFC2104HMAC(String data, String secret)throws SignatureException {
        String result;
        try{
            SecretKeySpec signinKey=new SecretKeySpec(secret.getBytes(), HMAC_SHA256_ALGORITHM);
            Mac mac=Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signinKey);

            byte[] rawHmac=mac.doFinal(data.getBytes());

            result= DatatypeConverter.printHexBinary(rawHmac).toLowerCase();

        }catch(Exception e){
            throw new SignatureException("Failed to generate HMAC: "+e.getMessage());
        }
        return result;
    }

}
