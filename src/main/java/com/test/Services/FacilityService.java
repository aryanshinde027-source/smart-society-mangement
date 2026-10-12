package com.test.Services;

import java.util.List;
import com.test.Dto.FacilityDto;
import com.test.Dto.FacilityRequestDto;
import com.test.Dto.FacilityResponseDto;

public interface FacilityService {

    FacilityDto addFacility(FacilityDto dto);

    List<FacilityDto> getAllFacility();

    FacilityDto getFacilityById(int facilid);

    FacilityDto updateFacility(int facilid, FacilityDto dto);

    void deleteFacility(int facilid);

    List<FacilityDto> getFacilitiesBySociety(int societyId);

	FacilityResponseDto updateFacility(int facilid, FacilityRequestDto dto);
}