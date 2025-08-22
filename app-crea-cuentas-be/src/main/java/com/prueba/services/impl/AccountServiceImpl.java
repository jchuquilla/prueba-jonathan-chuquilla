package com.prueba.services.impl;

import com.prueba.entities.Account;
import com.prueba.repositories.AccountRepo;
import com.prueba.repositories.GenericRepo;
import com.prueba.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends GenericServiceImpl<Account, Long> implements AccountService {

    private final AccountRepo accountRepo;

    @Override
    protected GenericRepo<Account, Long> getRepo() {
        return accountRepo;
    }
}
