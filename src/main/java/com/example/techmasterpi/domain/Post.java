package com.example.techmasterpi.domain;


import com.example.techmasterpi.model.Category;
import com.example.techmasterpi.model.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Post {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postid;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private LocalDate postdate;

    @Column
    private Integer countlike;

    @Column
    @Enumerated(EnumType.STRING)
    private State state;

    @Column
    private Integer views;

    @OneToMany(mappedBy = "postComment")
    private Set<Comment> postCommentComments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_post_id")
    private User userPost;



    @ManyToMany
    private Set<Tag>  pOSTTAGTags;


}
