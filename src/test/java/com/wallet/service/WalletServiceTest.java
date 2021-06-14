package com.wallet.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.wallet.entity.Wallet;
import com.wallet.repository.WalletRepository;

@SpringBootTest
@ActiveProfiles("test")
class WalletServiceTest {

	private static final String NAME = "Carteira teste";
	
	@MockBean
	WalletRepository repository;
	
	@Autowired
	WalletService service;
	
	@BeforeEach
	public void setUp() {
		BDDMockito.given(repository.save(Mockito.any(Wallet.class)))
			.willReturn(getMockWallet(NAME));
	}
	
	@Test
	void testSave() {
		Wallet response = repository.save(getMockWallet(NAME));
		
		assertNotNull(response);
		assertEquals(NAME, response.getName());
	}
	
	private Wallet getMockWallet(String name) {
		Wallet wallet = new Wallet();
		wallet.setName(name);
		wallet.setValue(BigDecimal.TEN);
		return wallet;
	}

}
