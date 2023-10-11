package com.ayman.web.services;

import com.ayman.web.dtos.ClubDto;
import com.ayman.web.repositories.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ayman.web.mappers.ClubMapper.mapToClub;
import static com.ayman.web.mappers.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService{
    private ClubRepository clubRepository;
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        return clubRepository.findAll().stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public ClubDto saveClub(ClubDto clubDto) {

        return mapToClubDto(clubRepository.save(mapToClub(clubDto)));
    }

    @Override
    public ClubDto findClubById(Long clubId) {

        return mapToClubDto(clubRepository.findById(clubId).get());
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        clubRepository.save(mapToClub(clubDto));
    }

    @Override
    public void delete(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        return clubRepository.searchClubs(query).stream().map(club-> mapToClubDto(club)).collect(Collectors.toList());
    }
}
