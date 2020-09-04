package com.ieng.huaimi.database.mapper;

import com.ieng.huaimi.database.domain.Token;

public interface TokenDao {
    int deleteByPrimaryKey(Long userId);

    int insert(Token record);

    int insertSelective(Token record);

    Token selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
}