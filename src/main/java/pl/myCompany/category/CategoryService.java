package pl.myCompany.category;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.myCompany.account.Account;
import pl.myCompany.transaction.Transaction;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getCategories() {
    Iterable<Category> categoriesIterable = categoryRepository.findAll();
    Iterator<Category> categoryIterator = categoriesIterable.iterator();
    List<Category> categoriesList = StreamSupport.stream(Spliterators.spliteratorUnknownSize(categoryIterator, Spliterator.ORDERED), false)
        .collect(Collectors.toList());
    return categoriesList;
  }

  public Optional<Category> getCategoryById(long id) {
    return categoryRepository.findById(id);
  }

  public Category createCategory(Category categoryFromController) {
    return categoryRepository.save(categoryFromController);
  }

  public void deleteCategory(long id) {
    categoryRepository.deleteById(id);
  }

  public void changeCategoryName(long id, String newName) {
    categoryRepository.changeCategoryName(id, newName);
  }

  public void changeCategory(long id, Category categoryFromController) {
    categoryFromController.setId(id);
    categoryRepository.save(categoryFromController);
  }



}