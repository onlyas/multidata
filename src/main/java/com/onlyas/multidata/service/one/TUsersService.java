package com.onlyas.multidata.service.one;

import com.github.pagehelper.PageInfo;
import com.onlyas.multidata.domain.one.TUsers;

import java.util.List;

public interface TUsersService {
    List<TUsers> list();
    PageInfo<TUsers> page();
}
