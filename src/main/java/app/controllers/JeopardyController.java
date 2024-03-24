package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class JeopardyController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/jeopardy", ctx -> index(ctx, connectionPool));
        app.post("/jeopardy/create", ctx -> create(ctx, connectionPool));
        app.post("/jeopardy/join", ctx -> join(ctx, connectionPool));
        app.get("/join", ctx -> ctx.render("jeopardy/join.html"));
    }

    private static void join(Context ctx, ConnectionPool connectionPool) {

        ctx.render("/jeopardy/join.html");
    }

    private static void create(Context ctx, ConnectionPool connectionPool) {
        ctx.attribute("create.html");
        ctx.render("create.html");
    }

    private static void index(Context ctx, ConnectionPool connectionPool) {
        ctx.render("/jeopardy/index.html");
    }
}