package com.test.Services;

import java.util.List;

import com.test.Dto.NoticeDto;
import com.test.Dto.NoticeRequestDto;
import com.test.Dto.NoticeResponseDto;



public interface NoticeService {
    void addNotice(NoticeRequestDto noticeRequestDto);

    List<NoticeResponseDto> getAllNotice();

    NoticeResponseDto getNoticeById(int noticeId);

    void updateNotice(int noticeId, NoticeRequestDto noticeRequestDto);

    void deleteNotice(int noticeId);

    void addSocietyToNotice(int noticeId, int societyId);

    NoticeResponseDto getNoticeWithSociety(int noticeId);
}
