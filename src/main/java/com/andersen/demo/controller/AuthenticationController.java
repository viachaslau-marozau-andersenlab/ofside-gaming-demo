package com.andersen.demo.controller;

import com.andersen.demo.dto.LoginDto;
import com.andersen.demo.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/login")
    @ApiOperation(value = "Generate token")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully generated token"),
            @ApiResponse(code = 403, message = "Incorrect password"),
            @ApiResponse(code = 404, message = "User with this id not found")
    })
    public String getToken(
            @ApiParam(value = "Login information") @Valid @RequestBody LoginDto loginDto
    ) {
        return authenticationService.login(loginDto.getLogin(), loginDto.getPassword());
    }
}
