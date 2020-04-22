import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountApplicationOld {

  public static void main(String[] args) {
    SpringApplication.run(AccountApplicationOld.class, args);
  }

  private static final Logger log = Logger
      .getLogger("AccountLogger"); //TODO zły typ gdy = LoggerFactory.getLogger(AccountApplicationOld.class) jak w tutorialu JPA

/*  method created for tutorial reason
@Bean //TODO Question: z czym to dokładnie jest powiązane?
  public CommandLineRunner demo(AccountRepository accountRepository){
    return (args) -> {
      accountRepository.save(new Account("PKO bank", BigDecimal.valueOf(1000)));
      accountRepository.save(new Account("Millenium bank", BigDecimal.valueOf(800)));
      accountRepository.save(new Account("Mbank", BigDecimal.valueOf(1500)));
      accountRepository.save(new Account("Santander bank", BigDecimal.valueOf(2000)));

      log.info("List of all accounts:");
      for (Account pl.myCompany.account: accountRepository.findAll()){
        log.info(pl.myCompany.account.toString());
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
