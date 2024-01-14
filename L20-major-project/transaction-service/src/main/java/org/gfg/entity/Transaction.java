package org.gfg.entity;

import jakarta.persistence.*;
import lombok.*;
import org.gfg.TransactionStatusEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String txnId;
    @Column(nullable = false)
    private Long fromUserId;
    @Column(nullable = false)
    private Long toUserId;
    @Column(nullable = false)
    private Double amount;
    private String remark;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatusEnum status;

    private String reason;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;


}
