package com.wallet.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wallet.entity.User;
import com.wallet.util.Bcrypt;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(value = Include.NON_NULL)
public class UserDTO {

	private Long id;
	
	@Length(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres")
	private String name;
	
	@NotNull
	@Length(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
	private String password;
	
	@Email(message = "Email inválido")
	private String email;
	
	public UserDTO() {
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}
	
	public User toEntity() {
		User user = new User();
		user.setId(this.id);
		user.setName(this.name);
		user.setPassword(Bcrypt.getHash(this.password));
		user.setEmail(this.email);
		return user;
	}
	
}
