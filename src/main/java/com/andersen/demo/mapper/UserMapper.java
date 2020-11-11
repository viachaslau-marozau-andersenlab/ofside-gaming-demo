package com.andersen.demo.mapper;

import com.andersen.demo.dto.UserDto;
import com.andersen.demo.model.User;
import com.andersen.demo.service.RoleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", uses = {RoleService.class})
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "metrics", ignore = true),
            @Mapping(target = "authorities", ignore = true),
            @Mapping(target = "userRole", source = "userDto.role")
    })
    User fromDto(UserDto userDto);

    @Mappings({
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "role", source = "user.userRole.roleName")
    })
    UserDto toDto(User user);
}
