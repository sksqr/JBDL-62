package org.gfg.common;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreatedPayload {
    private Long userId;
    private String userName;
    private String userEmail;
    private String requestId;
}
