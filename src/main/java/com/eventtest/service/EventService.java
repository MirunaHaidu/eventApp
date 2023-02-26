package com.eventtest.service;

import com.eventtest.dto.EventDto;
import com.eventtest.model.Event;

import java.util.List;

public interface EventService {
//    EventInfoDto createEvent(EventDto eventDto);
    void createEvent(EventDto eventDto);
    List<EventDto> getAllEvents();
    Event findByTitle(String title);


}
