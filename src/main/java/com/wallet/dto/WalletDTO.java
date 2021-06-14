package com.wallet.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.wallet.entity.Wallet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class WalletDTO {

	private Long id;
	@Length(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
	@NotNull(message = "O nome não pode ser nulo")
	private String name;
	@NotNull(message = "Insira um valor para a carteira")
	private BigDecimal value;
	
	public WalletDTO() {
	}
	
	public WalletDTO(Wallet wallet) {
		this.id = wallet.getId();
		this.name = wallet.getName();
		this.value = wallet.getValue();
	}
	
	public Wallet toEntity() {
		Wallet wallet = new Wallet();
		wallet.setId(this.id);
		wallet.setName(this.name);
		wallet.setValue(this.value);
		return wallet;
	}
}
