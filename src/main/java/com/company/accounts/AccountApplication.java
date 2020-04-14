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

  @Bean //TODO Question: z czym to dokładnie jest powiązane?
  public CommandLineRunner demo(AccountRepositoryJPA accountRepositoryJPA){
    return (args) -> {
      accountRepositoryJPA.save(new Account("PKO bank", BigDecimal.valueOf(1000)));
      accountRepositoryJPA.save(new Account("Millenium bank", BigDecimal.valueOf(800)));
      accountRepositoryJPA.save(new Account("Mbank", BigDecimal.valueOf(1500)));
      accountRepositoryJPA.save(new Account("Santander bank", BigDecimal.valueOf(2000)));

      log.info("List of all accounts:");
      for (Account account:accountRepositoryJPA.findAll()){
        log.info(account.toString());
      }

      log.info("----------------");

      Account accountWittId = accountRepositoryJPA.findById(1L);
      log.info("Account with id = " + accountWittId.getId() + " : " + accountWittId.toString());

      log.info("----------------");

      //Account accountWithDescription = accountRepositoryJPA.findByDescription("Mbank");
      log.info("Account found with description \"Mbank\" = ");
      accountRepositoryJPA.findByDescription("Mbank").forEach(foundAccount -> { //TODO ~?
        log.info(foundAccount.toString());
      });
    };
  }
}
