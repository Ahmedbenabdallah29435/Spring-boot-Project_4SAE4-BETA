package com.example.techmasterpi.domain;

import com.example.techmasterpi.model.NameRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private Integer phonenumber;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String adress;

    @Column
    private Integer cin;

    @Column
    private String profilpicture;

    @Column
    private String companyname;
    @Column
    private NameRole nameRole;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_userid"),
            inverseJoinColumns = @JoinColumn(name = "role_roleid")
    )
    private Set<Role> userRoleRoles;

    @OneToMany(mappedBy = "userRelocation")
    private Set<Relocation> userRelocationRelocations;

    @OneToMany(mappedBy = "userPost")
    private Set<Post> userPostPosts;

    @OneToMany(mappedBy = "userComplain")
    private Set<Complaint> userComplainComplaints;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_meeting",
            joinColumns = @JoinColumn(name = "user_userid"),
            inverseJoinColumns = @JoinColumn(name = "meeting_meetingid")
    )
    private Set<Meeting> userMeetingMeetings;

    @OneToMany(mappedBy = "planUser")
    private Set<ContractPlan> planUserContractPlans;

    @OneToOne
    @JoinColumn(name = "contract_user_id")
    private ContractPlan contractUser;

    @OneToMany(mappedBy = "userSell")
    private Set<SellerOffer> userSellSellerOffers;

    @OneToMany(mappedBy = "userContractsale")
    private Set<SellContract> userContractsaleSellContracts;

    @OneToMany(mappedBy = "userRentalcontract")
    private Set<RentalContract> userRentalcontractRentalContracts;

    @OneToMany(mappedBy = "userContartplan")
    private Set<ContractPlan> userContartplanContractPlans;

    @OneToMany(mappedBy = "userDelevery")
    private Set<Delivery> userDeleveryDeliverys;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
