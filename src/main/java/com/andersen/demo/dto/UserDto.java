package com.andersen.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @ApiModelProperty(hidden = true)
    @Min(message = "Id must be more than 0", value = 0)
    @JsonProperty("id")
    private Long id;

    @NotNull(message = "Login is mandatory.")
    @Size(message = "Login must be from 5 to 255 characters.", min = 5, max = 255)
    @JsonProperty("login")
    private String login;

    @ApiModelProperty(hidden = true)
    @Size(message = "Password must be from 5 to 255 characters.", min = 5, max = 255)
    @JsonProperty("password")
    private String password;

    @NotNull(message = "Role is mandatory.")
    @JsonProperty("role")
    private String role;
}
