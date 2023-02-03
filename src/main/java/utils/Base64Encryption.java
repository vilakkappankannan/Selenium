package utils;

import java.util.Base64;

public class Base64Encryption {

    public String encrypt(String toBeEncrypted){

        String encrypt= Base64.getEncoder().encodeToString(toBeEncrypted.getBytes());
        System.out.println(encrypt);
        return encrypt;
    }
    public String decrypt(String toBeDecrypted) {

        byte[] actualByte= Base64.getDecoder().decode(toBeDecrypted);
        String decrypt= new String(actualByte);
        System.out.println(decrypt);
        return decrypt;
    }

//    public class Base64Encryption {
//        public static void main(String[] args) {
//            System.out.println("Test");
//            byte[] tt = null;
//            try {
//                tt = EncryptionEngine.encrypt("");
//            } catch (EncryptionException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Base64.getEncoder().encodeToString(tt));
//        }
//    }

}
