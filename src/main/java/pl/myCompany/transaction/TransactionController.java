package pl.myCompany.transaction;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping("/transactions")
  public ResponseEntity<Iterable<Transaction>> getTransactions() {
    return ResponseEntity.ok(transactionService.getTransactions());
  }

  @GetMapping("/transaction/{id}")
  public ResponseEntity<Optional<Transaction>> getTranscation(@PathVariable long id) {
    return ResponseEntity.ok(transactionService.getTransaction(id));
  }

  @PostMapping("/transaction")
  public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest transactionRequest) {
    Transaction newTransaction = convertTranscationRequestToTransaction(transactionRequest);
    return ResponseEntity.ok(transactionService.createTransaction(newTransaction));
  }

  private Transaction convertTranscationRequestToTransaction(TransactionRequest transactionRequest) {
    Transaction convertedTransaction = Transaction.builder().
        description(transactionRequest.getDescription())
        .categoryId(transactionRequest.getCategoryId())
        .localDate(transactionRequest.getLocalDate())
        .isPlanned(transactionRequest.isPlanned()).build(); //TODO isPlanned works?
    return convertedTransaction;
  }

  @PutMapping("/transaction/{id}")
  public ResponseEntity<?> updateTransaction(@PathVariable long id, @RequestBody TransactionRequest transactionRequest) {
    Transaction newTransaction = Transaction.builder()
        .description(transactionRequest.getDescription())
        .categoryId(transactionRequest.getCategoryId())
        .localDate(transactionRequest.getLocalDate())
        .isPlanned(transactionRequest.isPlanned())
        .build(); //TODO isPlanned works?
    transactionService.updateTransaction(id, newTransaction);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/transaction/{id}")
  public ResponseEntity<?> deleteTransaction(@PathVariable long id) {
    transactionService.deleteTransaction(id);
    return ResponseEntity.ok().build();
  }

}
