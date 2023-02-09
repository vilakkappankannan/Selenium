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
    public String mask(String toBeMasked) {

        toBeMasked = toBeMasked.replaceFirst(toBeMasked.substring(0, 4), "****");
        return(toBeMasked);
    }
}


