package ru.levelp.example;


import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.levelp.example.web.EventsListBean;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class EventsListControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testList() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/events/all")
                        .header("User-Agent", "my-agent")
                .param("x", "")
        ).andExpect(status().isOk())
                .andExpect(view().name("event-list"))
                .andExpect(model().attribute("bean", CoreMatchers.notNullValue()))
                .andExpect(model().attribute("bean", new UserAgentMatcher()))
                .andReturn();
    }

    @Test
    public void testAddInvalid() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .param("x", "")
        ).andExpect(status().isOk())
                .andExpect(view().name("event-add"))
                .andExpect(model().hasErrors())
                .andReturn();
    }

    private static class UserAgentMatcher extends BaseMatcher<EventsListBean> {
        @Override
        public void describeTo(Description description) {
        }

        @Override
        public boolean matches(Object o) {
            if (!(o instanceof EventsListBean)) return false;
            EventsListBean bean = (EventsListBean) o;
            if (bean.getUserAgent() == null) return false;

            return bean.getUserAgent().equals("my-agent");
        }
    }
}
