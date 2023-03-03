package com.eventtest.repository;

import com.eventtest.model.Event;
import com.eventtest.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
    Event findByTitle(String title);

}
