package com.test.ServiceImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Dto.ComplaintDto;
import com.test.Dto.ComplaintRequestDto;
import com.test.Dto.ComplaintResponseDto;
import com.test.Dto.ResidentDto;
import com.test.Entities.Complaint;
import com.test.Entities.Resident;
import com.test.Repos.ComplaintRepo;
import com.test.Repos.ResidentRepo;
import com.test.Services.ComplaintService;

@Service
public class ComplaintImpl implements ComplaintService {

    @Autowired
    private ComplaintRepo complaintRepo;
    @Autowired
    ResidentRepo residentRepo;
	@Override
	public ComplaintDto addComplaint(ComplaintRequestDto complaintRequestDto) {
    if (complaintRequestDto.getResidentId() <= 0) {
        throw new IllegalArgumentException("residentId is required");
    }

    Resident resident = residentRepo.findById(complaintRequestDto.getResidentId())
            .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

    Complaint complaint = new Complaint();
    complaint.setTitle(complaintRequestDto.getTitle());
    complaint.setDescription(complaintRequestDto.getDescription());
    complaint.setStatus(complaintRequestDto.getStatus());
    complaint.setComplaintDate(complaintRequestDto.getComplaintDate());
    complaint.setResident(resident);

    complaint = complaintRepo.save(complaint);

    ComplaintDto dto = new ComplaintDto();
    dto.setComplaintid(complaint.getComplaintid());
    dto.setTitle(complaint.getTitle());
    dto.setDescription(complaint.getDescription());
    dto.setStatus(complaint.getStatus());
    dto.setComplaintDate(complaint.getComplaintDate());
    return dto;
}

	@Override
	public List<ComplaintDto> getAllComplaint() {
	     List<Complaint> list = complaintRepo.findAll();
	        List<ComplaintDto> dtoList = new ArrayList<>();

	        for (Complaint complaint : list) {

	            ComplaintDto dto = new ComplaintDto();

	            dto.setComplaintid(complaint.getComplaintid());
	            dto.setTitle(complaint.getTitle());
	            dto.setDescription(complaint.getDescription());
	            dto.setStatus(complaint.getStatus());
	            dto.setComplaintDate(complaint.getComplaintDate());

	            dtoList.add(dto);
	        }

	        return dtoList;
	}

	@Override
	public ComplaintResponseDto getComplaintById(int complaintId) {
		
		 Complaint complaint = complaintRepo.findById(complaintId).orElse(null);

	        ComplaintResponseDto dto = new ComplaintResponseDto();

	        dto.setComplaintid(complaint.getComplaintid());
	        dto.setTitle(complaint.getTitle());
	        dto.setDescription(complaint.getDescription());
	        dto.setStatus(complaint.getStatus());
	        dto.setComplaintDate(complaint.getComplaintDate());

	        return dto;
	}

	@Override
	public ComplaintResponseDto updateComplaint(int complaintId, ComplaintRequestDto complaintRequestDto) {
    Complaint complaint = complaintRepo.findById(complaintId)
            .orElseThrow(() -> new IllegalArgumentException("Complaint not found"));

    if (complaintRequestDto.getResidentId() <= 0) {
        throw new IllegalArgumentException("residentId is required");
    }

    Resident resident = residentRepo.findById(complaintRequestDto.getResidentId())
            .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

    complaint.setTitle(complaintRequestDto.getTitle());
    complaint.setDescription(complaintRequestDto.getDescription());
    complaint.setStatus(complaintRequestDto.getStatus());
    complaint.setComplaintDate(complaintRequestDto.getComplaintDate());
    complaint.setResident(resident);

    complaint = complaintRepo.save(complaint);

    ComplaintResponseDto dto = new ComplaintResponseDto();
    dto.setComplaintid(complaint.getComplaintid());
    dto.setTitle(complaint.getTitle());
    dto.setDescription(complaint.getDescription());
    dto.setStatus(complaint.getStatus());
    dto.setComplaintDate(complaint.getComplaintDate());
    return dto;
}

	@Override
	public String deleteComplaint(int complaintId) {
		

        complaintRepo.deleteById(complaintId);

        return "Complaint Deleted Successfully";
	}

	@Override
	public ComplaintResponseDto addResidentToComplaint(int complaintId, int residentId) {
		Complaint complaint = complaintRepo.findById(complaintId).orElse(null);
        Resident resident = residentRepo.findById(residentId).orElse(null);

        complaint.setResident(resident);

        complaint = complaintRepo.save(complaint);

        ComplaintResponseDto dto = new ComplaintResponseDto();

        dto.setComplaintid(complaint.getComplaintid());
        dto.setTitle(complaint.getTitle());
        dto.setDescription(complaint.getDescription());
        dto.setStatus(complaint.getStatus());
        dto.setComplaintDate(complaint.getComplaintDate());

        ResidentDto residentDto = new ResidentDto();
        residentDto.setResidentid(resident.getResidentid());
        residentDto.setResidentName(resident.getResidentName());
        residentDto.setMobilenumber(resident.getMobilenumber());
        residentDto.setEmail(resident.getEmail());
        residentDto.setGender(resident.getGender());
        residentDto.setAge(resident.getAge());
        residentDto.setRole(resident.getRole());

        dto.setResident(residentDto);

        return dto;
		
	}

	@Override
	public ComplaintResponseDto getComplaintWithResident(int complaintId) {
		Complaint complaint = complaintRepo.findById(complaintId).orElse(null);

        ComplaintResponseDto dto = new ComplaintResponseDto();

        dto.setComplaintid(complaint.getComplaintid());
        dto.setTitle(complaint.getTitle());
        dto.setDescription(complaint.getDescription());
        dto.setStatus(complaint.getStatus());
        dto.setComplaintDate(complaint.getComplaintDate());

        Resident resident = complaint.getResident();

        if (resident != null) {

            ResidentDto residentDto = new ResidentDto();

            residentDto.setResidentid(resident.getResidentid());
            residentDto.setResidentName(resident.getResidentName());
            residentDto.setMobilenumber(resident.getMobilenumber());
            residentDto.setEmail(resident.getEmail());
            residentDto.setGender(resident.getGender());
            residentDto.setAge(resident.getAge());
            residentDto.setRole(resident.getRole());

            dto.setResident(residentDto);
        }

        return dto;
    }
	}

    

   

   



