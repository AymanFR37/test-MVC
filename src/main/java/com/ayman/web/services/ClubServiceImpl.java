package com.ayman.web.services;

import com.ayman.web.dtos.ClubDto;
import com.ayman.web.mappers.ClubMapper;
import com.ayman.web.repositories.ClubRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService{
    private ClubRepository clubRepository;
    private ClubMapper clubMapper = Mappers.getMapper(ClubMapper.class);
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<ClubDto> all = clubMapper.clubsToClubsDtos(clubRepository.findAll());
        return all;
    }

    @Override
    public ClubDto saveClub(ClubDto clubDto) {
        ClubDto savedClub = clubMapper.clubToClubDto(clubRepository.save(clubMapper.clubDtoToClub(clubDto)));
        return savedClub;
    }

    @Override
    public ClubDto findClubById(Long clubId) {
        ClubDto clubDto = clubMapper.clubToClubDto(clubRepository.findById(clubId).get());
        return clubDto;
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        clubRepository.save(clubMapper.clubDtoToClub(clubDto));
    }
}
