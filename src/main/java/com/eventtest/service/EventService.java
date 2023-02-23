package com.eventtest.service;

import com.eventtest.dto.EventCreateDto;
import com.eventtest.dto.EventInfoDto;

import java.util.List;

public interface EventService {
    EventInfoDto createEvent(EventCreateDto eventCreateDto);
    List<EventInfoDto> getAllEvents();
    EventInfoDto findEventByTitle(String title);

}
