package pl.myCompany.account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AccountController {

  private AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping("/accounts")
  public ResponseEntity<List<Account>> getAllAccounts() {
    return ResponseEntity.ok(accountService.getAccounts());
  }

  @GetMapping("/account/{id}")
  public ResponseEntity<Optional<Account>> getSpecificAccount(@PathVariable Long id) {
    ResponseEntity<Optional<Account>> response = checkIfAccountExists(id);
    if (response.equals(ResponseEntity.notFound().build())) {
      return response;
    }
    return ResponseEntity.ok(accountService.getAccount(id));
  }

  @PostMapping("/account")
  public ResponseEntity<Account> addAccount(@RequestBody AccountRequest accountRequestToBeAdded) {
    Account accountForService = convertAccountRequestToAccount(accountRequestToBeAdded);
    return ResponseEntity.ok(accountService.addAccount(accountForService));
  }

  @PutMapping("/updateAccountBalance/{id}")
  public ResponseEntity<?> updateAccountBalance(@PathVariable Long id, @RequestBody BigDecimal newBalance) {
    ResponseEntity<Optional<Account>> response = checkIfAccountExists(id);
    if (response.equals(ResponseEntity.notFound().build())) {
      return response;
    }
    accountService.changeAccountBalance(id, newBalance);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/updateAccountDescription/{id}")
  public ResponseEntity<?> updateAccountDescription(@PathVariable Long id, @RequestBody String newDescription) {
    ResponseEntity<Optional<Account>> response = checkIfAccountExists(id);
    if (response.equals(ResponseEntity.notFound().build())) {
      return response;
    }
    accountService.changeAccountDescription(id, newDescription);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/account/{id}")
  public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
    ResponseEntity<Optional<Account>> response = checkIfAccountExists(id);
    if (response.equals(ResponseEntity.notFound().build())) {
      return response;
    }
    Account accountForService = convertAccountRequestToAccount(accountRequest);
    accountService.changeAccount(id, accountForService);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/account/{id}")
  public ResponseEntity<?> deleteAccount(@PathVariable Long id) {  // why in request body only 2, not "id" = 2 ?
    //String returnStatement = "Account id " + id + " has been deleted."; // "Account id = " + id " has been deleted."?
    ResponseEntity<Optional<Account>> response = checkIfAccountExists(id);
    if (response.equals(ResponseEntity.notFound().build())) {
      return response;
    }
    accountService.deleteAccount(id);
    log.info("Account with id = {} has been deleted", id);
    return ResponseEntity.ok().build();
  }

  public Account convertAccountRequestToAccount(AccountRequest accountRequest) {
    return Account.builder()
        .description(accountRequest.getDescription())
        .balance(accountRequest.sumOfMoney) // I can not see getter balance, what should be done after changing contructor's field name?
        .build();
  }

  public ResponseEntity<Optional<Account>> checkIfAccountExists(long id) {
    Optional<Account> account = accountService.getAccount(id);
    if (account.isEmpty()) {
      log.info("Account with id = {} was not found", id);
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().build();
  }
}

