package com.eventtest.controller;

import com.eventtest.dto.EventDto;
import com.eventtest.model.Event;
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

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("create")
    public String showCreateForm(Model model){
        EventDto eventDto = new EventDto();
        model.addAttribute("event", eventDto);
        return "create";
    }

    @PostMapping("/create/save")
    public String createEvent(@Valid @ModelAttribute("event") EventDto eventDto,
                              BindingResult result, Model model) {
        Event existing = eventService.findByTitle(eventDto.getTitle());
//       if(existing != null){
//           result.rejectValue("title", null, "There is already an event with that name");
//       }
//       if(result.hasErrors()){
//           model.addAttribute("event", eventDto);
//           return "create";
//       }
       eventService.createEvent(eventDto);
        return "redirect:/create?success";
    }

    @GetMapping("/events")
    public String listCreatedEvents(Model model){
        List<EventDto> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events";
    }
//
//    @GetMapping("/findEventByTitle")
//    public ResponseEntity<EventInfoDto> getEventByTitle(@RequestParam String title){
//        EventInfoDto eventInfoDto = eventService.findEventByTitle(title);
//        return ResponseEntity.ok(eventInfoDto);
//    }


}
