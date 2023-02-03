package utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Masking {
    private String cardNumber;
public Masking(String cardNumber){
    super();
    this.cardNumber=cardNumber;



    }
}
