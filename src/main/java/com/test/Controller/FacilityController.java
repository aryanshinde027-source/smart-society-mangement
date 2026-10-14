package com.test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Dto.FacilityDto;
import com.test.Dto.FacilityRequestDto;
import com.test.Dto.FacilityResponseDto;
import com.test.Services.FacilityService;

@RestController
@RequestMapping("/facility")
@CrossOrigin(origins = "*")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @PostMapping("/add")
    public FacilityDto addFacility(@RequestBody FacilityDto dto) {
        return facilityService.addFacility(dto);
    }

    @GetMapping("/all")
    public List<FacilityDto> getAllFacility() {
        return facilityService.getAllFacility();
    }

    @GetMapping("/{facilid}")
    public FacilityDto getFacilityById(@PathVariable int facilid) {
        return facilityService.getFacilityById(facilid);
    }

    @PutMapping("/update/{facilid}")
    public FacilityDto updateFacility(
            @PathVariable int facilid,
            @RequestBody FacilityDto dto) {

        return facilityService.updateFacility(facilid, dto);
    }

    @PutMapping("/update-request/{facilid}")
    public FacilityResponseDto updateFacilityRequest(
            @PathVariable int facilid,
            @RequestBody FacilityRequestDto dto) {

        return facilityService.updateFacility(facilid, dto);
    }

    @DeleteMapping("/delete/{facilid}")
    public String deleteFacility(@PathVariable int facilid) {
        facilityService.deleteFacility(facilid);
        return "Facility deleted successfully";
    }

    @GetMapping("/society/{societyId}")
    public List<FacilityDto> getFacilitiesBySociety(
            @PathVariable int societyId) {

        return facilityService.getFacilitiesBySociety(societyId);
    }
}
