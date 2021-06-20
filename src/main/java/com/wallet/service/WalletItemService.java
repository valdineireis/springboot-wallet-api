package com.wallet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;

public interface WalletItemService {

	WalletItem save(WalletItem walletItem);

	Page<WalletItem> findBetweenDates(long walletId, Date start, Date end, int page);

	List<WalletItem> findByWalletAndType(long walletId, TypeEnum type);

	BigDecimal sumByWalletId(long walletId);
}
