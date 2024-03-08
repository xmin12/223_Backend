package com.example.demo.domain.mylistentry.dto;

import com.example.demo.core.generic.AbstractMapper;
import com.example.demo.domain.mylistentry.MyListEntry;
import com.example.demo.domain.user.dto.UserMinimalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMinimalMapper.class})
public interface MyListEntryMinimalMapper extends AbstractMapper<MyListEntry, MyListEntryMinimalDTO> {

}
