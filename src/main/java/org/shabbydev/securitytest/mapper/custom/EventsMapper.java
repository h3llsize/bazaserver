package org.shabbydev.securitytest.mapper.custom;

import org.mapstruct.Mapper;
import org.shabbydev.securitytest.entity.EventsEntity;
import org.shabbydev.securitytest.mapper.dto.EventsDTO;

@Mapper(componentModel = "spring")
public interface EventsMapper {
    EventsDTO toDto(EventsEntity eventsEntity);
    EventsEntity toEntity(EventsDTO eventsDTO);
}
