package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;

public class WarhammerController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/warhammer", ctx -> ctx.redirect("http://bases.tjorne.dk/"));
    }
}
