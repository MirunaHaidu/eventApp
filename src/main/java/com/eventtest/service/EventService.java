package com.eventtest.service;

import com.eventtest.dto.EventDto;
import com.eventtest.model.Event;

import java.util.List;

public interface EventService {

    void createEvent(EventDto eventDto);
    List<EventDto> getAllEvents();
    EventDto findEventByTitle(String title);
    void deleteEvent(String title);
//    String editEvent
    void saveEvent(Event event);
}
