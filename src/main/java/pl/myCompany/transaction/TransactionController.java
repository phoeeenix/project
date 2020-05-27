package pl.myCompany.transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
public class TransactionController {

  private TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping("/transactions")
  public ResponseEntity<List<Transaction>> getTransactions() {
    Iterable<Transaction> transactionsIterable = transactionService.getTransactions();
    Iterator<Transaction> transactionIterator = transactionsIterable.iterator();
    List<Transaction> transactionList = convertIterableToCollectionList(transactionIterator);
    //TODO this iterable to list conversion should happen in service, there is also more cleaner way to change iterable to list using streams
    return ResponseEntity.ok(transactionList);
  }

  private List<Transaction> convertIterableToCollectionList(Iterator<Transaction> transactions) {
    List<Transaction> transactionList = new ArrayList<>();
    transactions.forEachRemaining(transactionList::add);
    return transactionList;
  }

  @GetMapping("/transaction/{id}")
  public ResponseEntity<Optional<Transaction>> getTranscation(@PathVariable long id) {
    Optional<Transaction> transaction = transactionService.getTransaction(id);
    if (!transaction.isPresent()) {
      //TODO please use isEmpty insted of isPresent. Like IJ suggest
      log.info("Transaction with id = {} was not found", id);
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(transaction);
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
        .isPlanned(transactionRequest.isPlanned()).build();
    //TODO Question: isPlanned does not works, why there is no getter for that boolean type parameter? The name of the field in get method is "planned", why?
    //There is getter for boolean field maybe is not named get.... but like in this case "planned". You can always use Refactor-> Delombok to check how lombok named methods etc
    return convertedTransaction;
  }

  @PutMapping("/transaction/{id}")
  public ResponseEntity<?> updateTransaction(@PathVariable long id, @RequestBody TransactionRequest transactionRequest) {
    //TODO please first check if id exist in db and return proper code if not
    Transaction newTransaction = Transaction.builder()
        .description(transactionRequest.getDescription())
        .categoryId(transactionRequest.getCategoryId())
        .localDate(transactionRequest.getLocalDate())
        .isPlanned(transactionRequest.isPlanned())
        .build(); //TODO same as 64
    transactionService.updateTransaction(id, newTransaction);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/transaction/{id}")
  public ResponseEntity<?> deleteTransaction(@PathVariable long id) {
    Optional<Transaction> transaction = transactionService.getTransaction(id);
    if (!transaction.isPresent()) {
      log.info("No able to delete transaction with id = {} because it was not found", id);
      return ResponseEntity.notFound().build();
    }
    transactionService.deleteTransaction(id);
    return ResponseEntity.ok().build();
  }

}
