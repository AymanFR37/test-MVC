package com.ayman.web.controllers;

import com.ayman.web.dtos.ClubDto;
import com.ayman.web.dtos.EventDto;
import com.ayman.web.services.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String eventList(Model model){
        List<EventDto> eventDtos = eventService.findAllEvents();
        model.addAttribute("events",eventDtos);
        return "list-events";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model){
        EventDto event = new EventDto();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "create-events";
    }

    @PostMapping("events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId,
                              @Valid @ModelAttribute("event") EventDto eventDto,
                              Model model,
                              BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("event",eventDto);
            return "create-clubs";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" +clubId;
    }

    @GetMapping("/events/{eventId}")
    public String eventDetail(@PathVariable("eventId") Long eventId, Model model){
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event",eventDto);
        return "detail-event";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model){
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event",eventDto);
        return "edit-events";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateClub(@PathVariable("eventId") Long eventId,
                             @Valid @ModelAttribute("event") EventDto eventDto,
                             Model model,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("event",eventDto);
            return "edit-events";
        }
        EventDto dto = eventService.findEventById(eventId);
        eventDto.setId(eventId);
        eventDto.setClub(dto.getClub());
        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteClub(@PathVariable("eventId") Long eventId){
        eventService.delete(eventId);
        return "redirect:/events";
    }
}
