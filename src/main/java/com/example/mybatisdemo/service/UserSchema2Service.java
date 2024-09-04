package com.example.mybatisdemo.service;

import com.example.mybatisdemo.model.UserSchema2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSchema2Service {

    private final SqlSessionTemplate sqlSessionTemplate;

    public UserSchema2Service(@Qualifier("schema2SqlSessionTemplate") SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public UserSchema2 findById(Long id) {
        return sqlSessionTemplate.selectOne("UserMapperSchema2.findById", id);
    }

    public List<UserSchema2> findAll() {
        return sqlSessionTemplate.selectList("UserMapperSchema2.findAll");
    }

    public void save(UserSchema2 user) {
        if (user.getId() == null) {
            sqlSessionTemplate.insert("UserMapperSchema2.insert", user);
        } else {
            sqlSessionTemplate.update("UserMapperSchema2.update", user);
        }
    }

    public void delete(Long id) {
        sqlSessionTemplate.delete("UserMapperSchema2.delete", id);
    }
}
