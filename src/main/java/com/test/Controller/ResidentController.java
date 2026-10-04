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

import com.test.Dto.ResidentDto;
import com.test.Dto.ResidentRequestDto;
import com.test.Dto.ResidentResponseDto;
import com.test.Dto.SocietyDto;
import com.test.Services.ResidentService;

@RestController
@RequestMapping("/resident")
@CrossOrigin(origins = "*")
public class ResidentController {

	@Autowired
	ResidentService residentService;

	@PostMapping("/add")
	public void addResident(@RequestBody ResidentRequestDto residentRequestDto) {
		residentService.addResident(residentRequestDto);
	}

	@GetMapping("/getAll")
	public List<ResidentResponseDto> getAllResident() {
		return residentService.getAllResident();
	}

	@GetMapping("/getbyid/{residentId}")
	public ResidentResponseDto getResidentById(@PathVariable int residentId) {
		return residentService.getResidentById(residentId);
	}

	@PutMapping("/update/{residentId}")
	public void updateResident(@PathVariable int residentId,
			@RequestBody ResidentRequestDto residentRequestDto) {
		residentService.updateResident(residentId, residentRequestDto);
	}

	@DeleteMapping("/delete/{residentId}")
	public void deleteResident(@PathVariable int residentId) {
		residentService.deleteResident(residentId);
	}

	@PutMapping("/addComplaintToResident/{residentId}/{complaintId}")
	public void addComplaintToResident(@PathVariable int residentId,
			@PathVariable int complaintId) {

		residentService.addComplaintToResident(residentId, complaintId);
	}

	@GetMapping("/getResidentWithComplaints/{residentId}")
	public ResidentResponseDto getResidentWithComplaints(@PathVariable int residentId) {
		return residentService.getResidentWithComplaints(residentId);
	}


}
