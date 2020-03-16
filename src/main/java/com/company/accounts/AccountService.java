package com.company.accounts;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AccountService {

  private AccountRepository accountRepository;

  public Account getSpecificAccount(Long id) {
    return accountRepository.getAccount(id);
  }

  public long addAccount(Account accountToAdd){
    return accountRepository.addAccount(accountToAdd);
  }

}
