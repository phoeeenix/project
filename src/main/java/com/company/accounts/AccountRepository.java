package com.company.accounts;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

  //List<Account> findAll(); nie implementuje tego ponownie, ponieważ jest w interfejsie
  Account findById(long id); //TODO Question: Optional<T> findById(ID id)?
  List<Account> findByDescription(String description);
  //List<Account> findAllById(List<Account> listOfIds); //TODO Question: w parametrze ma być lista z id'kami?
  boolean existsById(long id);
  void delete(Account account);

  @Transactional
  @Modifying
  @Query("update Account account set account.sumOfMoney = :newBalance where account.id = :id")
  void updateAccountBalance(@Param("newBalance") BigDecimal newBalance, @Param("id") long id);

  @Transactional // inaczej wyrzuca error javax.persistence.TransactionRequiredException: Executing an update/delete query
  @Modifying
  @Query("update Account account set account.description = :newDescription where account.id = :id")
  void updateAccountDescription(@Param("newDescription") String description, @Param("id") long id);

/*  @Transactional
  @Modifying
  @Query("update Account account set account = :newAccount where account.id = :id")
  void updateAccount(@Param("id") long id, @Param("newAccount") Account newAccount);*/
}

/*
  @Query("select account from Account account where lower(account.name) like lower(:nameToFind) AND account.userId = :id")
  List<Account> findByNameIgnoreCaseAndUserId(@Param("nameToFind") String nameToFind, @Param("id") long id);

  Optional<Account> findByIdAndUserId(long id, long userId);
*/
