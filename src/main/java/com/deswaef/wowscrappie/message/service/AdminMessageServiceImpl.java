package com.deswaef.wowscrappie.message.service;

import com.deswaef.wowscrappie.message.dto.AdminMessageDto;
import com.deswaef.wowscrappie.message.repository.AdminMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMessageServiceImpl implements AdminMessageService{

    @Autowired
    private AdminMessageRepository adminMessageRepository;

    @Override
    public List<AdminMessageDto> returnLast3() {
        return null;
    }

    @Override
    public AdminMessageRepository getRepository() {
        return adminMessageRepository;
    }
}
