package com.wallet.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.wallet.entity.Wallet;

@SpringBootTest
@ActiveProfiles("test")
class WalletRepositoryTest {
	
	@Autowired
	WalletRepository repository;
	
	@BeforeEach
	public void setUp() {
		
	}
	
	@AfterEach
	public void tearDown() {
		repository.deleteAll();
	}

	@Test
	void testSave() {
		Wallet wallet = new Wallet();
		wallet.setName("Carteira 01");
		wallet.setValue(BigDecimal.TEN);
		
		Wallet response = repository.save(wallet);
		
		assertNotNull(response);
	}

}
