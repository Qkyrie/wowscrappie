package com.deswaef.weakauras.message.service;

import com.deswaef.weakauras.infrastructure.service.DefaultService;
import com.deswaef.weakauras.message.dto.AdminMessageDto;
import com.deswaef.weakauras.message.domain.AdminMessage;

import java.util.List;

public interface AdminMessageService extends DefaultService<AdminMessage, AdminMessageDto, Long> {
    List<AdminMessageDto> returnLast3();
}
