package com.ayman.web.services;

import com.ayman.web.dtos.ClubDto;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    ClubDto saveClub(ClubDto clubDto);
    ClubDto findClubById(Long clubId);

    void updateClub(ClubDto clubDto);
}
