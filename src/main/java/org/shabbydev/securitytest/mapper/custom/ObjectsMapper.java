package org.shabbydev.securitytest.mapper.custom;

import org.mapstruct.Mapper;
import org.shabbydev.securitytest.entity.ObjectsEntity;
import org.shabbydev.securitytest.mapper.dto.ObjectsDTO;

@Mapper(componentModel = "spring")
public interface ObjectsMapper {
    ObjectsDTO toDto(ObjectsEntity objectsEntity);

    ObjectsEntity toEntity(ObjectsDTO objectsDTO);
}
