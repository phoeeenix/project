package pl.myCompany.category;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

  private CategoryService categoryService;
  private List<Long> parentCategoryIds = new ArrayList<>();
  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  private List<Long> categories = new ArrayList<>();

  @GetMapping("/categories")
  public Iterable<Category> getCategories() {
    return categoryService.getCategories();
  }

  @GetMapping("/category/{id}")
  public Category getCategoryById(@PathVariable long id) {
    return categoryService.getCategoryById(id);
  }

  @PostMapping("/category")
  public Category createCategory(@RequestBody CategoryRequest categoryRequest) {
    Category categoryForService = convertCategoryRequestToCategory(categoryRequest);
    return categoryService.createCategory(categoryForService);
  }

  @DeleteMapping("/category/{id}")
  public void deleteCategory(@PathVariable long id) {
    categoryService.deleteCategory(id);
  }

  @PutMapping("/changeCategoryName/{id}")
  public void changeCategoryName(@PathVariable long id, @RequestBody String newName) {
    categoryService.changeCategoryName(id, newName);
  }

  @PutMapping("/category/{id}")
  public void changeCategory(@PathVariable long id, @RequestBody CategoryRequest categoryRequest) {
    Category categoryForService = convertCategoryRequestToCategory(categoryRequest);
    categoryService.changeCategory(id, categoryForService);
  }

  public Category convertCategoryRequestToCategory(CategoryRequest categoryRequest) {

    Long parentCategoryId = categoryRequest.getParentCategoryId();
    categories = getParentCategoryIds();

    if (parentCategoryId != null && categories.contains(parentCategoryId)) {
      System.out.println("The given Id already exists in database");
      return null;
    } else
      return Category.builder().name(categoryRequest.getName())
          .parentCategory(parentCategoryId == null ? null : Category.builder().id(parentCategoryId).build())
          .build();

  }

  public List<Long> getParentCategoryIds() {
    //List<Long> categories = new ArrayList<>();
    //categories.clear();
    Iterable<Category> categories2 = getCategories();

    for (Category category : categories2) {
      categories.add(category.getId());
    }
    return categories;
  }

}
