package ru.levelp.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.levelp.example.dao.EventsDAO;
import ru.levelp.example.model.Event;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EventsListController {
    @Autowired
    private EventsDAO eventsDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/events/all")
    public String eventsList(@RequestHeader("User-Agent") String userAgent, ModelMap model) {
        List<Event> events = eventsDAO.findEvents(new Date());

        EventsListBean bean = new EventsListBean(userAgent, events);

        model.put("bean", bean);

        return "event-list";
    }

    @ModelAttribute("formBean")
    public EventAddFormBean createDefaultFormBean() {
        return new EventAddFormBean();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/events/add")
    public String addEventShowForm() {
        return "event-add";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/events/add")
    public String addEventPostForm(@Valid @ModelAttribute("formBean") EventAddFormBean formBean,
                                   BindingResult binding) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date startDate = null;
        Date endDate = null;

        /*if (formBean.getTitle().isEmpty()) {
            binding.addError(new FieldError("formBean",
                    "title",
                    "Заголовок пустой"));
        }
        if (formBean.getDescription().isEmpty()) {
            binding.addError(new FieldError("formBean",
                    "description",
                    "Описание пустое"));
        }*/

        try {
            startDate = format.parse(formBean.getStart());
        } catch (ParseException e) {
            binding.addError(new FieldError("formBean",
                    "start",
                    "Неверный формат даты"));
        }

        try {
            endDate = format.parse(formBean.getEnd());
        } catch (ParseException e) {
            binding.addError(new FieldError("formBean",
                    "end",
                    "Неверный формат даты"));
        }

        if (binding.hasErrors()) {
            return "event-add";
        }

        Event event = new Event(formBean.getTitle(), formBean.getDescription(), startDate, endDate);
        eventsDAO.add(event);

        return "redirect:/events/all";
    }
}
