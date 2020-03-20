package com.company.accounts;

import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  //@Autowired //niepotrzebny?
  private AccountRepository accountRepository;

  public Collection<Account> getAccounts() {
    return accountRepository.getAccounts();
  }

  public Account getAccount(long id) {
    return accountRepository.getAccount(id);
  }

  public long addAccount(Account accountToAdd) {
    return accountRepository.addAccount(accountToAdd);
  }

  public long addAccount(AccountRequest accountRequestToAdd) {
    return accountRepository.addAccount(accountRequestToAdd);
  }

  public Account changeAccountBalance(long id, BigDecimal newBalance) {
    return accountRepository.changeAccountBalance(id, newBalance);
  }

  public Account changeDescriptionOfAccount(long id, String description) {
    return accountRepository.changeDescriptionOfAccount(id, description);
  }

  public Account deleteAccount(long id) {
    return accountRepository.deleteAccount(id);
  }

}
