package com.wallet.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wallet.entity.User;
import com.wallet.entity.UserWallet;
import com.wallet.entity.Wallet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserWalletDTO {

	private Long id;
	
	@NotNull(message = "Informe o id do usuário")
	@JsonProperty("users_id")
	private Long usersId;
	
	@NotNull(message = "Informe o id da carteira")
	@JsonProperty("wallet_id")
	private Long walletId;
	
	public UserWalletDTO() {
	}
	
	public UserWalletDTO(UserWallet uw) {
		this.setId(uw.getId());
		this.setUsersId(uw.getUsers().getId());
		this.setWalletId(uw.getWallet().getId());
	}
	
	public UserWallet toEntity() {
		UserWallet uw = new UserWallet();
		uw.setId(this.getId());
		
		User user = new User();
		user.setId(this.getUsersId());
		
		Wallet wallet = new Wallet();
		wallet.setId(this.getWalletId());
		
		uw.setUsers(user);
		uw.setWallet(wallet);
		
		return uw;
	}
}
