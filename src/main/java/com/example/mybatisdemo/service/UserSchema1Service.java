package com.example.mybatisdemo.service;

import com.example.mybatisdemo.model.UserSchema1;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSchema1Service {

    private final SqlSessionTemplate sqlSessionTemplate;

    public UserSchema1Service(@Qualifier("schema1SqlSessionTemplate") SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public UserSchema1 findById(Long id) {
        return sqlSessionTemplate.selectOne("UserMapperSchema1.findById", id);
    }

    public List<UserSchema1> findAll() {
        return sqlSessionTemplate.selectList("UserMapperSchema1.findAll");
    }

    public void save(UserSchema1 user) {
        if (user.getId() == null) {
            sqlSessionTemplate.insert("UserMapperSchema1.insert", user);
        } else {
            sqlSessionTemplate.update("UserMapperSchema1.update", user);
        }
    }

    public void delete(Long id) {
        sqlSessionTemplate.delete("UserMapperSchema1.delete", id);
    }
}
