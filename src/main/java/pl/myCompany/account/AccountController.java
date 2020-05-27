package pl.myCompany.account;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  private AccountService accountService;

  //TODO use ResponseEntity to return proper HTTP response codes

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  } //po usunieciu AccountController - nie chodzi, NullPointerExpception dla metody post bo brak adnotacji do serwisu

  @GetMapping("/accounts")
  public Iterable<Account> getAllAccounts() {
    return accountService.getAccounts(); //
    //TODO we should receive collection here please change servce method to return e.g list not iterable
  }

  @GetMapping("/account/{id}")
  public Account getSpecificAccount(@PathVariable Long id) {
    //TODO first we should check if account with provided id exist in db and return proper status if not
    return accountService.getAccount(id);
  }

  @PostMapping("/account")
  public Account addAccount(@RequestBody AccountRequest accountRequestToBeAdded) {
    Account accountForService = convertAccountRequestToAccount(accountRequestToBeAdded);
    return accountService.addAccount(accountForService);
  }

  @PutMapping("/changeAccountBalance/{id}")
  public void changeAccountBalance(@PathVariable Long id, @RequestBody BigDecimal newBalance) {
    //TODO update not change
    //TODO first we should check if account with provided id exist in db and return proper status if not
    accountService.changeAccountBalance(id, newBalance);
  }

  @PutMapping("/changeAccountDescription/{id}")
  public void changeAccountDescription(@PathVariable Long id, @RequestBody String newDescription) {
    //TODO update not change
    //TODO first we should check if account with provided id exist in db and return proper status if not
    accountService.changeAccountDescription(id, newDescription);
  }

  @PutMapping("/account/{id}")
  public void changeAccount(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
    //TODO update not change
    //TODO first we should check if account with provided id exist in db and return proper status if not
    Account accountForService = convertAccountRequestToAccount(accountRequest);
    accountService.changeAccount(id, accountForService);
  }

  @DeleteMapping("/account/{id}")
  public void deleteAccount(@PathVariable Long id) {  // why in request body only 2, not "id" = 2 ?
    //String returnStatement = "Account id " + id + " has been deleted."; // "Account id = " + id " has been deleted."?
    //TODO update not change
    //TODO first we should check if account with provided id exist in db and return proper status if not
    accountService.deleteAccount(id);
  }

  public Account convertAccountRequestToAccount(AccountRequest accountRequest) {
    return Account.builder()
        .description(accountRequest.getDescription())
        .sumOfMoney(accountRequest.getSumOfMoney())
        .build();
  }

}
