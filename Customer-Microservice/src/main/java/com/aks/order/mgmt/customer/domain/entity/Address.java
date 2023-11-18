
package com.aks.order.mgmt.customer.domain.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
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
