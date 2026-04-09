package edu.ban7.chatbotmsnmsii2527.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;


    @ManyToMany
    @JoinTable(name= "question_include_tag", joinColumns = @JoinColumn(name="question_id"), inverseJoinColumns = @JoinColumn(name="tag_id"))
    private List<Tag>includeTags;

    @ManyToMany
    @JoinTable(name= "question_exclude_tag", joinColumns = @JoinColumn(name="question_id"), inverseJoinColumns = @JoinColumn(name="tag_id"))

    private List<Tag>excludeTags;

}
