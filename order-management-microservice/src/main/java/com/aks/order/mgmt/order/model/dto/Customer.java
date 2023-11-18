package com.aks.order.mgmt.order.model.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable
{

	private final static long serialVersionUID = 6938865787322676118L;
	
	@Id
    public String id;
    public String firstName;
    public String lastName;
    public long phoneNumber;
    public String email;
    public Address address;
    public Double creditLimit;
    @Version
    public long version;
    @CreatedBy
    public String createdBy;
    @CreatedDate
    public Date createdDate;
    @LastModifiedBy
    public String updatedBy;
    @LastModifiedDate
    public Date updatedDate;
	public double getCreditLimit() {
		// TODO Auto-generated method stub
		return creditLimit;
	}
    
   
}
