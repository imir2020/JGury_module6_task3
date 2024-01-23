package dao;

import entity.Category;
import entity.Products;
import entity.Suppliers;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;
import utils.TestDataImporter;

import java.util.List;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class ProductsRepositoryTest {
    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private final ProductsRepository productsRepository = new ProductsRepository();
    private final CategoryRepository categoryRepository = new CategoryRepository();
    private final SuppliersRepository suppliersRepository = new SuppliersRepository();
    private final Logger log = LoggerFactory.getLogger("ProductsDaoTest");

    @BeforeAll
    static void initDb() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    static void finish() {
        sessionFactory.close();
    }

    @Test
    public void findAll() {
        List<Products> products = productsRepository.findAll();
        log.info("This is list of products from method findAll(): {}", products);
    }

    @Test
    public void findById() {
        Long id = 1L;
        Products product = productsRepository.findById(id).get();
        log.info("Object from method findBuId(): {}", product);
    }

    @Test
    public void save() {
        Products product = Products.builder()
                .name("Мясо")
                .count(125L)
                .priceForOne(500L)
                .build();
        Category category = categoryRepository.findById(3L).get();
        Suppliers supplier = suppliersRepository.findById(3L).get();
        product.setSupplier(supplier);
        product.setCategory(category);
        productsRepository.save(product);
        log.info("Object was saved in method save(): {}", product);
    }

    @Test
    public void update() {
        Products product = productsRepository.findById(1L).get();
        product.setCount(700L);
        productsRepository.update(product);
        log.info("Object was updated in method update(): {}", product);
    }

    @Test
    public void delete() {
        Long id = 4L;
        Products product = productsRepository.findById(id).get();
        productsRepository.delete(id);
        log.warn("Object was deleted in method delete():{}", product);
    }
}
