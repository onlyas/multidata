package com.onlyas.multidata.controller.one;

import com.github.pagehelper.PageInfo;
import com.onlyas.multidata.domain.one.TUsers;
import com.onlyas.multidata.service.one.TUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/one/users")
public class UsersController {

    @Autowired
    TUsersService tUsersService;

    @GetMapping("/list")
    public List<TUsers> list() {
        return tUsersService.list();
    }

    @GetMapping("/page")
    public PageInfo<TUsers> page() {
        return tUsersService.page();
    }
}
