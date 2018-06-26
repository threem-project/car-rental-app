package com.threem.carrental.app.service.mapper;

import com.threem.carrental.app.model.dto.MainOfficeDto;
import com.threem.carrental.app.model.entity.MainOfficeEntity;
import org.springframework.stereotype.Service;

/**
 * @author misza_lemko on 27.05.2018
 * @project car-rental-app
 */
@Deprecated
@Service
public class MainOfficeMapper {

    public MainOfficeEntity toMainOfficeEntity(MainOfficeDto fromMainOfficeDto) {
        return MainOfficeEntity.builder()
                .id(fromMainOfficeDto.getId())
                .name(fromMainOfficeDto.getName())
                .domain(fromMainOfficeDto.getDomain())
                .address(fromMainOfficeDto.getAddress())
                .phone(fromMainOfficeDto.getPhone())
                .email(fromMainOfficeDto.getEmail())
                .build();
    }

    public MainOfficeDto toMainOfficeDto(MainOfficeEntity fromMainOfficeEntity) {
        return MainOfficeDto.builder()
                .id(fromMainOfficeEntity.getId())
                .name(fromMainOfficeEntity.getName())
                .domain(fromMainOfficeEntity.getDomain())
                .address(fromMainOfficeEntity.getAddress())
                .phone(fromMainOfficeEntity.getPhone())
                .email(fromMainOfficeEntity.getEmail())
                .build();
    }
}
