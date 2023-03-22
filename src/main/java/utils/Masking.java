package utils;

public class Masking {

// All mask
//    public String mask(String toBeMasked) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < toBeMasked.length(); sb.append("*"), i++) ;
//        return sb.toString();
//
//      }

    // Particular mask
    public String mask(String toBeMasked, int value) {

        String output = "";
        for (int i = 0; i < value; i++) {
            output = output + "*";
        }

        toBeMasked = toBeMasked.replaceFirst(toBeMasked.substring(0, value), output);

        return toBeMasked;

    }
}


