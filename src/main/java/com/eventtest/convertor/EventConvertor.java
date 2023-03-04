package com.eventtest.convertor;

import com.eventtest.dto.EventDto;
import com.eventtest.model.Event;
import com.eventtest.repository.EventRepository;
import com.eventtest.repository.UserRepository;

public class EventConvertor {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public EventConvertor(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }



    public static EventDto convertEntityToDto(Event event){
        EventDto eventDto = new EventDto();
        eventDto.setTitle(event.getTitle());
        eventDto.setDate(event.getDate());
        eventDto.setDescription(event.getDescription());
        eventDto.setCreatedBy(event.getCreatedBy().getEmail());
        return eventDto;
    }
    public Event convertDtoToEntity(EventDto eventDto){
        Event event = new Event();
        event.setTitle(eventDto.getTitle());
        event.setDate(eventDto.getDate());
        event.setDescription(eventDto.getDescription());
        event.setCreatedBy(userRepository.findByEmail(eventDto.getCreatedBy()));
        return event;
    }




}
