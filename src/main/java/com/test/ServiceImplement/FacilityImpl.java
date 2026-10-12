package com.test.ServiceImplement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Dto.FacilityDto;
import com.test.Dto.FacilityRequestDto;
import com.test.Dto.FacilityResponseDto;
import com.test.Entities.Facility;
import com.test.Entities.Society;
import com.test.Repos.FacilityRepo;
import com.test.Repos.SocietyRepo;
import com.test.Services.FacilityService;

@Service
public class FacilityImpl implements FacilityService {

    @Autowired
    private FacilityRepo facilityRepo;

    @Autowired
    private SocietyRepo societyRepo;

    @Override
    public FacilityDto addFacility(FacilityDto dto) {

        Facility facility = new Facility();

        facility.setFacilname(dto.getFacilname());
        facility.setType(dto.getType());
        facility.setDescription(dto.getDescription());
        facility.setLocation(dto.getLocation());
        facility.setStatus(dto.getStatus());

        Society society = societyRepo.findById(dto.getSocietyId())
                .orElseThrow(() -> new IllegalArgumentException("Society not found"));

        facility.setSocietyId(society);

        return convertToDto(facilityRepo.save(facility));
    }

    @Override
    public List<FacilityDto> getAllFacility() {

        return facilityRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FacilityDto getFacilityById(int facilid) {

        Facility facility = facilityRepo.findById(facilid)
                .orElseThrow(() -> new IllegalArgumentException("Facility not found"));

        return convertToDto(facility);
    }

    @Override
    public FacilityDto updateFacility(int facilid, FacilityDto dto) {

        Facility facility = facilityRepo.findById(facilid)
                .orElseThrow(() -> new IllegalArgumentException("Facility not found"));

        facility.setFacilname(dto.getFacilname());
        facility.setType(dto.getType());
        facility.setDescription(dto.getDescription());
        facility.setLocation(dto.getLocation());
        facility.setStatus(dto.getStatus());

        Society society = societyRepo.findById(dto.getSocietyId())
                .orElseThrow(() -> new IllegalArgumentException("Society not found"));

        facility.setSocietyId(society);

        return convertToDto(facilityRepo.save(facility));
    }

    @Override
    public FacilityResponseDto updateFacility(int facilid, FacilityRequestDto dto) {

        Facility facility = facilityRepo.findById(facilid)
                .orElseThrow(() -> new IllegalArgumentException("Facility not found"));

        facility.setFacilname(dto.getFacilname());
        facility.setType(dto.getType());
        facility.setDescription(dto.getDescription());
        facility.setLocation(dto.getLocation());
        facility.setStatus(dto.getStatus());

        Society society = societyRepo.findById(dto.getSocietyId())
                .orElseThrow(() -> new IllegalArgumentException("Society not found"));

        facility.setSocietyId(society);

        Facility updatedFacility = facilityRepo.save(facility);

        FacilityResponseDto response = new FacilityResponseDto();

        response.setFacilid(updatedFacility.getFacilid());
        response.setFacilname(updatedFacility.getFacilname());
        response.setType(updatedFacility.getType());
        response.setDescription(updatedFacility.getDescription());
        response.setLocation(updatedFacility.getLocation());
        response.setStatus(updatedFacility.getStatus());
        response.setSocietyId(updatedFacility.getSocietyId().getId());

        return response;
    }

    @Override
    public void deleteFacility(int facilid) {

        Facility facility = facilityRepo.findById(facilid)
                .orElseThrow(() -> new IllegalArgumentException("Facility not found"));

        facilityRepo.delete(facility);
    }

    @Override
    public List<FacilityDto> getFacilitiesBySociety(int societyId) {

        return facilityRepo.findBySocietyId_Id(societyId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private FacilityDto convertToDto(Facility facility) {

        FacilityDto dto = new FacilityDto();

        dto.setFacilid(facility.getFacilid());
        dto.setFacilname(facility.getFacilname());
        dto.setType(facility.getType());
        dto.setDescription(facility.getDescription());
        dto.setLocation(facility.getLocation());
        dto.setStatus(facility.getStatus());
        dto.setSocietyId(facility.getSocietyId().getId());

        return dto;
    }
}