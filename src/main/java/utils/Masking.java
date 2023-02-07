package utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Masking {

    public String mask(String toBeMasked) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < toBeMasked.length(); sb.append("*"), i++) ;
        return sb.toString();

      }
}


