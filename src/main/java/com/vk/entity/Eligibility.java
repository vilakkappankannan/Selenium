package com.vk.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Eligibility")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eligibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long provisionId;
    private String cardNumber;
    private String cvv;

}
