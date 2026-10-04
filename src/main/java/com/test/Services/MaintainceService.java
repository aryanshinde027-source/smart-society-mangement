package com.test.Services;

import java.util.List;

import com.test.Dto.MaintainceDto;
import com.test.Dto.MaintainceRequestDto;
import com.test.Dto.MaintainceResponseDto;


public interface MaintainceService {

    MaintainceDto addMaintaince(MaintainceRequestDto maintainceRequestDto);

    List<MaintainceDto> getAllMaintaince();

    MaintainceResponseDto getMaintainceById(int maintainceId);

    MaintainceResponseDto updateMaintaince(int maintainceId,
                                           MaintainceRequestDto maintainceRequestDto);

    String deleteMaintaince(int maintainceId);

    MaintainceResponseDto addFlatToMaintaince(int maintainceId, int flatId);

    MaintainceResponseDto getMaintainceWithFlat(int maintainceId);
}