package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import java.util.List;

@Component(value="testMapper")
public interface TestMapper {

    List listAll() throws Exception;
}
