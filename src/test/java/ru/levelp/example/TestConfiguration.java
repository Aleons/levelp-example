package ru.levelp.example;

import org.junit.Before;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.levelp.example.dao.EventsDAO;
import ru.levelp.example.dao.TicketsDAO;
import ru.levelp.example.dao.TicketsDAOImpl;
import ru.levelp.example.web.ProdConfiguration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = "ru.levelp.example",
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = Configuration.class),
                @ComponentScan.Filter(type = FilterType.REGEX,
                        pattern = "ru.levelp.example.+Impl")
})
public class TestConfiguration {
    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

    @Bean
    public TicketsDAO getTicketsDAO() {
        return Mockito.mock(TicketsDAO.class);
    }

    @Bean
    public EventsDAO getEventsDAO() {
        return Mockito.mock(EventsDAO.class);
    }
}
