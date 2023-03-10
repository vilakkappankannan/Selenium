package com.vk.module;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EligibilityRequest {

    @Valid
    @NotNull
    @Size(min = 1, max = 10)
    private String requestId;

    private CardDetails cardDetails;

}
