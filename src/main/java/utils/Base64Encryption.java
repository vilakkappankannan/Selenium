package utils;

import com.vk.entity.Eligibility;
import com.vk.module.EligibilityRequest;

import java.util.Base64;

public class Base64Encryption {

    private EligibilityRequest eligibilityRequest;
    public static void main(String[] args) {

        String encode = "Test";
        String encrypt= Base64.getEncoder().encodeToString(encode.getBytes());
        System.out.println(encrypt);


        byte[] actualByte= Base64.getDecoder().decode(encrypt);
        String actualString= new String(actualByte);
        System.out.println(actualString);
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
