package com.onlyas.multidata.service.two;

import com.github.pagehelper.PageInfo;
import com.onlyas.multidata.domain.two.TUsers;

import java.util.List;

public interface TUsersService {
    List<TUsers> list();
    PageInfo<TUsers> page();
}
