package pl.myCompany.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public Iterable<Category> getCategories() {
    return categoryRepository.findAll();
  }

  public Category getCategoryById(long id) {
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
    categoryFromController.setName(categoryFromController.getName());
    categoryRepository.save(categoryFromController);
  }
}