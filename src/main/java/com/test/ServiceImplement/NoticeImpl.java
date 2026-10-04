package com.test.ServiceImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Dto.NoticeResponseDto;
import com.test.Dto.NoticeRequestDto;
import com.test.Dto.SocietyDto;
import com.test.Entities.Notice;
import com.test.Entities.Society;
import com.test.Repos.NoticeRepo;
import com.test.Repos.SocietyRepo;
import com.test.Services.NoticeService;

@Service
public class NoticeImpl implements NoticeService {

    @Autowired
    NoticeRepo noticeRepo;

    @Autowired
    SocietyRepo societyRepo;

    @Override
    public void addNotice(NoticeRequestDto noticeRequestDto) {
        if (noticeRequestDto.getSocietyId() <= 0) {
            throw new IllegalArgumentException("societyId is required");
        }

        Society society = societyRepo.findById(noticeRequestDto.getSocietyId())
                .orElseThrow(() -> new IllegalArgumentException("Society not found"));

        Notice notice = new Notice();
        notice.setTitle(noticeRequestDto.getTitle());
        notice.setDescription(noticeRequestDto.getDescription());
        notice.setNoticeDate(noticeRequestDto.getNoticeDate());
        notice.setSociety(society);

        noticeRepo.save(notice);
    }

    @Override
    public List<NoticeResponseDto> getAllNotice() {
        List<Notice> noticeList = noticeRepo.findAll();
        List<NoticeResponseDto> dtoList = new ArrayList<>();

        for (Notice notice : noticeList) {
            NoticeResponseDto dto = new NoticeResponseDto();
            dto.setNoticeid(notice.getNoticeid());
            dto.setTitle(notice.getTitle());
            dto.setDescription(notice.getDescription());
            dto.setNoticeDate(notice.getNoticeDate());
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public NoticeResponseDto getNoticeById(int noticeId) {
        Notice notice = noticeRepo.findById(noticeId).orElse(null);

        if (notice == null) {
            return null;
        }

        NoticeResponseDto dto = new NoticeResponseDto();
        dto.setNoticeid(notice.getNoticeid());
        dto.setTitle(notice.getTitle());
        dto.setDescription(notice.getDescription());
        dto.setNoticeDate(notice.getNoticeDate());

        return dto;
    }

    @Override
    public void updateNotice(int noticeId, NoticeRequestDto noticeRequestDto) {
        Notice notice = noticeRepo.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("Notice not found"));

        if (noticeRequestDto.getSocietyId() <= 0) {
            throw new IllegalArgumentException("societyId is required");
        }

        Society society = societyRepo.findById(noticeRequestDto.getSocietyId())
                .orElseThrow(() -> new IllegalArgumentException("Society not found"));

        notice.setTitle(noticeRequestDto.getTitle());
        notice.setDescription(noticeRequestDto.getDescription());
        notice.setNoticeDate(noticeRequestDto.getNoticeDate());
        notice.setSociety(society);

        noticeRepo.save(notice);
    }

    @Override
    public void deleteNotice(int noticeId) {
        noticeRepo.deleteById(noticeId);
    }

    @Override
    public void addSocietyToNotice(int noticeId, int societyId) {
        Notice notice = noticeRepo.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("Notice not found"));
        Society society = societyRepo.findById(societyId)
                .orElseThrow(() -> new IllegalArgumentException("Society not found"));

        notice.setSociety(society);
        noticeRepo.save(notice);
    }

    @Override
    public NoticeResponseDto getNoticeWithSociety(int noticeId) {
        Notice notice = noticeRepo.findById(noticeId).orElse(null);

        if (notice == null) {
            return null;
        }

        NoticeResponseDto response = new NoticeResponseDto();
        response.setNoticeid(notice.getNoticeid());
        response.setTitle(notice.getTitle());
        response.setDescription(notice.getDescription());
        response.setNoticeDate(notice.getNoticeDate());

        if (notice.getSociety() != null) {
            Society society = notice.getSociety();

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
}
