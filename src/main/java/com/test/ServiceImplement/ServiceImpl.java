package com.test.ServiceImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Dto.FlatDto;
import com.test.Dto.FlatResponseDto;
import com.test.Dto.NoticeDto;
import com.test.Dto.SocietyDto;
import com.test.Dto.SocietyRequestDto;
import com.test.Dto.SocietyResponseDto;
import com.test.Entities.Flat;
import com.test.Entities.Notice;
import com.test.Entities.Society;
import com.test.Repos.FlatRepo;
import com.test.Repos.NoticeRepo;
import com.test.Repos.SocietyRepo;
import com.test.Services.SocietyService;

@Service
public class ServiceImpl implements SocietyService {
	@Autowired
	SocietyRepo societyRepo;
	@Autowired
	FlatRepo flatRepo;
	@Autowired
	NoticeRepo noticeRepo;
	 @Override
	    public void addSociety(SocietyRequestDto societyRequestDto) {

	        Society society = new Society();

	        society.setSocietyname(societyRequestDto.getSocietyname());
	        society.setAddress(societyRequestDto.getAddress());
	        society.setCity(societyRequestDto.getCity());
	        society.setPincode(societyRequestDto.getPincode());
	        society.setRegistrationnumber(societyRequestDto.getRegistrationNumber());

	        societyRepo.save(society);
	    }

	    @Override
	    public List<SocietyResponseDto> getAllSociety() {

	        List<Society> societyList = societyRepo.findAll();

	        List<SocietyResponseDto> responseList = new ArrayList<>();

	        for (Society society : societyList) {

	            SocietyResponseDto response = new SocietyResponseDto();

	            response.setSocietyId(society.getId());
	            response.setSocietyname(society.getSocietyname());
	            response.setAddress(society.getAddress());
	            response.setCity(society.getCity());
	            response.setPincode(society.getPincode());
	            response.setRegistrationNumber(society.getRegistrationnumber());

	            responseList.add(response);
	        }

	        return responseList;
	    }

	    @Override
	    public SocietyResponseDto getSocietyById(int societyId) {

	        Society society = societyRepo.findById(societyId).orElse(null);

	        if (society == null) {
	            return null;
	        }

	        SocietyResponseDto response = new SocietyResponseDto();

	        response.setSocietyId(society.getId());
	        response.setSocietyname(society.getSocietyname());
	        response.setAddress(society.getAddress());
	        response.setCity(society.getCity());
	        response.setPincode(society.getPincode());
	        response.setRegistrationNumber(society.getRegistrationnumber());

	        return response;
	    }

	    @Override
	    public void updateSociety(int societyId, SocietyRequestDto societyRequestDto) {

	        Society society = societyRepo.findById(societyId).orElse(null);

	        if (society != null) {

	            society.setSocietyname(societyRequestDto.getSocietyname());
	            society.setAddress(societyRequestDto.getAddress());
	            society.setCity(societyRequestDto.getCity());
	            society.setPincode(societyRequestDto.getPincode());
	            society.setRegistrationnumber(societyRequestDto.getRegistrationNumber());

	            societyRepo.save(society);
	        }
	    }

	    @Override
	    public void deleteSociety(int societyId) {

	        societyRepo.deleteById(societyId);
	    }

		@Override
		public void addFlatToSociety(int societyId, int flatId) {
			Society society = societyRepo.findById(societyId).orElse(null);
			Flat flat = flatRepo.findById(flatId).orElse(null);
			if(society==null||flat==null) {
				return;
				
			}
			flat.setSocietyId(society);
			flatRepo.save(flat);
		}

		@Override
		public SocietyResponseDto getSocietyWithFlats(int societyId) {
			Society society = societyRepo.findById(societyId).orElse(null);
			if(society==null) {
			return null;
		}
			SocietyResponseDto response = new SocietyResponseDto();

		    response.setSocietyId(society.getId());
		    response.setSocietyname(society.getSocietyname());
		    response.setAddress(society.getAddress());
		    response.setCity(society.getCity());
		    response.setPincode(society.getPincode());
		    response.setRegistrationNumber(society.getRegistrationnumber());

		    List<FlatDto> flatDtoList = new ArrayList<>();

		    for (Flat flat : society.getFlats()) {

		        FlatDto dto = new FlatDto();

		        dto.setFlatid(flat.getFlatid());
		        dto.setFlatnumber(flat.getFlatnumber());
		        dto.setWing(flat.getWing());
		        dto.setFloortype(flat.getFloortype());
		        dto.setStatus(flat.getStatus());

		        flatDtoList.add(dto);
		    }

		    response.setFlats(flatDtoList);

		    return response;
		}

		@Override
		public void addNoticeToSociety(int societyId, int noticeId) {
			 Society society = societyRepo.findById(societyId).orElse(null);
			    Notice notice = noticeRepo.findById(noticeId).orElse(null);

			    if (society == null || notice == null) {
			        return;
			    }

			    notice.setSociety(society);

			    noticeRepo.save(notice);
			
		}

		@Override
		public SocietyResponseDto getSocietyWithNotices(int societyId) {
			 Society society = societyRepo.findById(societyId).orElse(null);

			    if (society == null) {
			        return null;
			    }

			    SocietyResponseDto response = new SocietyResponseDto();

			    response.setSocietyId(society.getId());
			    response.setSocietyname(society.getSocietyname());
			    response.setAddress(society.getAddress());
			    response.setCity(society.getCity());
			    response.setPincode(society.getPincode());
			    response.setRegistrationNumber(society.getRegistrationnumber());

			    List<NoticeDto> noticeDtoList = new ArrayList<>();

			    for (Notice notice : society.getNotices()) {

			        NoticeDto dto = new NoticeDto();

			        dto.setNoticeid(notice.getNoticeid());
			        dto.setTitle(notice.getTitle());
			        dto.setDescription(notice.getDescription());
			        dto.setNoticeDate(notice.getNoticeDate());

			        noticeDtoList.add(dto);
			    }

			    response.setNotices(noticeDtoList);

			    return response;
		}

	

	
}



