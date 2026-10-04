package com.test.ServiceImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Dto.FlatResponseDto;
import com.test.Dto.MaintainceDto;
import com.test.Dto.ResidentDto;
import com.test.Dto.SocietyDto;
import com.test.Dto.FlatRequestDto;
import com.test.Entities.Flat;
import com.test.Entities.Maintaince;
import com.test.Entities.Resident;
import com.test.Entities.Society;
import com.test.Repos.FlatRepo;
import com.test.Repos.MaintainceRepo;
import com.test.Repos.ResidentRepo;
import com.test.Repos.SocietyRepo;
import com.test.Services.FlatService;

@Service
public class FlatImpl implements FlatService {

    @Autowired
    FlatRepo flatRepo;

    @Autowired
    SocietyRepo societyRepo;

    @Autowired
    ResidentRepo residentRepo;

    @Autowired
    MaintainceRepo maintainceRepo;

    @Override
    public void addFlat(FlatRequestDto flatRequestDto) {
        if (flatRequestDto.getSocietyId() <= 0) {
            throw new IllegalArgumentException("societyId is required");
        }

        Society society = societyRepo.findById(flatRequestDto.getSocietyId())
                .orElseThrow(() -> new IllegalArgumentException("Society not found"));

        Flat flat = new Flat();
        flat.setFlatnumber(flatRequestDto.getFlatnumber());
        flat.setWing(flatRequestDto.getWing());
        flat.setFloortype(flatRequestDto.getFloortype());
        flat.setStatus(flatRequestDto.getStatus());
        flat.setSocietyId(society);

        flatRepo.save(flat);
    }

    @Override
    public List<FlatResponseDto> getAllFlat() {
        List<Flat> flatList = flatRepo.findAll();
        List<FlatResponseDto> dtoList = new ArrayList<>();

        for (Flat flat : flatList) {
            FlatResponseDto dto = new FlatResponseDto();
            dto.setFlatid(flat.getFlatid());
            dto.setFlatnumber(flat.getFlatnumber());
            dto.setWing(flat.getWing());
            dto.setFloortype(flat.getFloortype());
            dto.setStatus(flat.getStatus());
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public FlatResponseDto getFlatById(int flatId) {
        Flat flat = flatRepo.findById(flatId).orElse(null);

        if (flat == null) {
            return null;
        }

        FlatResponseDto dto = new FlatResponseDto();
        dto.setFlatid(flat.getFlatid());
        dto.setFlatnumber(flat.getFlatnumber());
        dto.setWing(flat.getWing());
        dto.setFloortype(flat.getFloortype());
        dto.setStatus(flat.getStatus());

        return dto;
    }

    @Override
    public void updateFlat(int flatId, FlatRequestDto flatRequestDto) {
        Flat flat = flatRepo.findById(flatId)
                .orElseThrow(() -> new IllegalArgumentException("Flat not found"));

        if (flatRequestDto.getSocietyId() <= 0) {
            throw new IllegalArgumentException("societyId is required");
        }

        Society society = societyRepo.findById(flatRequestDto.getSocietyId())
                .orElseThrow(() -> new IllegalArgumentException("Society not found"));

        flat.setFlatnumber(flatRequestDto.getFlatnumber());
        flat.setWing(flatRequestDto.getWing());
        flat.setFloortype(flatRequestDto.getFloortype());
        flat.setStatus(flatRequestDto.getStatus());
        flat.setSocietyId(society);

        flatRepo.save(flat);
    }

    @Override
    public void deleteFlat(int flatId) {
        flatRepo.deleteById(flatId);
    }

    @Override
    public void addSocietyToFlat(int flatId, int societyId) {
        Flat flat = flatRepo.findById(flatId)
                .orElseThrow(() -> new IllegalArgumentException("Flat not found"));
        Society society = societyRepo.findById(societyId)
                .orElseThrow(() -> new IllegalArgumentException("Society not found"));

        flat.setSocietyId(society);
        flatRepo.save(flat);
    }

    @Override
    public FlatResponseDto getFlatWithSociety(int flatId) {
        Flat flat = flatRepo.findById(flatId).orElse(null);

        if (flat == null) {
            return null;
        }

        FlatResponseDto response = new FlatResponseDto();
        response.setFlatid(flat.getFlatid());
        response.setFlatnumber(flat.getFlatnumber());
        response.setWing(flat.getWing());
        response.setFloortype(flat.getFloortype());
        response.setStatus(flat.getStatus());

        Society society = flat.getSocietyId();
        if (society != null) {
            SocietyDto dto = new SocietyDto();
            dto.setSocietyId(society.getId());
            dto.setSocietyname(society.getSocietyname());
            dto.setAddress(society.getAddress());
            dto.setCity(society.getCity());
            dto.setPincode(society.getPincode());
            dto.setRegistrationNumber(society.getRegistrationnumber());
            response.setSociety(dto);
        }

        return response;
    }

    @Override
    public void addResidentToFlat(int flatId, int residentId) {
        Flat flat = flatRepo.findById(flatId)
                .orElseThrow(() -> new IllegalArgumentException("Flat not found"));
        Resident resident = residentRepo.findById(residentId)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

        resident.setFlat(flat);
        residentRepo.save(resident);
    }

    @Override
    public FlatResponseDto getFlatWithResidents(int flatId) {
        Flat flat = flatRepo.findById(flatId).orElse(null);

        if (flat == null) {
            return null;
        }

        FlatResponseDto response = new FlatResponseDto();
        response.setFlatid(flat.getFlatid());
        response.setFlatnumber(flat.getFlatnumber());
        response.setWing(flat.getWing());
        response.setFloortype(flat.getFloortype());
        response.setStatus(flat.getStatus());

        List<ResidentDto> residentDtoList = new ArrayList<>();

        if (flat.getResidents() != null) {
            for (Resident resident : flat.getResidents()) {
                ResidentDto dto = new ResidentDto();
                dto.setResidentid(resident.getResidentid());
                dto.setResidentName(resident.getResidentName());
                dto.setMobilenumber(resident.getMobilenumber());
                dto.setEmail(resident.getEmail());
                dto.setGender(resident.getGender());
                dto.setAge(resident.getAge());
                dto.setRole(resident.getRole());
                residentDtoList.add(dto);
            }
        }

        response.setResidents(residentDtoList);
        return response;
    }

    @Override
    public void addMaintenanceToFlat(int flatId, int maintenanceId) {
        Flat flat = flatRepo.findById(flatId)
                .orElseThrow(() -> new IllegalArgumentException("Flat not found"));
        Maintaince maintaince = maintainceRepo.findById(maintenanceId)
                .orElseThrow(() -> new IllegalArgumentException("Maintaince not found"));

        maintaince.setFlat(flat);
        maintainceRepo.save(maintaince);
    }

    @Override
    public FlatResponseDto getFlatWithMaintenance(int flatId) {
        Flat flat = flatRepo.findById(flatId).orElse(null);

        if (flat == null) {
            return null;
        }

        FlatResponseDto response = new FlatResponseDto();
        response.setFlatid(flat.getFlatid());
        response.setFlatnumber(flat.getFlatnumber());
        response.setWing(flat.getWing());
        response.setFloortype(flat.getFloortype());
        response.setStatus(flat.getStatus());

        List<MaintainceDto> maintainceDtoList = new ArrayList<>();

        if (flat.getMaintainces() != null) {
            for (Maintaince maintaince : flat.getMaintainces()) {
                MaintainceDto dto = new MaintainceDto();
                dto.setMaintainceid(maintaince.getMaintainceid());
                dto.setAmount(maintaince.getAmount());
                dto.setAmountdue(maintaince.getAmountdue());
                dto.setPaymentDate(maintaince.getPaymentDate());
                dto.setPaymentStatus(maintaince.getPaymentStatus());
                maintainceDtoList.add(dto);
            }
        }

        response.setMaintainces(maintainceDtoList);
        return response;
    }
}
