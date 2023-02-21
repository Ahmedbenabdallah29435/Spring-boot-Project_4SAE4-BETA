package com.example.techmasterpi.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
public class DeliveryDTO {

    private Integer deliveryid;

    private Long totaleprice;

    @Size(max = 255)
    private String servicedetail;

    @Size(max = 255)
    private String track;

    @Size(max = 255)
    private String state;

    private Integer userDelevery;

    private Integer relocationDelivery;

}
