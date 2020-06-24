package pl.myCompany.account;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

  //List<Account> findAllById(List<Account> listOfIds); //TODO Question: w parametrze ma być lista z id'kami?

  @Transactional
  @Modifying
  @Query("update Account account set account.balance = :newBalance where account.id = :id")
  void updateAccountBalance(@Param("newBalance") BigDecimal newBalance, @Param("id") long id);

  @Transactional // inaczej wyrzuca error javax.persistence.TransactionRequiredException: Executing an update/delete query
  @Modifying
  @Query("update Account account set account.description = :newDescription where account.id = :id")
  void updateAccountDescription(@Param("newDescription") String description, @Param("id") long id);

/*  @Transactional
  @Modifying
  @Query("update Account pl.myCompany.account set pl.myCompany.account = :newAccount where pl.myCompany.account.id = :id")
  void updateAccount(@Param("id") long id, @Param("newAccount") Account newAccount);*/
}

/*
  @Query("select pl.myCompany.account from Account pl.myCompany.account where lower(pl.myCompany.account.name) like lower(:nameToFind) AND pl.myCompany.account.userId = :id")
  List<Account> findByNameIgnoreCaseAndUserId(@Param("nameToFind") String nameToFind, @Param("id") long id);

  Optional<Account> findByIdAndUserId(long id, long userId);
*/
