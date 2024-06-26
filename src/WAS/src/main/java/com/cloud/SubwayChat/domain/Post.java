package com.cloud.SubwayChat.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post_tb")
@NoArgsConstructor
@Getter @Setter
public class Post extends TimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 10)
    private List<Comment> comments = new ArrayList<>();

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private PostType type;

    @Builder
    public Post(User user ,String title, String content, PostType type) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public void addComment(Comment comment){
        comments.add(comment);
        comment.updatePost(this);
    }

    public void updatePost(String title, String content, PostType type){
        this.title = title;
        this.content = content;
        this.type = type;
    }
}
