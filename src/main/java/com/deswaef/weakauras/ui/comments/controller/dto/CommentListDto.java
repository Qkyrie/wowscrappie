package com.deswaef.weakauras.ui.comments.controller.dto;

public class CommentListDto {
    private Long id;
    private String commenter;
    private String content;
    private String when;

    public Long getId() {
        return id;
    }

    public CommentListDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCommenter() {
        return commenter;
    }

    public CommentListDto setCommenter(String commenter) {
        this.commenter = commenter;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentListDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getWhen() {
        return when;
    }

    public CommentListDto setWhen(String when) {
        this.when = when;
        return this;
    }
}
