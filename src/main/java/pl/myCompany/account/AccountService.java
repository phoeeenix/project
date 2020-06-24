package pl.myCompany.account;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public List<Account> getAccounts() {
    Iterable<Account> accountsIterable = accountRepository.findAll();
    Iterator<Account> accountIterator = accountsIterable.iterator();
    List<Account> accountsList = StreamSupport.stream(Spliterators.spliteratorUnknownSize(accountIterator, Spliterator.ORDERED), false)
        .collect(Collectors.toList());
    return accountsList;
  }

  public Optional<Account> getAccount(long id) {
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

  public void updateAccount(long id, Account newAccount) {
    newAccount.setId(id);
    accountRepository.save(newAccount);
  }

  public void deleteAccount(long id) {
    accountRepository.deleteById(id);
  }

}
