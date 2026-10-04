package com.test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.test.Dto.MaintainceDto;
import com.test.Dto.MaintainceRequestDto;
import com.test.Dto.MaintainceResponseDto;
import com.test.Services.MaintainceService;


@RestController
@RequestMapping("/maintaince")
@CrossOrigin(origins = "*")
public class MaintainceController {

    @Autowired
    MaintainceService maintainceService;

    @PostMapping("/add")
    public MaintainceDto addMaintaince(@RequestBody MaintainceRequestDto maintainceRequestDto) {

        return maintainceService.addMaintaince(maintainceRequestDto);
    }

    @GetMapping("/getAll")
    public List<MaintainceDto> getAllMaintaince() {

        return maintainceService.getAllMaintaince();
    }

    @GetMapping("/getById/{maintainceId}")
    public MaintainceResponseDto getMaintainceById(@PathVariable int maintainceId) {

        return maintainceService.getMaintainceById(maintainceId);
    }

    @PutMapping("/update/{maintainceId}")
    public MaintainceResponseDto updateMaintaince(@PathVariable int maintainceId,
            @RequestBody MaintainceRequestDto maintainceRequestDto) {

        return maintainceService.updateMaintaince(maintainceId, maintainceRequestDto);
    }

    @DeleteMapping("/delete/{maintainceId}")
    public String deleteMaintaince(@PathVariable int maintainceId) {

        return maintainceService.deleteMaintaince(maintainceId);
    }

    @PutMapping("/addFlatToMaintaince/{maintainceId}/{flatId}")
    public MaintainceResponseDto addFlatToMaintaince(@PathVariable int maintainceId,
            @PathVariable int flatId) {

        return maintainceService.addFlatToMaintaince(maintainceId, flatId);
    }

    @GetMapping("/getMaintainceWithFlat/{maintainceId}")
    public MaintainceResponseDto getMaintainceWithFlat(@PathVariable int maintainceId) {

        return maintainceService.getMaintainceWithFlat(maintainceId);
    }
}