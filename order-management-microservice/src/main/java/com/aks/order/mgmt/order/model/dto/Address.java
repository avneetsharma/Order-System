
package com.aks.order.mgmt.order.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable
{
	private final static long serialVersionUID = -3930009017282385831L;

    public String addressLine1;
    public String addressLine2;
    public String city;
    public String state;
    public long zipCode;
    public String country;
}
