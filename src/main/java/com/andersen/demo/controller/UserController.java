package com.andersen.demo.controller;

import com.andersen.demo.dto.UserDto;
import com.andersen.demo.mapper.UserMapper;
import com.andersen.demo.model.User;
import com.andersen.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/v1/users")
@RequiredArgsConstructor
@CrossOrigin
@Validated
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully get user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public UserDto getUserById(
            @ApiParam(value = "User id") @NotNull @PathVariable("userId") Long userId
    ) {
        User user = userService.findById(userId);
        return userMapper.toDto(user);
    }

    @GetMapping("")
    @ApiOperation(value = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully get users"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Page<UserDto> getAllUsers(
            @ApiParam(value = "Page") @RequestParam(value = "page", defaultValue = "0") int page,
            @ApiParam(value = "Limit") @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        Page<User> usersRage = userService.findAll(PageRequest.of(page, size));
        return new PageImpl<>(usersRage.get()
                .map(userMapper::toDto)
                .collect(toList()),
                PageRequest.of(page, size),
                usersRage.getTotalElements());
    }
}
