package org.gfg.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TxnStatusDto {
    private String status;
    private String reason;
}
