package com.mercadolibre;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseData {
    private int statusCode;
    private BodyResponse body;

}
