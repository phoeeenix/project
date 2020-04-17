package com.company.accounts;

import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  //private AccountRepositoryOld accountRepositoryOld;
  @Autowired
  private AccountRepository accountRepository;

  /*@Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }*/

  public Collection<Account> getAccounts() {
    return accountRepository.findAll();
  }

  public Account getAccount(long id) {
    return accountRepository.findById(id);
  }

  public Account addAccount(Account accountToAdd) {
    return accountRepository.save(accountToAdd);
  }

  public void changeAccountBalance(long id, BigDecimal newBalance) {
    accountRepository.updateAccountBalance(newBalance, id);
  }

  public void changeDescriptionOfAccount(long id, String description) {
    accountRepository.updateAccountDescription(description, id);
  }

/*  public Account changeAccount(long id, Account accountToBeChanged) {
    return accountRepositoryOld.changeAccount(id, accountToBeChanged);
  }*/

  public void deleteAccount(long id) {
    accountRepository.deleteById(id);
  }

}
