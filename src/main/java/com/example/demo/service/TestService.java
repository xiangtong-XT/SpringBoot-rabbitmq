package com.example.demo.service;

import com.example.demo.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testService")
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List listAll() throws Exception{
        return testMapper.listAll();
    }
}
