package com.onlyas.multidata.service.one.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onlyas.multidata.dao.one.TUsersMapper;
import com.onlyas.multidata.domain.one.TUsers;
import com.onlyas.multidata.service.one.TUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TUsersServiceImpl implements TUsersService {

    @Autowired
    TUsersMapper usersMapper;

    @Override
    public List<TUsers> list() {
        return usersMapper.selectByExample(null);
    }

    @Override
    public PageInfo<TUsers> page() {
        PageHelper.startPage(1, 10);
        List<TUsers> list = usersMapper.selectByExample(null);
        PageInfo<TUsers> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


}
