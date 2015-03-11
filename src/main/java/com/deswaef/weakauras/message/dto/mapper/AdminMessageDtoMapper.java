package com.deswaef.weakauras.message.dto.mapper;

import com.deswaef.weakauras.message.dto.AdminMessageDto;
import com.deswaef.weakauras.infrastructure.service.DtoMapper;
import com.deswaef.weakauras.message.domain.AdminMessage;
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
