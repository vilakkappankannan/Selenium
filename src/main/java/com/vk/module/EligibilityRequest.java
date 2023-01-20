package com.vk.module;

import com.fasterxml.jackson.annotation.JsonInclude;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class EligibilityRequest {

    @Valid
    @NotNull
    @Size(min = 1, max = 5)
    private String cardNumber;

    @Valid
    @NotNull
    @Size(min = 1, max = 64)
    private int cvv;
}
