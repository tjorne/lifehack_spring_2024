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
    }

    public static void index(Context ctx, ConnectionPool connectionPool) {
        try {
            String quote = ctx.attribute(QuotesMapper.getRandomQuote(QuotesMapper.loadQuotes(connectionPool)));
            ctx.attribute("quote", quote);
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
            throw new RuntimeException(e);
        }
    }

    private static void happyQuotes(Context ctx, ConnectionPool connectionPool) {
        try {
            String newRandomQuote = QuotesMapper.getRandomQuote(QuotesMapper.loadHappyQuotes(connectionPool));
            ctx.attribute("newRandomQuote", newRandomQuote);
            ctx.render("/QuotesGenerator/index.html");
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void create(Context ctx, ConnectionPool connectionPool) {
        String quoteBody = ctx.formParam("quoteBody");

        try {
            QuotesMapper.createUserQuote("userGenerated", quoteBody, connectionPool);
            ctx.attribute("message", "Dit quote er hermed oprettet ");

            List<Quote> quotesList = QuotesMapper.loadQuotes(connectionPool);
            ctx.attribute("quoteList", quotesList);

            ctx.render("/QuotesGenerator/index.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", "Quotet findes allerede. Prøv igen");
            ctx.render("/QuotesGenerator/createQuote.html");
        }
    }

}