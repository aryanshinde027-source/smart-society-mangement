package com.test.Services;

import java.util.List;

import com.test.Dto.ComplaintDto;
import com.test.Dto.ComplaintRequestDto;
import com.test.Dto.ComplaintResponseDto;
import com.test.Dto.ComplaintRequestDto;

public interface ComplaintService {

    ComplaintDto addComplaint(ComplaintRequestDto complaintRequestDto);

    List<ComplaintDto> getAllComplaint();

    ComplaintResponseDto getComplaintById(int complaintId);

    ComplaintResponseDto updateComplaint(int complaintId, ComplaintRequestDto complaintRequestDto);

    String deleteComplaint(int complaintId);

    ComplaintResponseDto addResidentToComplaint(int complaintId, int residentId);

    ComplaintResponseDto getComplaintWithResident(int complaintId);
}
