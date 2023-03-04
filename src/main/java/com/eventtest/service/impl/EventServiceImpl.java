package com.eventtest.service.impl;

import com.eventtest.model.User;
import com.eventtest.repository.UserRepository;
import com.eventtest.service.EventService;
import com.eventtest.dto.EventDto;
import com.eventtest.repository.EventRepository;
import com.eventtest.convertor.EventConvertor;
import com.eventtest.model.Event;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("test_qualifier_eventServiceImpl")
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }


    public void createEvent(EventDto eventDto){
        Event event = new Event();
        event.setTitle(eventDto.getTitle());
        event.setDate(eventDto.getDate());
        event.setDescription(eventDto.getDescription());
        User user = userRepository.findByEmail(eventDto.getCreatedBy());
        event.setCreatedBy(user);
        user.setEvent(event);
        eventRepository.save(event);
    }


    @Override
    public List<EventDto> getAllEvents() {
        return this.eventRepository.findAll().stream()
                .map(EventConvertor::convertEntityToDto)
                .collect(Collectors.toList());

    }

    @Override
    public EventDto findEventByTitle(String title) {
        Event event = eventRepository.findByTitle(title);
        if(event == null){
            throw new RuntimeException("Event with "+ title+" not found");
        }
        return EventConvertor.convertEntityToDto(event);
    }

    @Override
    public void deleteEvent(String title) {
        Event event = eventRepository.findByTitle(title);
        if(event == null){
            throw new RuntimeException("Event with "+ title+" not found");
        }
        eventRepository.delete(event);

    }

    @Override
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }


}
