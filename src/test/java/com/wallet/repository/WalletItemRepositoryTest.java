package com.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;

@SpringBootTest
@ActiveProfiles("test")
class WalletItemRepositoryTest {

	private static final LocalDateTime DATE = LocalDateTime.now();
	private static final TypeEnum TYPE = TypeEnum.EN;
	private static final String DESCRIPTION = "Conta de Luz";
	private static final BigDecimal VALUE = BigDecimal.valueOf(65);
	private Long savedWalletItemId = null;
	private Long savedWalletId = null;
	
	@Autowired
	WalletItemRepository repository;
	
	@Autowired
	WalletRepository walletRepository;
	
	@BeforeEach
	public void setUp() {
		Wallet wallet = new Wallet();
		wallet.setName("Carteira Teste");
		wallet.setValue(BigDecimal.valueOf(250));
		wallet = walletRepository.save(wallet);
		
		WalletItem item = new WalletItem(1L, wallet, DATE, TYPE, DESCRIPTION, VALUE);
		item = repository.save(item);

		savedWalletId = wallet.getId();
		savedWalletItemId = item.getId();
	}
	
	@AfterEach
	public void tearDown() {
		repository.deleteAll();
		walletRepository.deleteAll();
	}
	
	@Test
	void testSave() {
		Wallet wallet = new Wallet();
		wallet.setName("Carteira 1");
		wallet.setValue(BigDecimal.valueOf(500));
		
		walletRepository.save(wallet);
		
		WalletItem item = new WalletItem(1L, wallet, DATE, TYPE, DESCRIPTION, VALUE);
		
		WalletItem response = repository.save(item);
		
		assertNotNull(response);
		assertEquals(response.getDescription(), DESCRIPTION);
		assertEquals(response.getType(), TYPE);
		assertEquals(response.getValue(), VALUE);
		assertEquals(response.getWallet().getId(), wallet.getId());
	}
	
	@Test
	public void testSaveInvalidWalletItem() {
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			WalletItem item = new WalletItem(null, null, DATE, null, DESCRIPTION, null);
			repository.save(item);
		});
	}
	
	@Test
	public void testUpdate() {
		Optional<WalletItem> wi = repository.findById(savedWalletItemId);
		
		String description = "Descrição alterada";
		
		WalletItem changed = wi.get();
		changed.setDescription(description);
		
		repository.save(changed);
		
		Optional<WalletItem> updatedWalletItem = repository.findById(savedWalletItemId);
		
		assertEquals(description, updatedWalletItem.get().getDescription());
	}
	
	@Test
	public void deleteWalletItem() {
		Optional<Wallet> wallet = walletRepository.findById(savedWalletId);
		WalletItem wi = new WalletItem(null, wallet.get(), DATE, TYPE, DESCRIPTION, VALUE);
		
		repository.save(wi);
		
		repository.deleteById(wi.getId());
		
		Optional<WalletItem> response = repository.findById(wi.getId());
		
		assertFalse(response.isPresent());
	}

}
