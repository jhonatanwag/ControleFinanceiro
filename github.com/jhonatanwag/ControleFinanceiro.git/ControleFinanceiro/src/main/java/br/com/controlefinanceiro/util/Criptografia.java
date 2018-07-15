package br.com.controlefinanceiro.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

    private static MessageDigest md = null;

    private static char[] hexcodes(byte[] text) {
        char[] hexoutput = new char[text.length * 2];

        for (int i = 0; i < text.length; ++i) {
            String hexstring = "00" + Integer.toHexString(text[i]);
            hexstring.toUpperCase().getChars(hexstring.length() - 2, hexstring.length(), hexoutput, i * 2);
        }

        return hexoutput;
    }

    /**
     * Metodo responsavel por receber uma String e retornar uma outra String
     * criptografada utilizando o algoritimo md5.
     * @param String
     * @return String
     */
    public static String criptografar(String pwd) {
        if (md != null) {
            return new String(hexcodes(md.digest(pwd.getBytes())));
        } else {
            return null;
        }
    }

    static {
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		System.out.println(Criptografia.criptografar("123".toLowerCase()));
	}
}
