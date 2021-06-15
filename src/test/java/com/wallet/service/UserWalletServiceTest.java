package com.wallet.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.wallet.entity.User;
import com.wallet.entity.UserWallet;
import com.wallet.entity.Wallet;
import com.wallet.repository.UserWalletRepository;

@SpringBootTest
@ActiveProfiles("test")
class UserWalletServiceTest {

	@MockBean
	UserWalletRepository repository;
	
	@Autowired
	UserWalletService service;
	
	@BeforeEach
	public void setUp() {
		BDDMockito.given(repository.save(Mockito.any(UserWallet.class)))
			.willReturn(getMockUserWallet());
	}
	
	@Test
	void testSave() {
		UserWallet response = repository.save(getMockUserWallet());
		
		assertNotNull(response);
		assertNotNull(response.getUsers());
		assertNotNull(response.getWallet());
	}
	
	private UserWallet getMockUserWallet() {
		UserWallet uw = new UserWallet();
		uw.setUsers(new User());
		uw.setWallet(new Wallet());
		return uw;
	}

}
