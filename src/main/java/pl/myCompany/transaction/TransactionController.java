package pl.myCompany.transaction;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {


  private TransactionService transactionService;

  @Autowired //TODO Question: działa bez tej adnotacji, czy potrzebna?
  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping("/transactions")
  public Iterable<Transaction> getTransactions() {
    return transactionService.getTransactions();
  }

  @GetMapping("/transaction/{id}")
  public Optional<Transaction> getTranscation(@PathVariable long id) {
    return transactionService.getTransaction(id);
  }

  @PostMapping("/transaction")
  public Transaction createTransaction(@RequestBody TransactionRequest transactionRequest) {
    Transaction newTransaction = convertTranscationRequestToTransaction(transactionRequest);
    return transactionService.createTransaction(newTransaction);
  }

  private Transaction convertTranscationRequestToTransaction(TransactionRequest transactionRequest) {
    Transaction convertedTransaction = Transaction.builder().description(transactionRequest.getDescription())
        .categoryId(transactionRequest.getCategoryId()).localDate(transactionRequest.getLocalDate()).isPlanned(transactionRequest.isPlanned()).build(); //TODO isPlanned works?
    return convertedTransaction;
  }

  @PutMapping("/transaction/{id}")
  public void modifyTransaction(@PathVariable long id, @RequestBody TransactionRequest transactionRequest) {
    Transaction newTransaction = Transaction.builder().description(transactionRequest.getDescription())
        .categoryId(transactionRequest.getCategoryId()).localDate(transactionRequest.getLocalDate()).isPlanned(transactionRequest.isPlanned())
        .build(); //TODO isPlanned works?
    transactionService.modifyTransaction(id, newTransaction);
  }

  @DeleteMapping("/transaction/{id}")
  public void deleteTransaction(@PathVariable long id) {
    transactionService.deleteTransaction(id);
  }



}
