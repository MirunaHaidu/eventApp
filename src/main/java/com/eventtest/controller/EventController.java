package com.eventtest.controller;

import com.eventtest.dto.EventCreateDto;
import com.eventtest.dto.EventInfoDto;
import com.eventtest.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public ResponseEntity<EventInfoDto> createEvent(@RequestBody @Valid EventCreateDto eventCreateDto) {
        return ResponseEntity.ok(eventService.createEvent(eventCreateDto));
    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<List<EventInfoDto>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/findEventByTitle")
    public ResponseEntity<EventInfoDto> getEventByTitle(@RequestParam String title){
        EventInfoDto eventInfoDto = eventService.findEventByTitle(title);
        return ResponseEntity.ok(eventInfoDto);
    }


}
