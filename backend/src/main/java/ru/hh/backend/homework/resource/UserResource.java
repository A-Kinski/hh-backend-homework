package ru.hh.backend.homework.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.homework.dto.request.UserRequestDto;
import ru.hh.backend.homework.dto.response.UserResponseDto;
import ru.hh.backend.homework.entity.UserEntity;
import ru.hh.backend.homework.mapper.UserMapper;
import ru.hh.backend.homework.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class UserResource {
    private final UserMapper userMapper;
    private final UserService userService;

    @Inject
    public UserResource(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserResponseDto create(UserRequestDto userRequestDto) {
        UserEntity user = userService.create(userMapper.map(userRequestDto));
        return userMapper.map(user);
    }

/*    @OPTIONS
    @Consumes(MediaType.APPLICATION_JSON)
    public UserResponseDto create(@FormParam("userName") String userName) {
        UserEntity user = new UserEntity();
        //UserEntity user = userService.create(userMapper.map(userRequestDto));
        return userMapper.map(user);
    }*/


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public  List<UserResponseDto> get(@QueryParam("type") String type) {
        List<UserEntity> users = userService.getByType(type);
        List<UserResponseDto> response = users.stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
        return response;
        //return Response.ok(response).build();
    }
}
