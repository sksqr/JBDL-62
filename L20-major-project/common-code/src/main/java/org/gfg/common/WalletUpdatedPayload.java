package org.gfg.common;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WalletUpdatedPayload {

    private String userEmail;

    private Double balance;

    private String requestId;
}
