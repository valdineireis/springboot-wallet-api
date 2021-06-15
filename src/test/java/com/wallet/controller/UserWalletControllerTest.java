package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.User;
import com.wallet.entity.UserWallet;
import com.wallet.entity.Wallet;
import com.wallet.service.UserWalletService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserWalletControllerTest {

	private static final Long ID = 1L;
	private static final Long USER_ID = 2L;
	private static final Long WALLET_ID = 3L;
	private static final String URL = "/user-wallet";
	
	@MockBean
	UserWalletService service;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	void testSave() throws Exception {
		BDDMockito.given(service.save(Mockito.any(UserWallet.class)))
			.willReturn(getMockUserWallet());
		
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJsonPayload())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.users_id").value(USER_ID))
		.andExpect(jsonPath("$.data.wallet_id").value(WALLET_ID))
		;
	}
	
	private UserWallet getMockUserWallet() {
		User user = new User();
		user.setId(USER_ID);
		
		Wallet wallet = new Wallet();
		wallet.setId(WALLET_ID);
		
		UserWallet uw = new UserWallet();
		uw.setId(ID);
		uw.setUsers(user);
		uw.setWallet(wallet);
		return uw;
	}
	
	private String getJsonPayload() throws JsonProcessingException {
		UserWalletDTO dto = new UserWalletDTO();
		dto.setId(ID);
		dto.setUsersId(USER_ID);
		dto.setWalletId(WALLET_ID);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}

}
