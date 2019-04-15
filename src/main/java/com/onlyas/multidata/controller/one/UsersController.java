package com.onlyas.multidata.controller.one;

import com.github.pagehelper.PageInfo;
import com.onlyas.multidata.domain.one.TUsers;
import com.onlyas.multidata.dto.Result;
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
    public Result<List<TUsers>> list() {
        //return new Result(0,"找不到用户信息");
        return new Result<>(tUsersService.list());
    }

    @GetMapping("/page")
    public Result<PageInfo<TUsers>> page() {
        return new Result<>(tUsersService.page());
    }
}
