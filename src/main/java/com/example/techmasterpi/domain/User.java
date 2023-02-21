package com.example.techmasterpi.domain;

import com.example.techmasterpi.model.NameRole;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;



@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private int cin;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private int phone;
    private String address;
    @Nullable
    private String profilepicture;
    @Nullable
    private String companyname;
    @ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JsonIgnore
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getRoletype().toString()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



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




    @OneToMany(mappedBy = "userContractsale")
    private Set<SellContract> userContractsaleSellContracts;

    @OneToMany(mappedBy = "userRentalcontract")
    private Set<RentalContract> userRentalcontractRentalContracts;


    @OneToMany(mappedBy = "planUser")
    private Set<ContractPlan> planUser;



    @OneToMany(mappedBy = "userDelevery")
    private Set<Delivery> userDeleveryDeliverys;



}

