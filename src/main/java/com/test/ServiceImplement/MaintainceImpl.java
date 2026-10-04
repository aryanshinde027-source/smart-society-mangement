package com.test.ServiceImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Dto.FlatDto;
import com.test.Dto.MaintainceDto;
import com.test.Dto.MaintainceRequestDto;
import com.test.Dto.MaintainceResponseDto;
import com.test.Entities.Flat;
import com.test.Entities.Maintaince;
import com.test.Repos.FlatRepo;
import com.test.Repos.MaintainceRepo;
import com.test.Services.MaintainceService;

@Service
public class MaintainceImpl implements MaintainceService{
	@Autowired
	MaintainceRepo maintainceRepo;
     @Autowired
     FlatRepo flatRepo;
	@Override
	public MaintainceDto addMaintaince(MaintainceRequestDto maintainceRequestDto) {
    if (maintainceRequestDto.getFlatId() <= 0) {
        throw new IllegalArgumentException("flatId is required");
    }

    Flat flat = flatRepo.findById(maintainceRequestDto.getFlatId())
            .orElseThrow(() -> new IllegalArgumentException("Flat not found"));

    Maintaince maintaince = new Maintaince();
    maintaince.setAmount(maintainceRequestDto.getAmount());
    maintaince.setAmountdue(maintainceRequestDto.getAmountdue());
    maintaince.setPaymentDate(maintainceRequestDto.getPaymentDate());
    maintaince.setPaymentStatus(maintainceRequestDto.getPaymentStatus());
    maintaince.setFlat(flat);

    maintaince = maintainceRepo.save(maintaince);

    MaintainceDto dto = new MaintainceDto();
    dto.setMaintainceid(maintaince.getMaintainceid());
    dto.setAmount(maintaince.getAmount());
    dto.setAmountdue(maintaince.getAmountdue());
    dto.setPaymentDate(maintaince.getPaymentDate());
    dto.setPaymentStatus(maintaince.getPaymentStatus());
    return dto;
}

	@Override
	public List<MaintainceDto> getAllMaintaince() {
		   List<Maintaince> list = maintainceRepo.findAll();
	        List<MaintainceDto> dtoList = new ArrayList<>();

	        for (Maintaince maintaince : list) {

	            MaintainceDto dto = new MaintainceDto();

	            dto.setMaintainceid(maintaince.getMaintainceid());
	            dto.setAmount(maintaince.getAmount());
	            dto.setAmountdue(maintaince.getAmountdue());
	            dto.setPaymentDate(maintaince.getPaymentDate());
	            dto.setPaymentStatus(maintaince.getPaymentStatus());

	            dtoList.add(dto);
	        }

	        return dtoList;
	}

	@Override
	public MaintainceResponseDto getMaintainceById(int maintainceId) {
	    Maintaince maintaince = maintainceRepo.findById(maintainceId).orElse(null);

        MaintainceResponseDto dto = new MaintainceResponseDto();

        dto.setMaintainceid(maintaince.getMaintainceid());
        dto.setAmount(maintaince.getAmount());
        dto.setAmountdue(maintaince.getAmountdue());
        dto.setPaymentDate(maintaince.getPaymentDate());
        dto.setPaymentStatus(maintaince.getPaymentStatus());

        return dto;
	}

	@Override
	public MaintainceResponseDto updateMaintaince(int maintainceId, MaintainceRequestDto maintainceRequestDto) {
    Maintaince maintaince = maintainceRepo.findById(maintainceId)
            .orElseThrow(() -> new IllegalArgumentException("Maintaince not found"));

    if (maintainceRequestDto.getFlatId() <= 0) {
        throw new IllegalArgumentException("flatId is required");
    }

    Flat flat = flatRepo.findById(maintainceRequestDto.getFlatId())
            .orElseThrow(() -> new IllegalArgumentException("Flat not found"));

    maintaince.setAmount(maintainceRequestDto.getAmount());
    maintaince.setAmountdue(maintainceRequestDto.getAmountdue());
    maintaince.setPaymentDate(maintainceRequestDto.getPaymentDate());
    maintaince.setPaymentStatus(maintainceRequestDto.getPaymentStatus());
    maintaince.setFlat(flat);

    maintaince = maintainceRepo.save(maintaince);

    MaintainceResponseDto dto = new MaintainceResponseDto();
    dto.setMaintainceid(maintaince.getMaintainceid());
    dto.setAmount(maintaince.getAmount());
    dto.setAmountdue(maintaince.getAmountdue());
    dto.setPaymentDate(maintaince.getPaymentDate());
    dto.setPaymentStatus(maintaince.getPaymentStatus());
    return dto;
}

	@Override
	public String deleteMaintaince(int maintainceId) {
		
        maintainceRepo.deleteById(maintainceId);

        return "Maintaince Deleted Successfully";
	}

	@Override
	public MaintainceResponseDto addFlatToMaintaince(int maintainceId, int flatId) {
		   Maintaince maintaince = maintainceRepo.findById(maintainceId).orElse(null);
	        Flat flat = flatRepo.findById(flatId).orElse(null);

	        maintaince.setFlat(flat);

	        maintaince = maintainceRepo.save(maintaince);

	        MaintainceResponseDto dto = new MaintainceResponseDto();

	        dto.setMaintainceid(maintaince.getMaintainceid());
	        dto.setAmount(maintaince.getAmount());
	        dto.setAmountdue(maintaince.getAmountdue());
	        dto.setPaymentDate(maintaince.getPaymentDate());
	        dto.setPaymentStatus(maintaince.getPaymentStatus());

	        FlatDto flatDto = new FlatDto();

	        flatDto.setFlatid(flat.getFlatid());
	        flatDto.setFlatnumber(flat.getFlatnumber());
	        flatDto.setFloortype(flat.getFloortype());
	        flatDto.setStatus(flat.getStatus());

	        dto.setFlat(flatDto);
           return dto;
	}

	@Override
	public MaintainceResponseDto getMaintainceWithFlat(int maintainceId) {
		Maintaince maintaince = maintainceRepo.findById(maintainceId).orElse(null);

        MaintainceResponseDto dto = new MaintainceResponseDto();

        dto.setMaintainceid(maintaince.getMaintainceid());
        dto.setAmount(maintaince.getAmount());
        dto.setAmountdue(maintaince.getAmountdue());
        dto.setPaymentDate(maintaince.getPaymentDate());
        dto.setPaymentStatus(maintaince.getPaymentStatus());

        Flat flat = maintaince.getFlat();

        if (flat != null) {

            FlatDto flatDto = new FlatDto();

            flatDto.setFlatid(flat.getFlatid());
            flatDto.setFlatnumber(flat.getFlatnumber());
            flatDto.setFloortype(flat.getFloortype());
            flatDto.setStatus(flat.getStatus());

            dto.setFlat(flatDto);
        }

        return dto;
    }
	}

 
