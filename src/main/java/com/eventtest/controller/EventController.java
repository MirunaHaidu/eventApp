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

    @PutMapping("/update/{title}")
    public String updateEvent(@PathVariable("title") String title, Event event, Model model){

        eventService.updateEvent(event, title);
        model.addAttribute("event", event);
        return "redirect:/edit";
    }
//    @GetMapping("/edit/{title}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//
//        model.addAttribute("user", user);
//        return "update-user";
//    }


}
