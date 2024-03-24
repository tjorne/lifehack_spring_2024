package app.controllers;

import app.entities.Quote;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.QuotesMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class QuotesGeneratorController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/QuotesGenerator", ctx -> index(ctx, connectionPool));
        app.get("/createQuote", ctx -> ctx.render("/QuotesGenerator/createQuote.html"));
        app.post("/createQuote", ctx -> create(ctx, connectionPool));

        app.post("/newQuote", ctx -> newQuote(ctx, connectionPool));
        app.post("/displayHappyQuotes", ctx -> happyQuotes(ctx, connectionPool));
        app.post("/displaySadQuotes", ctx -> sadQuotes(ctx, connectionPool));
        app.post("displayMotivationalQuotes", ctx -> motivationalQuotes(ctx, connectionPool));
        app.post("displaySillyQuotes", ctx -> sillyQuotes(ctx, connectionPool));
        app.post("displayLeagueQuotes", ctx -> leagueQuotes(ctx, connectionPool));
        app.post("displayUserGeneratedQuotes", ctx -> userGeneratedQuotes(ctx, connectionPool));
    }

    public static void index(Context ctx, ConnectionPool connectionPool) {
        try {
            String newRandomQuote = QuotesMapper.getRandomQuote(QuotesMapper.loadQuotes(connectionPool));
            ctx.attribute("newRandomQuote", newRandomQuote);
            ctx.render("/QuotesGenerator/index.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
        }
    }

    private static void newQuote(Context ctx, ConnectionPool connectionPool) {
        try {
            String newRandomQuote = QuotesMapper.getRandomQuote(QuotesMapper.loadQuotes(connectionPool));
            ctx.attribute("newRandomQuote", newRandomQuote);
            ctx.render("/QuotesGenerator/index.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
        }
    }

    private static void happyQuotes(Context ctx, ConnectionPool connectionPool) {
        try {
            String newRandomQuote = QuotesMapper.getRandomQuote(QuotesMapper.loadHappyQuotes(connectionPool));
            ctx.attribute("newRandomQuote", newRandomQuote);
            ctx.render("/QuotesGenerator/index.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
        }
    }

    private static void sadQuotes(Context ctx, ConnectionPool connectionPool) {
        try {
            String newRandomQuote = QuotesMapper.getRandomQuote(QuotesMapper.loadSadQuotes(connectionPool));
            ctx.attribute("newRandomQuote", newRandomQuote);
            ctx.render("/QuotesGenerator/index.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
        }
    }

    private static void motivationalQuotes(Context ctx, ConnectionPool connectionPool) {
        try {
            String newRandomQuote = QuotesMapper.getRandomQuote(QuotesMapper.loadMotivationalQuotes(connectionPool));
            ctx.attribute("newRandomQuote", newRandomQuote);
            ctx.render("/QuotesGenerator/index.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
        }
    }

    private static void sillyQuotes(Context ctx, ConnectionPool connectionPool) {
        try {
            String newRandomQuote = QuotesMapper.getRandomQuote(QuotesMapper.loadSillyQuotes(connectionPool));
            ctx.attribute("newRandomQuote", newRandomQuote);
            ctx.render("/QuotesGenerator/index.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
        }
    }

    private static void leagueQuotes(Context ctx, ConnectionPool connectionPool) {
        try {
            String newRandomQuote = QuotesMapper.getRandomQuote(QuotesMapper.loadLeagueQuotes(connectionPool));
            ctx.attribute("newRandomQuote", newRandomQuote);
            ctx.render("/QuotesGenerator/index.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
        }
    }

    private static void userGeneratedQuotes(Context ctx, ConnectionPool connectionPool) {
        try {
            String newRandomQuote = QuotesMapper.getRandomQuote(QuotesMapper.loadUserGeneratedQuotes(connectionPool));
            ctx.attribute("newRandomQuote", newRandomQuote);
            ctx.render("/QuotesGenerator/index.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
        }
    }

    private static void create(Context ctx, ConnectionPool connectionPool) {
        String quoteBody = ctx.formParam("quoteBody");

        if (quoteBody.length() >= 3){
            try {
                QuotesMapper.createUserQuote("userGenerated", quoteBody, connectionPool);
                ctx.attribute("message", "Dit quote er hermed oprettet ");
                ctx.render("/QuotesGenerator/index.html");
            } catch (DatabaseException e) {
                ctx.attribute("message", "Dit quote findes allerede! Prøv igen");
                ctx.render("/QuotesGenerator/createQuote.html");
            }
        } else {
            ctx.attribute("message", "Dit quote kan ikke være tomt, og skal være mindst 3 karakterer langt! Prøv igen");
            ctx.render("/QuotesGenerator/createQuote.html");
        }
    }

}