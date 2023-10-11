package com.ayman.web.controllers;

import com.ayman.web.dtos.ClubDto;
import com.ayman.web.services.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto,
                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "create-clubs";
        }
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
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDto clubDto,
                             Model model,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("club",clubDto);
            return "edit-clubs";
        }
        clubDto.setId(clubId);
        clubService.updateClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}")
    public String detailClub(@PathVariable("clubId") Long clubId, Model model){
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "detail-club";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId){
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClubs(@RequestParam(value = "query") String query, Model model){
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs",clubs);
        return "list-clubs";
    }
}
