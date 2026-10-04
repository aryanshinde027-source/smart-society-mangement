package com.test.Services;

import java.util.List;

import com.test.Dto.SocietyDto;
import com.test.Dto.SocietyRequestDto;
import com.test.Dto.SocietyResponseDto;

public interface SocietyService {
	 void addSociety(SocietyRequestDto societyRequestDto);

	    List<SocietyResponseDto> getAllSociety();

	    SocietyResponseDto getSocietyById(int societyId);

	    void updateSociety(int societyId, SocietyRequestDto societyRequestDto);

	    void deleteSociety(int societyId);


	    // Relationship APIs

	    void addFlatToSociety(int societyId, int flatId);

	    SocietyResponseDto getSocietyWithFlats(int societyId);

	    void addNoticeToSociety(int societyId, int noticeId);

	    SocietyResponseDto getSocietyWithNotices(int societyId); 

}
