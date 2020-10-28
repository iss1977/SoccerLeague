import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Team {
    String name;
    Integer points;
    Integer goalsShot;
    Integer goalsReceived;
    Integer wins;
    Integer defeats;
    Integer draws;

    public Team(String name) {
        this.name = name;
        this.points = 0;
        this.goalsShot = 0;
        this.goalsReceived = 0;
        this.wins = 0;
        this.defeats =0;
        this.draws = 0;
    }


    public void addGame(Game newGame){
        // we must check if we are the winners or the loosers ....
        Integer myGoals, otherGoals;
        myGoals = (newGame.getHomeTeam()==this)?newGame.getGoalsForHomeTeam(): newGame.getGoalsForGuestTeam();
        otherGoals = (newGame.getHomeTeam()!=this)?newGame.getGoalsForHomeTeam(): newGame.getGoalsForGuestTeam();
        this.goalsShot += myGoals;
        this.goalsReceived += otherGoals;
        this.wins += (myGoals>otherGoals)?1:0;
        this.defeats+= (myGoals<otherGoals)?1:0;
        this.draws +=  (myGoals.equals(otherGoals))?1:0;
        this.points += (newGame.getHomeTeam()==this)?newGame.getPointsForHomeTeam(): newGame.getPointsForGuestTeam();

    }
    public Integer getPoints(){
        return points;
    }

    public Integer getPointsUsingLambda(){
        Integer points;
        LinkedList<Game> tempGames = HelloFX.myLeague.getGameTable();
        List<Game> selectedGames;

        Integer myGoals, otherGoals;
        myGoals = (newGame.getHomeTeam()==this)?newGame.getGoalsForHomeTeam(): newGame.getGoalsForGuestTeam();
        otherGoals = (newGame.getHomeTeam()!=this)?newGame.getGoalsForHomeTeam(): newGame.getGoalsForGuestTeam();



        selectedGames= tempGames.stream()
                .filter(game->game.getHomeTeam()==this)
                .collect(Collectors.toList());
        points= selectedGames.stream().mapToInt(g->1).sum();
        return points;
    }


    public Integer  getGoalDifference(){
        return this.goalsShot-this.goalsReceived;
    }

    public String getName(){
        return name.trim();
    }
    public Integer getWins(){
        return wins;
    }

    public Integer getDraws(){
        return draws;
    }

    public Integer getDefeats() {
        return defeats;
    }

    public Integer getGoalsShot(){
        return goalsShot;
    }

    public Integer getGoalsReceived() {
        return goalsReceived;
    }

    public Integer compareTo( Team compTeam){
        return 1;
    }

    @Override
    public boolean equals(Object teamToCompare){
        teamToCompare = (Team)teamToCompare;
        return this.getName().equals(((Team) teamToCompare).getName());
    }
}
