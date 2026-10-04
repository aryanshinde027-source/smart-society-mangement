package com.test.Services;

import java.util.List;


import com.test.Dto.ResidentDto;
import com.test.Dto.ResidentRequestDto;
import com.test.Dto.ResidentResponseDto;

public interface ResidentService {
	void addResident(ResidentRequestDto residentRequestDto);

	List<ResidentResponseDto> getAllResident();

	ResidentResponseDto getResidentById(int residentId);

	void updateResident(int residentId, ResidentRequestDto residentRequestDto);

	void deleteResident(int residentId);

	void addComplaintToResident(int residentId, int complaintId);

	ResidentResponseDto getResidentWithComplaints(int residentId);

}
