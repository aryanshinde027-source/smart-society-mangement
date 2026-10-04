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

import com.test.Dto.SocietyDto;
import com.test.Dto.SocietyRequestDto;
import com.test.Dto.SocietyResponseDto;
import com.test.Services.SocietyService;

@RestController
@RequestMapping("/society")
@CrossOrigin(origins = "*")
public class SocietyController {

    @Autowired
    SocietyService societyService;

    // Add Society
    @PostMapping("/add")
    public void addSociety(@RequestBody SocietyRequestDto societyRequestDto) {
        societyService.addSociety(societyRequestDto);
    }

    // Get All Society
    @GetMapping("/getAll")
    public List<SocietyResponseDto> getAllSociety() {
        return societyService.getAllSociety();
    }

    
    @GetMapping("/getById/{societyId}")
    public SocietyResponseDto getSocietyById(@PathVariable int societyId) {
        return societyService.getSocietyById(societyId);
    }

    // Update Society
    @PutMapping("/update/{societyId}")
    public void updateSociety(@PathVariable int societyId,
                              @RequestBody SocietyRequestDto societyRequestDto) {

        societyService.updateSociety(societyId, societyRequestDto);
    }

    // Delete Society
    @DeleteMapping("/delete/{societyId}")
    public void deleteSociety(@PathVariable int societyId) {
        societyService.deleteSociety(societyId);
    }

    // Add Flat To Society
    @PutMapping("/addFlat/{societyId}/{flatId}")
    public void addFlatToSociety(@PathVariable int societyId,
                                 @PathVariable int flatId) {

        societyService.addFlatToSociety(societyId, flatId);
    }

    // Get Society With Flats
    @GetMapping("/getSocietyWithFlats/{societyId}")
    public SocietyResponseDto getSocietyWithFlats(@PathVariable int societyId) {

        return societyService.getSocietyWithFlats(societyId);
    }

    // Add Notice To Society
    @PutMapping("/addNotice/{societyId}/{noticeId}")
    public void addNoticeToSociety(@PathVariable int societyId,
                                   @PathVariable int noticeId) {

        societyService.addNoticeToSociety(societyId, noticeId);
    }

    // Get Society With Notices
    @GetMapping("/getSocietyWithNotices/{societyId}")
    public SocietyResponseDto getSocietyWithNotices(@PathVariable int societyId) {

        return societyService.getSocietyWithNotices(societyId);
    }

}
