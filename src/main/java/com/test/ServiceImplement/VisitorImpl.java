package com.test.ServiceImplement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Dto.VisitorDto;
import com.test.Dto.VisitorRequestDto;
import com.test.Dto.VisitorResponseDto;
import com.test.Entities.Visitor;
import com.test.Entities.Flat;
import com.test.Entities.Resident;
import com.test.Repos.VisitorRepo;
import com.test.Repos.FlatRepo;
import com.test.Repos.ResidentRepo;
import com.test.Services.VisitorService;

@Service
public class VisitorImpl implements VisitorService {

    @Autowired
    private VisitorRepo visitorRepo;

    @Autowired
    private FlatRepo flatRepo;

    @Autowired
    private ResidentRepo residentRepo;

  
    @Override

public VisitorDto addVisitor(VisitorDto dto) {

    Visitor visitor = new Visitor();

    visitor.setName(dto.getName());
    visitor.setMobile(dto.getMobile());
    visitor.setEmail(dto.getEmail());
    visitor.setPurpose(dto.getPurpose());
    visitor.setEntryDate(dto.getEntryDate());
    visitor.setExitDate(dto.getExitDate());

    Flat flat = flatRepo.findById(dto.getFlatId())
            .orElseThrow(() -> new IllegalArgumentException("Flat not found"));

    Resident resident = residentRepo.findById(dto.getResidentId())
            .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

    // Validate that the resident belongs to the selected flat

if (resident.getFlat() == null ||
        resident.getFlat().getFlatid() != flat.getFlatid()) {
    throw new IllegalArgumentException(
            "Selected resident does not belong to the selected flat");
}

    visitor.setFlat(flat);
    visitor.setResident(resident);

    Visitor savedVisitor = visitorRepo.save(visitor);

    return convertToDto(savedVisitor);
}

   
    @Override
    public List<VisitorDto> getAllVisitors() {

        return visitorRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

  
    @Override
    public VisitorDto getVisitorById(int visid) {

        Visitor visitor = visitorRepo.findById(visid)
                .orElseThrow(() -> new IllegalArgumentException("Visitor not found"));

        return convertToDto(visitor);
    }

  
    @Override
    public VisitorDto updateVisitor(int visid, VisitorDto dto) {

        Visitor visitor = visitorRepo.findById(visid)
                .orElseThrow(() -> new IllegalArgumentException("Visitor not found"));

        visitor.setName(dto.getName());
        visitor.setMobile(dto.getMobile());
        visitor.setEmail(dto.getEmail());
        visitor.setPurpose(dto.getPurpose());
        visitor.setEntryDate(dto.getEntryDate());
        visitor.setExitDate(dto.getExitDate());

        Flat flat = flatRepo.findById(dto.getFlatId())
                .orElseThrow(() -> new IllegalArgumentException("Flat not found"));

        Resident resident = residentRepo.findById(dto.getResidentId())
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

        visitor.setFlat(flat);
        visitor.setResident(resident);

        return convertToDto(visitorRepo.save(visitor));
    }

   
    @Override
    public VisitorResponseDto updateVisitor(int visid, VisitorRequestDto dto) {

        Visitor visitor = visitorRepo.findById(visid)
                .orElseThrow(() -> new IllegalArgumentException("Visitor not found"));

        visitor.setName(dto.getName());
        visitor.setMobile(dto.getMobile());
        visitor.setEmail(dto.getEmail());
        visitor.setPurpose(dto.getPurpose());
        visitor.setEntryDate(dto.getEntryDate());
        visitor.setExitDate(dto.getExitDate());

        Flat flat = flatRepo.findById(dto.getFlatId())
                .orElseThrow(() -> new IllegalArgumentException("Flat not found"));

        Resident resident = residentRepo.findById(dto.getResidentId())
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

        visitor.setFlat(flat);
        visitor.setResident(resident);

        Visitor updatedVisitor = visitorRepo.save(visitor);

        VisitorResponseDto response = new VisitorResponseDto();

        response.setVisid(updatedVisitor.getVisid());
        response.setName(updatedVisitor.getName());
        response.setMobile(updatedVisitor.getMobile());
        response.setEmail(updatedVisitor.getEmail());
        response.setPurpose(updatedVisitor.getPurpose());
        response.setEntryDate(updatedVisitor.getEntryDate());
        response.setExitDate(updatedVisitor.getExitDate());
        response.setFlatId(updatedVisitor.getFlat().getFlatid());
        response.setResidentId(updatedVisitor.getResident().getResidentid());

        return response;
    }

   
    @Override
    public void deleteVisitor(int visid) {

        Visitor visitor = visitorRepo.findById(visid)
                .orElseThrow(() -> new IllegalArgumentException("Visitor not found"));

        visitorRepo.delete(visitor);
    }

   
    @Override
    public List<VisitorDto> getVisitorsByResident(int residentId) {

        return visitorRepo.findByResident_Residentid(residentId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

   
    @Override
    public List<VisitorDto> getVisitorsByFlat(int flatId) {

        return visitorRepo.findByFlat_Flatid(flatId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

   
    private VisitorDto convertToDto(Visitor visitor) {

        VisitorDto dto = new VisitorDto();

        dto.setVisid(visitor.getVisid());
        dto.setName(visitor.getName());
        dto.setMobile(visitor.getMobile());
        dto.setEmail(visitor.getEmail());
        dto.setPurpose(visitor.getPurpose());
        dto.setEntryDate(visitor.getEntryDate());
        dto.setExitDate(visitor.getExitDate());
        dto.setFlatId(visitor.getFlat().getFlatid());
        dto.setResidentId(visitor.getResident().getResidentid());

        return dto;
    }
}
