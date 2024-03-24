package app.entities;

public class JeopardyTeam {
    String teamName;
    int points;

    public JeopardyTeam(String teamName, int points) {
        this.teamName = teamName;
        this.points = points;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return teamName+" "+points;
    }
}
