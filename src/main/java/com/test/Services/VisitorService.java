package com.test.Services;

import java.util.List;

import com.test.Dto.VisitorDto;
import com.test.Dto.VisitorRequestDto;
import com.test.Dto.VisitorResponseDto;

public interface VisitorService {

    VisitorDto addVisitor(VisitorDto dto);

    List<VisitorDto> getAllVisitors();

    VisitorDto getVisitorById(int visid);

    VisitorDto updateVisitor(int visid, VisitorDto dto);

    VisitorResponseDto updateVisitor(int visid, VisitorRequestDto dto);

    void deleteVisitor(int visid);

    List<VisitorDto> getVisitorsByResident(int residentId);

    List<VisitorDto> getVisitorsByFlat(int flatId);
}
