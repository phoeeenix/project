package pl.myCompany.transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private TranscationRepository transcationRepository;

  @Autowired
  public TransactionService(TranscationRepository transcationRepository) {
    this.transcationRepository = transcationRepository;
  }

  public List<Transaction> getTransactions() {
    Iterable<Transaction> transactionsIterable = transcationRepository.findAll();
    Iterator<Transaction> transactionIterator = transactionsIterable.iterator();
    //List<Transaction> transactionList = convertIterableToCollectionList(transactionIterator);
    List<Transaction> transactionList = StreamSupport.stream(Spliterators.spliteratorUnknownSize(transactionIterator, Spliterator.ORDERED), false)
        .collect(Collectors.toList());
    return transactionList;
  }

  public Optional<Transaction> getTransaction(long id) {
    return transcationRepository.findById(id);
  }

  public Transaction createTransaction(Transaction transactionFromController) {
    return transcationRepository.save(transactionFromController);
  }

  public void updateTransaction(long id, Transaction newTransaction) {
    Optional oldTransaction = transcationRepository.findById(id);  //TODO Question: Optional, żeby nie robić operacji na obiekcie z wartością NULL?
    newTransaction.setId(id);
    transcationRepository.save(newTransaction);
  }

  public void deleteTransaction(long id) {
    transcationRepository.deleteById(id);
  }

  private List<Transaction> convertIterableToCollectionList(Iterator<Transaction> transactions) {
    List<Transaction> transactionList = new ArrayList<>();
    transactions.forEachRemaining(transactionList::add);
    return transactionList;
  }
}
