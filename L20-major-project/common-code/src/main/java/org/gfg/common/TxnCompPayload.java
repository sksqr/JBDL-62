package org.gfg.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TxnCompPayload {
    private Long id;
    private Boolean success;
    private String reason;
    private String requestId;
}
