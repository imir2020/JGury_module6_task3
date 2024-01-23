package dao;

import entity.Ranks;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.HibernateUtil;
import utils.TestDataImporter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class RanksRepositoryTest {
    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private final RanksRepository ranksRepository = new RanksRepository();

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
        List<Ranks> list = ranksRepository.findAll();
        assertTrue(list.size() > 0);
    }

    @Test
    public void findById() {
        Long rankId = 1L;
        Ranks rank = ranksRepository.findById(rankId).get();
        assertNotNull(rank);
    }

    @Test
    public void save() {

    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {
        Long rankId = 1L;
       // ranksRepository.delete(rankId);
    }
}
