package dao;

import entity.Employees;
import entity.Products;
import entity.Sales;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.HibernateUtil;
import utils.TestDataImporter;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class SalesRepositoryTest {

    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    private final SalesRepository salesRepository = new SalesRepository(sessionFactory);
    private final ProductsRepository productsRepository = new ProductsRepository();
    private final EmployeesRepository employeesRepository = new EmployeesRepository();


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
        List<Sales> list = salesRepository.findAll();
        list.forEach(System.out::println);
        assertTrue(list.size() > 0);

    }

    @Test
    public void findById() {
        Sales sales = salesRepository.findById(1L).get();
        assertTrue(sales!=null);
        System.out.println(sales + " sales");
    }

    @Test
    public void save() {
        Employees employee = employeesRepository.findById(4L).get();
        System.out.println(employee + " employee");
        Products product = productsRepository.findById(6L).get();
        Sales sales = Sales.builder()
                .product(product)
                .count(4L)
                .employee(employee)
                .dateSales(LocalDate.now())
                .build();
        salesRepository.save(sales);
    }

    @Test
    public void update() {
        Sales sales = salesRepository.findById(1L).get();
        sales.setCount(15L);
        salesRepository.update(sales);
        Sales beforeUpdate = salesRepository.findById(1L).get();
        assertEquals(sales, beforeUpdate);

    }

    @Test
    public void delete() {
        salesRepository.delete(2L);
    }
}
