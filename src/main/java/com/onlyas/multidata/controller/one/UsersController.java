package com.onlyas.multidata.controller.one;

import com.onlyas.multidata.service.one.TUsersService;
import com.onlyas.multidata.utils.OK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/one/users")
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
