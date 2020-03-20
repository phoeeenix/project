package com.company.accounts;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;
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

  private String content = "";
  // private Map<Long, Account> mapForAccounts = new HashMap<>();
  private AtomicLong counter = new AtomicLong();
  @Autowired //Spring łączy ten element z Repo
  private AccountRepository accountRepository;
  private AccountService accountService;

  @GetMapping("/accounts")
  public Collection<Account> getAllAccounts() {
    // Account testAccount = new Account(1L, "cheapAccount", BigDecimal.valueOf(23L));
    // mapForAccounts.put(1L, testAccount); // dlaczego po wyniesieniu poza metode wyznacza bledy?
    // return mapForAccounts.values();
    return accountService.getAccounts();
  }

  @GetMapping("/account/{id}")
  public Account getSpecificAccount(@PathVariable Long id) {
    //Account testAccount = new Account(2L, "cheapAccount", BigDecimal.valueOf(14L));
    //mapForAccounts.put(2L, testAccount);
    return accountService.getAccount(id);
  }

  // dlaczego bez getterow wyskakiwal blad, przeciez nic nie pobieralem tylko tworzer nowe, metoda values w mapie
  // pobiera? nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class

  @PostMapping("/account")
  public long addAccount(@RequestBody AccountRequest accountRequest) { // zrobić accountRequest, zeby przychodzil request a nie obiekt Account
    //metoda ktora bedzie konwertowac account Reqest na Account i ja tutaj zaimplementowac
    return accountService.addAccount(accountRequest);
  }

  @PutMapping("/changeAccountBalance/{id}")
  public Account changeAccountBalance(@PathVariable Long id, @RequestBody BigDecimal newBalance) {
    //mapForAccounts.put(id, new Account(id, "cheapAccount", BigDecimal.valueOf(10L)));
    // mapForAccounts.get(id).setSumOfMoney(moneyToAdd); //add(moneyToAdd) "error": "Bad Request","message": "JSON parse error: Cannot deserialize value of type `java.lang.Long` from String \"id\": not a valid Long value; nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.lang.Long` from String \"id\": not a valid Long value\n at [Source: (PushbackInputStream); line: 1, column: 2]",;
    //return mapForAccounts.get(id);
    return accountService.changeAccountBalance(id, newBalance);
  }

  @PutMapping("/changeDescriptionOfAccount/{id}")
  public Account changeDescriptionOfAccount(@RequestBody Long id,
      @RequestBody String description) { // why @RequestBody Long Id return error? "JSON parse error: Cannot deserialize instance of `java.lang.Long` out of START_OBJECT token; nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.lang.Long` out of START_OBJECT token\n at [Source: (PushbackInputStream); line: 1, column: 4]",
    //mapForAccounts.put(id, new Account(id, "cheapAccount", BigDecimal.valueOf(20L)));
    //mapForAccounts.get(id).setDescription(description);
    //return mapForAccounts.get(id);
    return accountService.changeDescriptionOfAccount(id, description);
  }

  @DeleteMapping("/account/{id}")
  public Account deleteAccount(@PathVariable Long id) {  // why in request body only 2, not "id" = 2 ?
    //mapForAccounts.put(id, new Account(id, "cheapAccount", BigDecimal.valueOf(20L)));
    //mapForAccounts.remove(id);
    //String returnStatement = "Account id " + id + " has been deleted."; // "Account id = " + id " has been deleted."?
    //return returnStatement;
    //return accountRepository.deleteAccount(id);
    return accountService.deleteAccount(id);
  }

  //DOJSC DO MOMENTU ZEBY W ACCOUNTCONTROLLER WYRZUC REPO I ZAIMPLEMENTOWAC SERVICE!!!!!
  //DODAC METODE ZMIEN KONTO, ABY W PARAMETRZE WYSŁAĆ CAŁY REQUEST Z ACCOUNT, A NIE POJEDYNCZE PARAMETRY JAK OPIS, SUMA PIENIEDZY
  //WSTRZYKIWANIE PRZEZ KONSTRUKTOR - POCZYATAC I SPROBOWAC ZASTGOSOWAC W APCE - CHODZI o to zeby @Autowired zastapic konstruktorem

 /* @GetMapping("/getAllAccounts")
  public Account getAllAccounts() {
   return new Account(1L, "cheapAccount", BigDecimal.valueOf(23L));
   // List<Account> listWithAllAccount = mapForAccounts.
    //return mapForAccounts.values();
  }
*/
}
