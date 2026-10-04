package com.test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Dto.NoticeDto;
import com.test.Dto.NoticeRequestDto;
import com.test.Dto.NoticeResponseDto;
import com.test.Dto.SocietyDto;
import com.test.Services.NoticeService;
@RestController
@RequestMapping("/notice")
@CrossOrigin(origins = "*")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    // CRUD APIs

    @PostMapping("/add")
    public void addNotice(@RequestBody NoticeRequestDto noticeRequestDto) {
        noticeService.addNotice(noticeRequestDto);
    }

    @GetMapping("/getAll")
    public List<NoticeResponseDto> getAllNotice() {
        return noticeService.getAllNotice();
    }

    @GetMapping("/getById/{noticeId}")
    public NoticeResponseDto getNoticeById(@PathVariable int noticeId) {
        return noticeService.getNoticeById(noticeId);
    }

    @PutMapping("/update/{noticeId}")
    public void updateNotice(@PathVariable int noticeId,
                             @RequestBody NoticeRequestDto noticeRequestDto) {

        noticeService.updateNotice(noticeId, noticeRequestDto);
    }

    @DeleteMapping("/delete/{noticeId}")
    public void deleteNotice(@PathVariable int noticeId) {
        noticeService.deleteNotice(noticeId);
    }

    // Relationship APIs

    @PutMapping("/addSocietyToNotice/{noticeId}/{societyId}")
    public void addSocietyToNotice(@PathVariable int noticeId,
                                   @PathVariable int societyId) {

        noticeService.addSocietyToNotice(noticeId, societyId);
    }

    @GetMapping("/getNoticeWithSociety/{noticeId}")
    public NoticeResponseDto getNoticeWithSociety(@PathVariable int noticeId) {

        return noticeService.getNoticeWithSociety(noticeId);
    }

}
