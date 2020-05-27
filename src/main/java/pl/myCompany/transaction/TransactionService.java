package pl.myCompany.transaction;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private TranscationRepository transcationRepository;

  @Autowired
  public TransactionService(TranscationRepository transcationRepository) {
    this.transcationRepository = transcationRepository;
  }

  public Iterable<Transaction> getTransactions() {
    //TODO please return collection here not iterable
    return transcationRepository.findAll();
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
}
