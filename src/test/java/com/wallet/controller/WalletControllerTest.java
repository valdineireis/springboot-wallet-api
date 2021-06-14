package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.WalletDTO;
import com.wallet.entity.Wallet;
import com.wallet.service.WalletService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WalletControllerTest {

	private static final Long ID = 1L;
	private static final String NAME = "Carteira teste";
	private static final BigDecimal VALUE_TEN = BigDecimal.TEN;
	private static final String URL = "/wallet";
	
	@MockBean
	WalletService service;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	void testSave() throws Exception {
		BDDMockito.given(service.save(Mockito.any(Wallet.class)))
			.willReturn(getMockWallet());
		
		getMvcPerformWallet(ID, NAME, VALUE_TEN)
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.name").value(NAME))
		.andExpect(jsonPath("$.data.value").value(VALUE_TEN))
		;
	}
	
	@Test
	public void testSaveInvalidWalletNullName() throws Exception {
		getMvcPerformWallet(ID, null, BigDecimal.TEN)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors[0]").value("O nome não pode ser nulo"));
	}
	
	@Test
	public void testSaveInvalidWalletLengthName() throws Exception {
		getMvcPerformWallet(ID, "AA", BigDecimal.TEN)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors[0]").value("O nome deve ter no mínimo 3 caracteres"));
	}
	
	@Test
	public void testSaveInvalidWalletNullValue() throws Exception {
		getMvcPerformWallet(ID, NAME, null)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors[0]").value("Insira um valor para a carteira"));
	}
	
	private ResultActions getMvcPerformWallet(Long id, String name, BigDecimal value) throws Exception {
		return mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJsonPayload(id, name, value))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
	}

	private Wallet getMockWallet() {
		Wallet wallet = new Wallet();
		wallet.setId(ID);
		wallet.setName(NAME);
		wallet.setValue(VALUE_TEN);
		return wallet;
	}

	private String getJsonPayload(Long id, String name, BigDecimal value) throws JsonProcessingException {
		WalletDTO dto = new WalletDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setValue(value);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}

}
