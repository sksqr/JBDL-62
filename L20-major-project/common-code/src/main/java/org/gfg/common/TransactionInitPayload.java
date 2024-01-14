package org.gfg.common;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionInitPayload {
    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private Double amount;
    private String remark;
    private String requestId;
}
