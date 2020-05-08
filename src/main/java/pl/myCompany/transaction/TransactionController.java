package pl.myCompany.transaction;

import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController public class TransactionController {

  TransactionService transactionService;

  @GetMapping("/transactions")
  public Iterable<Transaction> getTransactions(){
    return transactionService.getTransactions();
  }

  @GetMapping("/transaction/{id}")
  public Optional<Transaction> getTranscation(@PathVariable long id){
    return transactionService.getTransaction(id);
  }

  @PutMapping("/transaction")
  public Transaction(TransactionRequest transactionRequest){
    return transactionRequest.createTransacation();
  }
}
