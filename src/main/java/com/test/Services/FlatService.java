package com.test.Services;

import java.util.List;

import com.test.Dto.FlatDto;
import com.test.Dto.FlatRequestDto;
import com.test.Dto.FlatResponseDto;


public interface FlatService {
	 void addFlat(FlatRequestDto flatRequestDto);

	    List<FlatResponseDto> getAllFlat();

	    FlatResponseDto getFlatById(int flatId);

	    void updateFlat(int flatId, FlatRequestDto flatRequestDto);

	    void deleteFlat(int flatId);

	    // Relationship APIs

	    void addSocietyToFlat(int flatId, int societyId);

	    FlatResponseDto getFlatWithSociety(int flatId);

	    void addResidentToFlat(int flatId, int residentId);

	    FlatResponseDto getFlatWithResidents(int flatId);

	    void addMaintenanceToFlat(int flatId, int maintenanceId);

	    FlatResponseDto getFlatWithMaintenance(int flatId);
	

}
