/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Achref Bouthouri
 */
public class Md5 {
    
    private String code;
 
    public Md5(String md5) {
        Passe(md5);
        // TODO Auto-generated constructor stub
    }
 
    public void Passe(String pass){
        byte[] passBytes = pass.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(passBytes);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(passBytes);
            BigInteger number = new BigInteger(1, messageDigest);
            this.code= number.toString(16);
            } catch (NoSuchAlgorithmException e) {
                throw new Error("invalid JRE: have not 'MD5' impl.", e);
        }
    }
    public String codeGet(){
        return code;
    }
 
    
private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "1234567891234567".getBytes();
    
    public static void main(String args[]) throws Exception {
        Key key = generateKey();
       String encriptValue = encrypt("YOUR_SECRETE_KEY",key);
       decrypt(encriptValue,key);

   }
 
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }
    
    public static String encrypt(String valueToEnc, Key key) throws Exception {
    	 
        
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
  
        byte[] encValue = cipher.doFinal(valueToEnc.getBytes());
        //byte[] encryptedByteValue = new Base64().encode(encValue);
        //System.out.println("Encrypted Value :: " + new String(encryptedByteValue));
  
        //return new String(encryptedByteValue);
        return "";
    }
    
    public static String decrypt(String encryptedValue, Key key) throws Exception {
        // Key key = generateKey();
         Cipher cipher = Cipher.getInstance(ALGORITHM);
         cipher.init(Cipher.DECRYPT_MODE, key);
          
         //byte[] decodedBytes = new Base64().decode(encryptedValue.getBytes());
  
         //byte[] enctVal = cipher.doFinal(decodedBytes);
         
         //System.out.println("Decrypted Value :: " + new String(enctVal));
         //return new String(enctVal);
         return "";
     }
    
    }
 
    
