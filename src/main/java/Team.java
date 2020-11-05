import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    private class MyGameResults{
        Integer points, goalsShot, goalsReceived, wins, defeats, draws;

        public MyGameResults(Integer points, Integer goalsShot, Integer goalsReceived, Integer wins, Integer defeats, Integer draws) {
            this.points = points;
            this.goalsShot = goalsShot;
            this.goalsReceived = goalsReceived;
            this.wins = wins;
            this.defeats = defeats;
            this.draws = draws;
        }

        public MyGameResults() {
            this.points = 0;
            this.goalsShot = 0;
            this.goalsReceived = 0;
            this.wins = 0;
            this.defeats = 0;
            this.draws = 0;
        }

        public Integer getPoints() {
            return points;
        }
    }

    public MyGameResults extractMyResultsFromGame(Game game){
        int
                myGoals = (game.getHomeTeam().equals(this))?game.getGoalsForHomeTeam(): (game.getGuestTeam().equals(this) ? game.getGoalsForGuestTeam():-1 ) ,
                otherGoals =  (game.getHomeTeam().equals(this))?game.getGoalsForGuestTeam(): (game.getGuestTeam().equals(this) ? game.getGoalsForHomeTeam(): -1);


        MyGameResults myGameResults ;

        if (myGoals != -1 && otherGoals != -1)
        myGameResults  = new MyGameResults(
                (myGoals>otherGoals)?3:((myGoals==otherGoals)?1:0), //points
                myGoals, //goalsShot
                otherGoals, //goalsReceived
                (myGoals>otherGoals)?1:0, //wins
                (myGoals<otherGoals)?1:0, //defeats
                (myGoals == otherGoals)?1:0 //draws
        );
        else
            myGameResults  = new MyGameResults();

//        System.out.println("Extracting game results ...I am "+this.getName()+". Game teams are : Home Team:"+game.getHomeTeam().getName()+", Guest Team:"+game.getGuestTeam().getName()+" My points :"+myGameResults.getPoints());

        return myGameResults;
    }

    public void addGame(Game newGame){

        MyGameResults myGameResults = extractMyResultsFromGame(newGame);

        this.points += myGameResults.getPoints();
        this.goalsShot += myGameResults.goalsShot;
        this.goalsReceived += myGameResults.goalsReceived;
        this.wins += myGameResults.wins;
        this.defeats+= myGameResults.defeats;
        this.draws += myGameResults.draws;
    }
    public Integer getPoints(){
        return points;
    }

    public Integer getPointsUsingLambda(League sourceLeague){
        // why send here League? to remain flexible.
        // + easy usage: from League class we call this method : "getPointsUsingLambda(this)"

        LinkedList<Game> listOfGamesInLeague = sourceLeague.getGameTable();

        int points =
                listOfGamesInLeague.stream()
                .map(this::extractMyResultsFromGame)
                .mapToInt(MyGameResults::getPoints)
                .sum();

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
