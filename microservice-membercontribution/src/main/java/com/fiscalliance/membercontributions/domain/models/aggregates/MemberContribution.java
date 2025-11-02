package com.example.spliteasybackend.membercontributions.domain.models.aggregates;

import com.example.spliteasybackend.contributions.domain.models.aggregates.Contribution;
import com.example.spliteasybackend.membercontributions.domain.models.commands.CreateMemberContributionCommand;
import com.example.spliteasybackend.membercontributions.domain.models.valueobjects.Status;
import com.example.spliteasybackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.example.spliteasybackend.iam.domain.model.aggregates.User;
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

    @Column(nullable = false)
    private Long contributionId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @Column
    private LocalDateTime pagadoEn;

    protected MemberContribution() {}

    public MemberContribution(Long contributionId, Long memberId, BigDecimal monto) {
        this.contributionId = contributionId;
        this.memberId = memberId;
        this.monto = monto;
        this.status = Status.PENDIENTE;
        this.pagadoEn = null;
    }

    public void update(CreateMemberContributionCommand command) {
        this.monto = command.monto();
        this.status = command.status();
        this.pagadoEn = command.pagadoEn();
    }
}
