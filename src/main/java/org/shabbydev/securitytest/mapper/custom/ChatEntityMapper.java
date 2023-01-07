package org.shabbydev.securitytest.mapper.custom;

import org.mapstruct.Mapper;
import org.shabbydev.securitytest.entity.ChatEntity;
import org.shabbydev.securitytest.mapper.dto.ChatDTO;


@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ChatEntityMapper {

    ChatEntity toEntity(ChatDTO chatDTO);

    ChatDTO toDTO(ChatEntity chatEntity);

}
