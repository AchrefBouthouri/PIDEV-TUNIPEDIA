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

    public void Passe(String pass) {
        byte[] passBytes = pass.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(passBytes);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(passBytes);
            BigInteger number = new BigInteger(1, messageDigest);
            this.code = number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new Error("invalid JRE: have not 'MD5' impl.", e);
        }
    }

    public String codeGet() {
        return code;
    }

}
