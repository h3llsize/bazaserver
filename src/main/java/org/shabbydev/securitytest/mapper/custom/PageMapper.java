package org.shabbydev.securitytest.mapper.custom;

import org.mapstruct.Mapper;
import org.shabbydev.securitytest.entity.PageEntity;
import org.shabbydev.securitytest.mapper.dto.PageDTO;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface PageMapper {
    PageDTO toDTO(PageEntity pageEntity);
    PageEntity toEntity(PageDTO pageDTO);
}
