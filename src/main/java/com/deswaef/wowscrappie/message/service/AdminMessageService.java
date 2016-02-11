package com.deswaef.wowscrappie.message.service;

import com.deswaef.wowscrappie.infrastructure.service.DefaultService;
import com.deswaef.wowscrappie.message.dto.AdminMessageDto;
import com.deswaef.wowscrappie.message.domain.AdminMessage;

import java.util.List;

public interface AdminMessageService extends DefaultService<AdminMessage, AdminMessageDto, Long> {
    List<AdminMessageDto> returnLast3();
}
