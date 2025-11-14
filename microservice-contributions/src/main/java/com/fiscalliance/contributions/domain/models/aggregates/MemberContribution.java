package com.fiscalliance.contributions.domain.models.aggregates;


import com.fiscalliance.contributions.domain.models.valueobjects.Status;
import com.fiscalliance.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
public class MemberContribution extends AuditableAbstractAggregateRoot<MemberContribution> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contribution_id", nullable = false)
    private Contribution contribution;

   @Column(name = "member_id", nullable = false)
    private Long memberId;

    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime pagadoEn;

    protected MemberContribution() {}

    public MemberContribution(Contribution contribution, Long memberId, BigDecimal monto) {
        this.contribution = contribution;
        this.memberId = memberId;
        this.monto = monto;
        this.status = Status.PENDIENTE;
    }

    public void markAsPaid() {
        this.status = Status.PAGADO;
        this.pagadoEn = LocalDateTime.now();
    }

}
