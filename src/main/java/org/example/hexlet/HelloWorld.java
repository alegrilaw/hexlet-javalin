package org.example.hexlet;

import io.javalin.Javalin;
import java.util.Collections;
import org.example.hexlet.dao.CourseDao;
import org.example.hexlet.dto.courses.CoursesPage;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // Описываем, что загрузится по адресу /
        app.get("/", ctx -> ctx.render("index.jte"));
        app.get("/users", ctx -> ctx.result("GET /users"));
        app.post("/users", ctx -> ctx.result("POST /users"));

        app.get("/hello", ctx -> {
            var name = ctx.queryParam("name");
            if (name == null || name.isEmpty()) {
                name = "World";
            }

            ctx.result(String.format("Hello, %s!", name));
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var courses = CourseDao.getCourse(id);
            var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/users/{id}", ctx -> {
            ctx.result("User ID: " + ctx.pathParam("id"));
        });

        // Название параметров мы выбрали произвольно
        app.get("/courses/{courseId}/lessons/{id}", ctx -> {
            ctx.result(String.format("Course ID: %s%nLesson ID: %s",
                ctx.pathParam("courseId"),
                ctx.pathParam("id"))
            );
        });

        app.get("/users/{id}/post/{postId}", ctx -> {
            ctx.result(String.format("User ID: %s%nPost ID: %s",
                ctx.pathParam("id"),
                ctx.pathParam("postId"))
            );
        });

        app.start(7070); // Стартуем веб-сервер
    }
}
