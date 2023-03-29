package com.eventtest.controller;
import com.eventtest.dto.EventDto;
import com.eventtest.model.Event;
import com.eventtest.repository.EventRepository;
import com.eventtest.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EventController {
    private final EventService eventService;
    private final EventRepository eventRepository;

    @Autowired
    public EventController(EventService eventService, EventRepository eventRepository) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
    }


    @GetMapping("create")
    public String showCreateForm(Model model) {
        EventDto eventDto = new EventDto();
        model.addAttribute("event", eventDto);
        return "create";
    }

    @PostMapping("/create/save")
    public String createEvent(@Valid @ModelAttribute("event") EventDto eventDto,
                              BindingResult result, Model model) {
//        Event existing = eventService.findByTitle(eventDto.getTitle());
//       if(existing != null){
//           result.rejectValue("title", null, "There is already an event with that name");
//       }
//       if(result.hasErrors()){
//           model.addAttribute("event", eventDto);
//           return "create";
//       }
        eventService.createEvent(eventDto);
        return "redirect:/events";
    }

    @GetMapping("/events")
    public String listCreatedEvents(Model model) {

        List<EventDto> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/search")
    public String findEvent(@RequestParam(value = "title") String title, Model model) {
        EventDto eventDto = eventService.findEventByTitle(title);
        model.addAttribute("event", eventDto);
        return "details";
    }


    @PostMapping("/delete/{title}")
    public String deleteEvent(@PathVariable("title") String title, Model model) {
        eventService.deleteEvent(title);
        return "redirect:/events?success";
    }

    @GetMapping("/update/{title}")
    public String updateEvent(@PathVariable("title") String title, Model model, RedirectAttributes redirectAttributes) {
        EventDto eventDto = eventService.findEventByTitle(title);
        model.addAttribute("event", eventDto);
        model.addAttribute("eventTitle", "Edit Event (Title: " + title + ")");
        return "update_event";
    }

    @PostMapping("/update/save")
    public String saveUpdatedEvent(EventDto eventDto, RedirectAttributes redirectAttributes) {
        eventService.saveEvent(eventDto);
        redirectAttributes.addFlashAttribute("message", "The event has been saved successfully!");
        return "redirect:/events?success";
    }


}
