package com.company.accounts;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

//import static sun.nio.cs.Surrogate.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

  @Autowired
  private MockMvc mockmvc;
  @Autowired
  private AccountHelpers accountHelpers;

  //@Autowired
  private AccountRepository accountRepository;

  @Autowired
  public AccountControllerTest (AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  Logger logger = Logger.getLogger("AccountControllerTestLogger");

  protected String json(Object object) throws Exception { //konwertuje obiekt na json
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(object);
  }

  @Test
  public void testAllAccountsRequest() throws Exception {
    //given
    Account testAccount = new Account(1L, "cheapAccount", BigDecimal.valueOf(14L));
    Account testAccount2 = new Account(2L, "expensiveAccount", BigDecimal.valueOf(21L));

    //when
    mockmvc.perform(post("/account")
        .content(json(testAccount)) //TODO Q:Jak dodać accoountHelpers?
        //.param(testAccount)
        .contentType(MediaType.APPLICATION_JSON)
    );
    mockmvc.perform(post(
        "/account") //TODO czy jest sposob wyslac w 1 requescie, stworzyc taki czy nie ma sensu? - stworzyc metode przyjmujaca liste kont i je tworzaca
        .content(json(testAccount2))
        //.param(testAccount)
        .contentType(MediaType.APPLICATION_JSON)
    );

    mockmvc.perform(get("/accounts"))
        .andExpect(status().isOk())
        //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        //.andExpect(jsonPath("$").isEmpty())
        .andExpect(jsonPath("$[0].description", is(testAccount.getDescription())))
        .andExpect(jsonPath("$[1].description", is(testAccount2.getDescription()))) //TODO Q:czy musi byc id gdy tylko 1 obietk znajduje sie w mapie?
        .andExpect(jsonPath("$[0].sumOfMoney", is(testAccount.getSumOfMoney().intValue())))
        .andExpect(jsonPath("$[1].sumOfMoney", is(21)));
  }

  @Test
  public void testSpecificAccountRequest() throws Exception {
    //given
    Account testAccount = new Account(null, "cheapAccount", BigDecimal.valueOf(14L));

    //when
    String response = mockmvc.perform(post("/account")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(testAccount)))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString(); //andReturn zwraca to co metoda w perform, ustawiamy typ do zmiennej

    //then
    mockmvc.perform(get("/account/" + response))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.description", is(testAccount.getDescription())))
        .andExpect(jsonPath("$.sumOfMoney", is(14)))
        .andExpect(jsonPath("$.id", is(Integer.valueOf(response))))
    ;
  }

  @Test
  public void testChangeAccountBalance() throws Exception {
    //given
    Account testAccount = new Account(accountRepository.getId(), "mBank", BigDecimal.valueOf(100)); // czy potrzebne odwołanie do accountRepository?  addAccount ustawia id
    //TODO @BeforeEach - zrobic przed kazdym pusta baze, zeby przewidywac ID w tescie. Osobna klasa z accountHelper ktora ma stworoznych kilka kont, nie ma sensu tworzyc w beforeEach
    //when
    String response = mockmvc.perform(
        post("/account") // mockMVC działa jako stub, zatyczka, zaślepka? Wysyła reqesty - perform, ale nie są ona na stałe zapisywane w bazie, repo?
            .contentType(MediaType.APPLICATION_JSON)
            .content(json(testAccount)))  // jak przekazac parametr do metody? .param?
        .andExpect(status().isOk())
    .andReturn().getResponse().getContentAsString();
    logger.info("Account no. " + response + " has been created.");

    testAccount.setSumOfMoney(BigDecimal.valueOf(200));
    mockmvc.perform(put("/changeAccountBalance/" + response)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(testAccount.getSumOfMoney())))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

    //then
    mockmvc.perform(get("/account/" + response))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.sumOfMoney", is(200)))
    ;
  }

  @Test
  public void testDeleteAccount() throws Exception {
    //given
    Account testAccount = new Account(accountRepository.getId(), "Santader bank", BigDecimal.valueOf(50));

    //when
    String response = mockmvc.perform(
        post("/account") // mockMVC działa jako stub, zatyczka, zaślepka? Wysyła reqesty - perform, ale nie są ona na stałe zapisywane w bazie, repo?
            .contentType(MediaType.APPLICATION_JSON)
            .content(json(testAccount)))  // jak przekazac parametr do metody zamiast całego content? .param?  // gdy metoda delete z paramtetrem RequestBody wtedy informacja ze body is miising, ale test passed
        .andExpect(status().isOk())
    .andReturn().getResponse().getContentAsString();
    ;

    mockmvc.perform(delete("/account/" + response));
    logger.info("Account id no. " + response + " has been deleted successfully");
  }


/*mockMvc
        .perform(get(ACCOUNTS_SERVICE_PATH + "/" + accountId)
            .header(HttpHeaders.AUTHORIZATION, token))
        .andExpect(content().contentType(JSON_CONTENT_TYPE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(accountId.intValue())))
        .andExpect(jsonPath("$.name", is(account.getName())))
        .andExpect(jsonPath("$.balance", is(account.getBalance().toString())))`
        .andExpect(jsonPath("$.userId").doesNotExist());*/
}