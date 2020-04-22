package pl.myCompany.account;

import java.math.BigDecimal;
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

  public Iterable<Account> getAccounts() {
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

  public void changeAccountDescription(long id, String description) {
    accountRepository.updateAccountDescription(description, id);
  }

  public void changeAccount(long id, Account newAccount) {
    newAccount.setID(id);
    accountRepository.save(newAccount);
  }

  public void deleteAccount(long id) {
    accountRepository.deleteById(id);
  }

}