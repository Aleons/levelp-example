package ru.levelp.example;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.levelp.example.dao.TicketsDAO;
import ru.levelp.example.web.ProdConfiguration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@Import(ProdConfiguration.class)
public class TestConfiguration {
    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

    @Bean
    public TicketsDAO getTicketsDAO() {
        return Mockito.mock(TicketsDAO.class);
    }
}
