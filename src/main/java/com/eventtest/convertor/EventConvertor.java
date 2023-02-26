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



    public static EventDto convertEntityToDto(Event event){
        EventDto eventDto = new EventDto();
        eventDto.setTitle(event.getTitle());
        eventDto.setDate(event.getDate());
        eventDto.setDescription(event.getDescription());
        eventDto.setCreatedBy(event.getCreatedBy().getEmail());
        return eventDto;
    }




}
