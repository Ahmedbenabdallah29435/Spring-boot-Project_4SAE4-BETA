package com.example.techmasterpi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class ContractPlan {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;

    @Column
    private LocalDate contractDate;

    @Column
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_user_id")
    private User planUser;

    @OneToOne(mappedBy = "contractUser", fetch = FetchType.LAZY)
    private User contractUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_contartplan_id")
    private User userContartplan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_contract_plan_id")
    private Plan planContractPlan;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
