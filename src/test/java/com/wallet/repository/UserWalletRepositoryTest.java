package com.wallet.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.wallet.entity.User;
import com.wallet.entity.UserWallet;
import com.wallet.entity.Wallet;

@SpringBootTest
@ActiveProfiles("test")
class UserWalletRepositoryTest {

	private User user;
	private Wallet wallet;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	WalletRepository walletRepository;
	
	@Autowired
	UserWalletRepository repository;
	
	@BeforeEach
	public void setUp() {
		User mockUser = getMockUser();
		this.user = userRepository.save(mockUser);
		
		Wallet mockWallet = getMockWallet();
		this.wallet = walletRepository.save(mockWallet);
	}
	
	@AfterEach
	public void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	void testSave() {
		UserWallet uw = new UserWallet();
		uw.setUsers(this.user);
		uw.setWallet(this.wallet);
		
		UserWallet response = repository.save(uw);
		
		assertNotNull(response);
	}
	
	private User getMockUser() {
		User user = new User();
		user.setId(1L);
		user.setName("Usuário 01");
		user.setEmail("usuario@teste.com");
		user.setPassword("123456");
		return user;
	}
	
	private Wallet getMockWallet() {
		Wallet wallet = new Wallet();
		wallet.setId(1L);
		wallet.setName("Carteira 01");
		wallet.setValue(BigDecimal.TEN);
		return wallet;
	}

}
