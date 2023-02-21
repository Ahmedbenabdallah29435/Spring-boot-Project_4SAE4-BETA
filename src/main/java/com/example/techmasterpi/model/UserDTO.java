package com.example.techmasterpi.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
public class UserDTO {

    private Integer userid;

    @Size(max = 255)
    private String firstname;

    @Size(max = 255)
    private String lastname;

    private Integer phonenumber;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String password;

    @Size(max = 255)
    private String adress;

    private Integer cin;

    @Size(max = 255)
    private String profilpicture;

    @Size(max = 255)
    private String companyname;

    private List<Integer> userRoles;

    private List<Integer> userMeetings;

    private Integer contractUser;

}
