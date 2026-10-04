package com.test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Dto.FlatDto;
import com.test.Dto.FlatRequestDto;
import com.test.Dto.FlatResponseDto;
import com.test.Dto.SocietyDto;
import com.test.Services.FlatService;

@RestController
@RequestMapping("/flat")
@CrossOrigin(origins = "*")
public class FlatController {

    @Autowired
    FlatService flatService;

    // CRUD APIs

    @PostMapping("/add")
    public void addFlat(@RequestBody FlatRequestDto flatRequestDto) {
        flatService.addFlat(flatRequestDto);
    }

    @GetMapping("/getAll")
    public List<FlatResponseDto> getAllFlat() {
        return flatService.getAllFlat();
    }

    @GetMapping("/getById/{flatId}")
    public FlatResponseDto getFlatById(@PathVariable int flatId) {
        return flatService.getFlatById(flatId);
    }

    @PutMapping("/update/{flatId}")
    public void updateFlat(@PathVariable int flatId,
                           @RequestBody FlatRequestDto flatRequestDto) {

        flatService.updateFlat(flatId, flatRequestDto);
    }

    @DeleteMapping("/delete/{flatId}")
    public void deleteFlat(@PathVariable int flatId) {
        flatService.deleteFlat(flatId);
    }

    // Relationship APIs

    @PutMapping("/addSocietyToFlat/{flatId}/{societyId}")
    public void addSocietyToFlat(@PathVariable int flatId,
                                 @PathVariable int societyId) {

        flatService.addSocietyToFlat(flatId, societyId);
    }

    @GetMapping("/getFlatWithSociety/{flatId}")
    public FlatResponseDto getFlatWithSociety(@PathVariable int flatId) {
        return flatService.getFlatWithSociety(flatId);
    }

    @PutMapping("/addResidentToFlat/{flatId}/{residentId}")
    public void addResidentToFlat(@PathVariable int flatId,
                                  @PathVariable int residentId) {

        flatService.addResidentToFlat(flatId, residentId);
    }

    @GetMapping("/getFlatWithResidents/{flatId}")
    public FlatResponseDto getFlatWithResidents(@PathVariable int flatId) {
        return flatService.getFlatWithResidents(flatId);
    }

    @PutMapping("/addMaintenanceToFlat/{flatId}/{maintenanceId}")
    public void addMaintenanceToFlat(@PathVariable int flatId,
                                     @PathVariable int maintenanceId) {

        flatService.addMaintenanceToFlat(flatId, maintenanceId);
    }

    @GetMapping("/getFlatWithMaintenance/{flatId}")
    public FlatResponseDto getFlatWithMaintenance(@PathVariable int flatId) {
        return flatService.getFlatWithMaintenance(flatId);
    }

}
