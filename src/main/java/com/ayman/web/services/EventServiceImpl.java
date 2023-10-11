package com.ayman.web.services;

import com.ayman.web.dtos.ClubDto;
import com.ayman.web.dtos.EventDto;
import com.ayman.web.models.Event;
import com.ayman.web.repositories.ClubRepository;
import com.ayman.web.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ayman.web.mappers.ClubMapper.mapToClub;
import static com.ayman.web.mappers.ClubMapper.mapToClubDto;
import static com.ayman.web.mappers.EventMapper.mapToEvent;
import static com.ayman.web.mappers.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService{
    private EventRepository eventRepository;
    private ClubRepository clubRepository;
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        ClubDto clubDto = mapToClubDto(clubRepository.findById(clubId).get());
        eventDto.setClub(mapToClub(clubDto));

        eventRepository.save(mapToEvent(eventDto));
    }

    @Override
    public List<EventDto> findAllEvents() {
        return eventRepository.findAll().stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findEventById(Long eventId) {
        EventDto dto = mapToEventDto(eventRepository.findById(eventId).get());
        return dto;
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        eventRepository.save(mapToEvent(eventDto));
    }

    @Override
    public void delete(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
