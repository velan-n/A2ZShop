package com.a2zshop.microservices.productinfoservice.service;

import com.a2zshop.microservices.productinfoservice.exception.CategoryNotFoundException;
import com.a2zshop.microservices.productinfoservice.exception.DuplicateEntryException;
import com.a2zshop.microservices.productinfoservice.exception.SubCategoryNotFoundException;
import com.a2zshop.microservices.productinfoservice.model.Category;
import com.a2zshop.microservices.productinfoservice.model.SubCategory;
import com.a2zshop.microservices.productinfoservice.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private Logger logger= LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryService subCategoryService;

    @Override
    public Category addCategory(Category category) throws DuplicateEntryException {
        checkForDuplication(category);
        Category addedCategory = categoryRepository.save(category);
        logger.info("New category added");
        return addedCategory;
    }

    @Override
    public List<Category> retrieveAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category retrieveCategoryById(long categoryId) throws CategoryNotFoundException {
        logger.info("Finding category...");
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> {
                    logger.error("No category available with id : "+categoryId);
                    return new CategoryNotFoundException(categoryId);
                });
        logger.info("Category found");
        return category;
    }

    @Override
    public SubCategory addSubCategory(long categoryId, SubCategory subCategory) throws CategoryNotFoundException, DuplicateEntryException {
        Category category=retrieveCategoryById(categoryId);
        return subCategoryService.addSubCategory(category,subCategory);
    }

    @Override
    public List<SubCategory> retrieveAllSubCategoriesForCategory(long categoryId) throws CategoryNotFoundException, SubCategoryNotFoundException {
        Category category=retrieveCategoryById(categoryId);
        List<SubCategory> subCategories = category.getSubCategories();
        if(subCategories==null || subCategories.isEmpty()){
            throw new SubCategoryNotFoundException(category);
        }
        return subCategories;
    }

//    @Override
//    public SubCategory retrieveSubCategoryForCategory(long categoryId, long subCategoryId) throws CategoryNotFoundException, SubCategoryNotFoundException {
//        Category category=retrieveCategoryById(categoryId);
//        SubCategory subCategory = category.getSubCategories()
//                                        .stream()
//                                        .filter(subCat -> subCat.getSubCategoryId() == subCategoryId)
//                                        .findFirst()
//                                        .orElseThrow(() -> new SubCategoryNotFoundException(subCategoryId));
//        return subCategory;
//    }

    private void checkForDuplication(Category category) throws DuplicateEntryException {
        logger.info("Validating category...");
        boolean categoryExists=doesCategoryExists(category);

        if(categoryExists) {
            logger.error("Category already exists : "+ category.getCategoryName());
            throw new DuplicateEntryException(category.getClass().getSimpleName(), category.getCategoryName());
        }
        logger.info("Category validation successful");
    }

    @Override
    public boolean doesCategoryExists(Category category) {
          return categoryRepository.existsByCategoryNameIgnoreCase(category.getCategoryName());
    }

//    @Override
//    public Category findCategoryByName(String categoryName) throws CategoryNotfoundException {
//        Category category=categoryRepository.findByCategoryName(categoryName);
//        if(category==null)
//            throw new CategoryNotfoundException(categoryName);
//        return category;
//    }
}
