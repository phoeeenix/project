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
import org.springframework.test.web.servlet.ResultActions;

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
  private AccountRepositoryOld accountRepositoryOld;

  @Autowired
  public AccountControllerTest (AccountRepositoryOld accountRepositoryOld) {
    this.accountRepositoryOld = accountRepositoryOld;
  }

  Logger logger = Logger.getLogger("AccountControllerTestLogger");

  protected String json(Object object) throws Exception { //konwertuje obiekt na json
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(object);
  }

  @Test
  public void testAllAccountsRequest() throws Exception {
    //given
    Account testPKOBankAccountWithId1 = AccountTestProvider.testPKOBankAccountWithId1();
    Account testMilleniumAccountWithId2 = AccountTestProvider.testMilleniumAccountWithId2();
    //when
    mockMVCperformPostAccount(testPKOBankAccountWithId1);  //TODO Test nie przechodzi
    /*mockmvc.perform(post("/account")
        .content(json(testPKOBankAccountWithId1))
        //.param(testPKOBankAccountWithId1) //TODO Question: za pomocą gettera sprawdzamy sam parametr?
        .contentType(MediaType.APPLICATION_JSON)
    ); //TODO 56-61 wyciagnac do prywatnej metody i wtedy wywolac ja 2,3,10 razy DONE
    mockmvc.perform(post(
        "/account") //TODO czy jest sposob wyslac w 1 requescie, stworzyc tak czy nie ma sensu? - stworzyc metode przyjmujaca liste kont i je tworzaca
        .content(json(AccountTestProvider.testMilleniumAccountWithId2()))
        //.param(testAccount) //TODO Spróbować przetestować sam parametr
        .contentType(MediaType.APPLICATION_JSON)
    );*/
    mockMVCperformPostAccount(testMilleniumAccountWithId2);

    mockmvc.perform(get("/accounts"))
        .andExpect(status().isOk())
        //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        //.andExpect(jsonPath("$").isEmpty())
        .andExpect(jsonPath("$[0].description", is(testPKOBankAccountWithId1.getDescription())))
        .andExpect(jsonPath("$[1].description", is(testMilleniumAccountWithId2.getDescription()))) //TODO Q:czy musi byc id gdy tylko 1 obietk znajduje sie w mapie?
        .andExpect(jsonPath("$[0].sumOfMoney", is(testPKOBankAccountWithId1.getSumOfMoney().intValue())))
        .andExpect(jsonPath("$[1].sumOfMoney", is(21)));
  }

  private ResultActions mockMVCperformPostAccount(Account accountToPost) throws Exception {//TODO Question: Dobry typ metody?
    return mockmvc.perform(post("/account")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(accountToPost))
    );
  }

  @Test
  public void testSpecificAccountRequest() throws Exception {
    //given
    Account testPKOBankAccountWithId1 = AccountTestProvider.testPKOBankAccountWithId1();

    //when
    String response = mockmvc.perform(post("/account")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(testPKOBankAccountWithId1)))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString(); //andReturn zwraca to co metoda w perform, ustawiamy typ do zmiennej

    //then
    mockmvc.perform(get("/account/" + response))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.description", is(testPKOBankAccountWithId1.getDescription())))
        .andExpect(jsonPath("$.sumOfMoney", is(14)))
        .andExpect(jsonPath("$.id", is(Integer.valueOf(response))))
    ;
  }

  @Test
  public void testChangeAccountBalance() throws Exception {
    //given
    //Account testAccount = new Account(accountRepositoryOld.getId(), "mBank", BigDecimal.valueOf(100)); //TODO Question: czy potrzebne odwołanie do accountRepositoryOld?  addAccount ustawia id
    Account testMbankAccountWithId3 = AccountTestProvider.testMbankAccountWithId3();
    //when
    String response = mockmvc.perform(
        post("/account") // TODO Question: mockMVC działa jako stub, zatyczka, zaślepka? Wysyła reqesty - perform, ale nie są ona na stałe zapisywane w bazie, repo?
            .contentType(MediaType.APPLICATION_JSON)
            .content(json(testMbankAccountWithId3)))
        .andExpect(status().isOk())
    .andReturn().getResponse().getContentAsString();
    logger.info("Account no. " + response + " has been created.");

    testMbankAccountWithId3.setSumOfMoney(BigDecimal.valueOf(200));
    mockmvc.perform(put("/changeAccountBalance/" + response)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(testMbankAccountWithId3.getSumOfMoney()))) //TODO Question: tak pobrać parametr czy przy pomocy .param ?
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
    Account testSantanderBankAccountWithId4 = AccountTestProvider.testSantanderBankAccountWithId4();

    //when
    String response = mockmvc.perform(
        post("/account")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json(testSantanderBankAccountWithId4)))  // TODO Question: gdy metoda delete z paramtetrem RequestBody wtedy informacja ze body is miising, ale test passed. Dlaczego?
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