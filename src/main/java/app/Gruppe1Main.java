package app;

import app.gruppe1Controller.Gruppe1Controller;

import static app.Main.connectionPool;

public class Gruppe1Main
{
    public static void main(String[] args)
    {
        // ...........
        // Add routing
        Gruppe1Controller.addRoutes(app, connectionPool);
    }
}
