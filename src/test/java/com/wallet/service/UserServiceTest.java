package com.wallet.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.wallet.entity.User;
import com.wallet.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {
	
	@MockBean
	UserRepository repository;
	
	@Autowired
	UserService service;
	
	@BeforeEach
	public void setUp() {
		BDDMockito.given(repository.findByEmailEquals(Mockito.anyString()))
			.willReturn(Optional.of(new User()));
	}

	@Test
	void testFindByEmail() {
		Optional<User> user = service.findByEmail("email@teste.com");
		
		assertTrue(user.isPresent());
	}

}
