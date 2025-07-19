package org.example;

import org.example.bean.Lion;
import org.example.bean.Person;
import org.example.config.ProjectConfig;
import org.example.model.Comment;
import org.example.proxy.CommentNotificationProxy;
import org.example.proxy.EmailCommentNotificationProxy;
import org.example.repository.CommentRepository;
import org.example.repository.DBCommentRepository;
import org.example.service.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Comment comment = new Comment();
        comment.setAuthor("babylion");
        comment.setText("I'm hungry");

        var commentService = context.getBean(CommentService.class);
        commentService.publishComment(comment);
    }
}