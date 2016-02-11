package com.deswaef.wowscrappie.message.dto.mapper;

import com.deswaef.wowscrappie.message.dto.AdminMessageDto;
import com.deswaef.wowscrappie.infrastructure.service.DtoMapper;
import com.deswaef.wowscrappie.message.domain.AdminMessage;
import org.springframework.stereotype.Component;

@Component
public class AdminMessageDtoMapper implements DtoMapper<AdminMessage, AdminMessageDto>{
    @Override
    public AdminMessageDto map(AdminMessage adminMessage) {
        return AdminMessageDto.createAdminMessage()
                .setDateOfPosting(adminMessage.getDateOfPosting())
                .setId(adminMessage.getId())
                .setMessage(adminMessage.getMessage());
    }
}
