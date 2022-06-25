package com.evol.service.feign;

import com.evol.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "evol-user-server")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/list")
    List<UserDTO> getUsers();

    @RequestMapping(method = RequestMethod.POST, value = "/users/upsert/{userId}", consumes = "application/json")
    UserDTO update(@PathVariable("userId") Long userId, UserDTO user);

}
