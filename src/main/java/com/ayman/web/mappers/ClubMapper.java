package com.ayman.web.mappers;

import com.ayman.web.dtos.ClubDto;
import com.ayman.web.models.Club;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ClubMapper {
    Club clubDtoToClub(ClubDto clubDto);
    ClubDto clubToClubDto(Club club);
    List<ClubDto> clubsToClubsDtos(List<Club> clubs);
}
