package com.wallet.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class WalletItemDTO {

	private Long id;
	
	@NotNull(message = "Insira o id da carteira")
	@JsonProperty("wallet_id")
	private Long walletId;
	
	@NotNull(message = "Informe uma data")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date date;
	
	@NotNull(message = "Informe um tipo")
	@Pattern(regexp = "^(ENTRADA|SAÍDA)$", message = "Para o tipo somente são aceitos os valores ENTRADA ou SAÍDA")
	private String type;
	
	@NotNull(message = "Informe uma descrição")
	@Length(min = 5, message = "A descrição deve ter no mínimo 5 caracteres")
	private String description;
	
	@NotNull(message = "Informe um valor")
	private BigDecimal value;

	public WalletItemDTO() {
	}
	
	public WalletItemDTO(WalletItem wi) {
		this.id = wi.getId();
		this.date = wi.getDate();
		this.type = wi.getType().getValue();
		this.description = wi.getDescription();
		this.value = wi.getValue();
		this.walletId = wi.getWallet().getId();
	}

	public WalletItem toEntity() {
		Wallet w = new Wallet();
		w.setId(walletId);
		
		WalletItem wi = new WalletItem();
		wi.setId(id);
		wi.setDate(date);
		wi.setType(TypeEnum.getEnum(type));
		wi.setDescription(description);
		wi.setValue(value);
		wi.setWallet(w);
		
		return wi;
	}
}
