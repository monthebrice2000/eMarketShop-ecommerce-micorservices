package com.emarketshop.user_service.dto;

import java.io.Serializable;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto extends RepresentationModel<UserDto> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	
	private String firstName;
	
	private String lastName;
	
	private String imageUrl;
	
	private String email;
	
	private String phone;
	
	@JsonInclude(value = Include.NON_NULL)
	private Set<AddressDto> addressDtos;
	
	@JsonProperty("credential")
	@JsonInclude(value = Include.NON_NULL)
	private CredentialDto credentialDto;
	
}










