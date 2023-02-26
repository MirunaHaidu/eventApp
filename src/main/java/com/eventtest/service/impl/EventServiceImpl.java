package com.eventtest.service.impl;

import com.eventtest.model.User;
import com.eventtest.repository.UserRepository;
import com.eventtest.service.EventService;
import com.eventtest.dto.EventDto;
import com.eventtest.repository.EventRepository;
import com.eventtest.convertor.EventConvertor;
import com.eventtest.model.Event;
import jakarta.transaction.Transactional;
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

//    @Override
//    public EventInfoDto createEvent(EventDto eventDto) {
//        return EventConvertor.entityToInfoDto(eventRepository.save(EventConvertor.createDtoToEntity(eventDto)));
//    }

    public void createEvent(EventDto eventDto){
        Event event = new Event();
        event.setTitle(eventDto.getTitle());
        event.setDate(eventDto.getDate());
        event.setDescription(eventDto.getDescription());
        User user = userRepository.findByEmail(eventDto.getCreatedBy());
        System.out.println("Show user: "+user);
        event.setCreatedBy(user);
        user.setEvent(event);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> getAllEvents() {
//        List<EventInfoDto> eventInfoDto = new ArrayList<>();
//        eventRepository.findAll().forEach(event -> eventInfoDto.add(EventConvertor.entityToInfoDto(event)));
//        return eventInfoDto;
        return this.eventRepository.findAll().stream()
                .map(EventConvertor::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Event findByTitle(String title) {
        Event event = eventRepository.findByTitle(title);
        if(event == null){
            throw new RuntimeException("Event with "+ title+" not found");
        }
        return event;

    }


}
