package com.eventtest.service.impl;

import com.eventtest.service.EventService;
import com.eventtest.dto.EventCreateDto;
import com.eventtest.exception.EventException;
import com.eventtest.repository.EventRepository;
import com.eventtest.convertor.EventConvertor;
import com.eventtest.dto.EventInfoDto;
import com.eventtest.model.Event;
import com.eventtest.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("test_qualifier_eventServiceImpl")
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventInfoDto createEvent(EventCreateDto eventCreateDto) {
        return EventConvertor.entityToInfoDto(eventRepository.save(EventConvertor.createDtoToEntity(eventCreateDto)));
    }

    @Override
    public List<EventInfoDto> getAllEvents() {
        List<EventInfoDto> eventInfoDto = new ArrayList<>();
        eventRepository.findAll().forEach(event -> eventInfoDto.add(EventConvertor.entityToInfoDto(event)));
        return eventInfoDto;
    }

    @Override
    public EventInfoDto findEventByTitle(String title) {
        Event event = eventRepository.findByTitle(title)
                .orElseThrow(()-> new EventException("Event with title" + title+" not found"));

        return EventConvertor.entityToInfoDto(event);
    }


}
