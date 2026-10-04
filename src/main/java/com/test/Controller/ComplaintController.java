package com.test.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.test.Dto.ComplaintDto;
import com.test.Dto.ComplaintRequestDto;
import com.test.Dto.ComplaintResponseDto;
import com.test.Services.ComplaintService;

@RestController
@RequestMapping("/complaint")
@CrossOrigin(origins = "*")
public class ComplaintController {

    @Autowired
    ComplaintService complaintService;

    @PostMapping("/add")
    public ComplaintDto addComplaint(@RequestBody ComplaintRequestDto complaintRequestDto) {

        return complaintService.addComplaint(complaintRequestDto);
    }

    @GetMapping("/getAll")
    public List<ComplaintDto> getAllComplaint() {

        return complaintService.getAllComplaint();
    }

    @GetMapping("/getById/{complaintId}")
    public ComplaintResponseDto getComplaintById(@PathVariable int complaintId) {

        return complaintService.getComplaintById(complaintId);
    }

    @PutMapping("/update/{complaintId}")
    public ComplaintResponseDto updateComplaint(@PathVariable int complaintId,
            @RequestBody ComplaintRequestDto complaintRequestDto) {

        return complaintService.updateComplaint(complaintId, complaintRequestDto);
    }

    @DeleteMapping("/delete/{complaintId}")
    public String deleteComplaint(@PathVariable int complaintId) {

        return complaintService.deleteComplaint(complaintId);
    }

    @PutMapping("/addResidentToComplaint/{complaintId}/{residentId}")
    public ComplaintResponseDto addResidentToComplaint(@PathVariable int complaintId,
            @PathVariable int residentId) {

        return complaintService.addResidentToComplaint(complaintId, residentId);
    }

    @GetMapping("/getComplaintWithResident/{complaintId}")
    public ComplaintResponseDto getComplaintWithResident(@PathVariable int complaintId) {

        return complaintService.getComplaintWithResident(complaintId);
    }
}