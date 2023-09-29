package com.ayman.web.controllers;

import com.ayman.web.dtos.ClubDto;
import com.ayman.web.services.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model){
        List<ClubDto> allClubs = clubService.findAllClubs();
        model.addAttribute("clubs",allClubs);
        return "list-clubs";
    }

    @GetMapping("/clubs/new")
    public String createClubs(Model model){
        ClubDto clubDto = new ClubDto();
        model.addAttribute("club",clubDto);
        return "create-clubs";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@ModelAttribute("club") ClubDto clubDto){
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model){
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club",clubDto);
        return "edit-clubs";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId,@ModelAttribute ClubDto clubDto){
        clubDto.setId(clubId);
        clubService.updateClub(clubDto);
        return "redirect:/clubs";
    }
}
