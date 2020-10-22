public class Game {
    Team homeTeam;
    Team guestTeam;
    Integer goalsForHomeTeam;
    Integer goalsForGuestTeam;

    public Game(Team homeTeam, Team guestTeam, Integer goalsForHomeTeam, Integer goalsForGuestTeam) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.goalsForHomeTeam = goalsForHomeTeam;
        this.goalsForGuestTeam = goalsForGuestTeam;
    }


    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public Integer getGoalsForHomeTeam() {
        return goalsForHomeTeam;
    }

    public Integer getGoalsForGuestTeam() {
        return goalsForGuestTeam;
    }

    public Integer getPointsForHomeTeam() {
        Integer pointsForHomeTeam = 0;
        if (goalsForHomeTeam == goalsForGuestTeam ) pointsForHomeTeam = 1;
        if (goalsForHomeTeam > goalsForGuestTeam ) pointsForHomeTeam = 3;
        return pointsForHomeTeam;
    }

    public Integer getPointsForGuestTeam() {
        Integer pointsForGuestTeam = 0;
        if (goalsForHomeTeam == goalsForGuestTeam ) pointsForGuestTeam = 1;
        if (goalsForHomeTeam < goalsForGuestTeam ) pointsForGuestTeam = 3;
        return pointsForGuestTeam;
    }


}
