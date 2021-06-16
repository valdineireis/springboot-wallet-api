package com.wallet.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
	private Long walletId;
	
	@NotNull(message = "Informe uma data")
	private LocalDateTime date;
	
	@NotNull(message = "Informe um tipo")
	private String type;
	
	@NotNull(message = "Informe uma descrição")
	@Length(min = 5, message = "A descrição deve ter no mínimo 5 caracteres")
	private String description;
	
	@NotNull(message = "Informe um valor")
	private BigDecimal value;
}
