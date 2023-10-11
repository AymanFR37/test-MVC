package com.ayman.web.services;

import com.ayman.web.dtos.EventDto;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findEventById(Long eventId);

    void updateEvent(EventDto eventDto);

    void delete(Long eventId);
}
