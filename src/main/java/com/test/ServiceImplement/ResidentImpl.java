package com.test.ServiceImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Dto.ComplaintDto;
import com.test.Dto.ResidentRequestDto;
import com.test.Dto.ResidentResponseDto;
import com.test.Entities.Complaint;
import com.test.Entities.Flat;
import com.test.Entities.Resident;
import com.test.Repos.ComplaintRepo;
import com.test.Repos.FlatRepo;
import com.test.Repos.ResidentRepo;
import com.test.Services.ResidentService;

@Service
public class ResidentImpl implements ResidentService {

    @Autowired
    ResidentRepo residentRepo;

    @Autowired
    ComplaintRepo complaintRepo;

    @Autowired
    FlatRepo flatRepo;

    @Override
    public void addResident(ResidentRequestDto residentRequestDto) {
        if (residentRequestDto.getFlatId() <= 0) {
            throw new IllegalArgumentException("flatId is required");
        }

        Flat flat = flatRepo.findById(residentRequestDto.getFlatId())
                .orElseThrow(() -> new IllegalArgumentException("Flat not found"));

        Resident resident = new Resident();
        resident.setResidentName(residentRequestDto.getResidentName());
        resident.setMobilenumber(residentRequestDto.getMobilenumber());
        resident.setEmail(residentRequestDto.getEmail());
        resident.setGender(residentRequestDto.getGender());
        resident.setAge(residentRequestDto.getAge());
        resident.setRole(residentRequestDto.getRole());
        resident.setFlat(flat);

        residentRepo.save(resident);
    }

    @Override
    public List<ResidentResponseDto> getAllResident() {
        List<Resident> residentList = residentRepo.findAll();
        List<ResidentResponseDto> dtoList = new ArrayList<>();

        for (Resident resident : residentList) {
            ResidentResponseDto dto = new ResidentResponseDto();
            dto.setResidentid(resident.getResidentid());
            dto.setResidentName(resident.getResidentName());
            dto.setMobilenumber(resident.getMobilenumber());
            dto.setEmail(resident.getEmail());
            dto.setGender(resident.getGender());
            dto.setAge(resident.getAge());
            dto.setRole(resident.getRole());
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public ResidentResponseDto getResidentById(int residentId) {
        Resident resident = residentRepo.findById(residentId).orElse(null);

        if (resident == null) {
            return null;
        }

        ResidentResponseDto dto = new ResidentResponseDto();
        dto.setResidentid(resident.getResidentid());
        dto.setResidentName(resident.getResidentName());
        dto.setMobilenumber(resident.getMobilenumber());
        dto.setEmail(resident.getEmail());
        dto.setGender(resident.getGender());
        dto.setAge(resident.getAge());
        dto.setRole(resident.getRole());

        return dto;
    }

    @Override
    public void updateResident(int residentId, ResidentRequestDto residentRequestDto) {
        Resident resident = residentRepo.findById(residentId)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

        if (residentRequestDto.getFlatId() <= 0) {
            throw new IllegalArgumentException("flatId is required");
        }

        Flat flat = flatRepo.findById(residentRequestDto.getFlatId())
                .orElseThrow(() -> new IllegalArgumentException("Flat not found"));

        resident.setResidentName(residentRequestDto.getResidentName());
        resident.setMobilenumber(residentRequestDto.getMobilenumber());
        resident.setEmail(residentRequestDto.getEmail());
        resident.setGender(residentRequestDto.getGender());
        resident.setAge(residentRequestDto.getAge());
        resident.setRole(residentRequestDto.getRole());
        resident.setFlat(flat);

        residentRepo.save(resident);
    }

    @Override
    public void deleteResident(int residentId) {
        residentRepo.deleteById(residentId);
    }

    @Override
    public void addComplaintToResident(int residentId, int complaintId) {
        Resident resident = residentRepo.findById(residentId)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));
        Complaint complaint = complaintRepo.findById(complaintId)
                .orElseThrow(() -> new IllegalArgumentException("Complaint not found"));

        complaint.setResident(resident);
        complaintRepo.save(complaint);
    }

    @Override
    public ResidentResponseDto getResidentWithComplaints(int residentId) {
        Resident resident = residentRepo.findById(residentId).orElse(null);

        if (resident == null) {
            return null;
        }

        ResidentResponseDto response = new ResidentResponseDto();
        response.setResidentid(resident.getResidentid());
        response.setResidentName(resident.getResidentName());
        response.setMobilenumber(resident.getMobilenumber());
        response.setEmail(resident.getEmail());
        response.setGender(resident.getGender());
        response.setAge(resident.getAge());
        response.setRole(resident.getRole());

        List<ComplaintDto> complaintDtoList = new ArrayList<>();

        if (resident.getComplaints() != null) {
            for (Complaint complaint : resident.getComplaints()) {
                ComplaintDto dto = new ComplaintDto();
                dto.setComplaintid(complaint.getComplaintid());
                dto.setTitle(complaint.getTitle());
                dto.setDescription(complaint.getDescription());
                dto.setComplaintDate(complaint.getComplaintDate());
                dto.setStatus(complaint.getStatus());
                complaintDtoList.add(dto);
            }
        }

        response.setComplaints(complaintDtoList);
        return response;
    }
}
