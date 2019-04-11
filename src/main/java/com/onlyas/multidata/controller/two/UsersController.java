package com.onlyas.multidata.controller.two;

import com.onlyas.multidata.service.two.TUsersService;
import com.onlyas.multidata.dto.OK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/two/users")
public class UsersController {

    @Autowired
    TUsersService tUsersService;

    @GetMapping("/list")
    public OK list() {
        return new OK(tUsersService.list());
    }

    @GetMapping("/page")
    public OK page() {
        return new OK(tUsersService.page());
    }
}
