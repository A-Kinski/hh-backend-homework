package ru.hh.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hh.backend.dao.UserDao;
import ru.hh.backend.dto.request.ResumeRequestDto;
import ru.hh.backend.dto.response.ResumeResponseDto;
import ru.hh.backend.entity.Resume;

@Service
public class ResumeMapper {
    UserDao userDao;

    @Autowired
    public ResumeMapper(UserDao userDao) {
        this.userDao = userDao;
    }

    public Resume map(ResumeRequestDto resumeRequestDto) {
        return new Resume(resumeRequestDto.getResumeTitle(),
                resumeRequestDto.getWorkExperience(),
                resumeRequestDto.getContacts(),
                userDao.get(resumeRequestDto.getUserId()));
    }

    public ResumeResponseDto map(Resume resume) {
        return new ResumeResponseDto(resume.getId(),
                resume.getTitle(),
                resume.getContacts(),
                resume.getUser());
    }
}