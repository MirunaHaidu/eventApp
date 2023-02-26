package com.eventtest.convertor;

import com.eventtest.dto.EventDto;
import com.eventtest.model.Event;
import com.eventtest.repository.UserRepository;

import java.sql.Date;

public class EventConvertor {
    private final UserRepository userRepository;

    public EventConvertor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public Event createDtoToEntity(EventDto eventDto){
//        Event event = new Event();
//        event.setTitle(eventDto.getTitle());
//        event.setDate(eventDto.getDate());
//        event.setDescription(eventDto.getDescription());
//        event.setCreatedBy(userRepository.findByEmail(eventDto.getCreatedBy()));
//        return event;
//    }

    public static EventDto convertEntityToDto(Event event){
        EventDto eventDto = new EventDto();
        eventDto.setTitle(event.getTitle());
        eventDto.setDate(event.getDate().toString());
        eventDto.setDescription(event.getDescription());
        eventDto.setCreatedBy(event.getCreatedBy());
        return eventDto;
    }




}
