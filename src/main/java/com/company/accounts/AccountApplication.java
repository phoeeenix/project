package com.company.accounts;

import java.math.BigDecimal;
import java.util.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountApplication.class, args);
  }

  private static final Logger log = Logger.getLogger("AccountLogger"); //TODO zły typ gdy = LoggerFactory.getLogger(AccountApplication.class) jak w tutorialu JPA

/*  @Bean //TODO Question: z czym to dokładnie jest powiązane?
  public CommandLineRunner demo(AccountRepository accountRepository){
    return (args) -> {
      accountRepository.save(new Account("PKO bank", BigDecimal.valueOf(1000)));
      accountRepository.save(new Account("Millenium bank", BigDecimal.valueOf(800)));
      accountRepository.save(new Account("Mbank", BigDecimal.valueOf(1500)));
      accountRepository.save(new Account("Santander bank", BigDecimal.valueOf(2000)));

      log.info("List of all accounts:");
      for (Account account: accountRepository.findAll()){
        log.info(account.toString());
      }

      log.info("----------------");

      Account accountWittId = accountRepository.findByIdIn(1L);
      log.info("Account with id = " + accountWittId.getId() + " : " + accountWittId.toString());

      log.info("----------------");

      //Account accountWithDescription = accountRepository.findByDescription("Mbank");
      log.info("Account found with description \"Mbank\" = ");
      accountRepository.findByDescription("Mbank").forEach(foundAccount -> { //TODO ~?
        log.info(foundAccount.toString());
      });
    };
  }*/
}
