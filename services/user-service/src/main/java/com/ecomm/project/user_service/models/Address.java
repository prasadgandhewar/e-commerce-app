package com.ecomm.project.user_service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address {
    private String addressLine1;
    private String postalCode;
    private String City;
    private String State;
    private String country;
}
