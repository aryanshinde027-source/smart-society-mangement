package com.test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Dto.VisitorDto;
import com.test.Dto.VisitorRequestDto;
import com.test.Dto.VisitorResponseDto;
import com.test.Services.VisitorService;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

  
    @PostMapping("/add")
    public VisitorDto addVisitor(@RequestBody VisitorDto dto) {
        return visitorService.addVisitor(dto);
    }

   
    @GetMapping("/all")
    public List<VisitorDto> getAllVisitors() {
        return visitorService.getAllVisitors();
    }

   
    @GetMapping("/{visid}")
    public VisitorDto getVisitorById(@PathVariable int visid) {
        return visitorService.getVisitorById(visid);
    }

   
    @PutMapping("/update/{visid}")
    public VisitorDto updateVisitor(
            @PathVariable int visid,
            @RequestBody VisitorDto dto) {

        return visitorService.updateVisitor(visid, dto);
    }

   
    @PutMapping("/update-request/{visid}")
    public VisitorResponseDto updateVisitorRequest(
            @PathVariable int visid,
            @RequestBody VisitorRequestDto dto) {

        return visitorService.updateVisitor(visid, dto);
    }

   
    @DeleteMapping("/delete/{visid}")
    public String deleteVisitor(@PathVariable int visid) {
        visitorService.deleteVisitor(visid);
        return "Visitor deleted successfully";
    }

    
    @GetMapping("/resident/{residentId}")
    public List<VisitorDto> getVisitorsByResident(
            @PathVariable int residentId) {

        return visitorService.getVisitorsByResident(residentId);
    }

  
    @GetMapping("/flat/{flatId}")
    public List<VisitorDto> getVisitorsByFlat(
            @PathVariable int flatId) {

        return visitorService.getVisitorsByFlat(flatId);
    }
}
