package com.tensquare.search.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Document(indexName = "tensquare", type = "article")
public class Article implements Serializable {

    @Id
    private String id;//ID

    /**
     * 是否索引，该域是否能被搜索
     * 是否分词，表示搜索的条件是整体匹配还是分词搜索
     * 是否存储，在页面上是否显示
     */
    @Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;//标题

    @Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;//内容

    private String state;//审核状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
