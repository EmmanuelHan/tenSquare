package com.tensquare.article.config.project;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Constant {

    @Value("${project.mongodb.database:comment}")
    private String database;

}
