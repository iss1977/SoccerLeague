import java.util.LinkedList;
import java.util.ListIterator;

public class League {
    LinkedList<Team> teamTable;
    LinkedList<Game> gameTable;

    public League() {
        teamTable = new LinkedList<Team>();
        gameTable = new LinkedList<Game>();
    }

    public void addGameResult ( Game game){

        // add the Game to gameTable
        gameTable.add(game);


        Team teamHome;
        if (teamTable.indexOf(game.getHomeTeam())== -1){ // Team existiert nicht, add

            teamTable.add(game.getHomeTeam());
            teamHome = teamTable.getLast();
            System.out.println(String.format("Team %s was added to the TeamList.",teamHome.getName()) );
        }else{
            teamHome = teamTable.get(teamTable.indexOf(game.getHomeTeam()));
            System.out.println(String.format("Team %s was found to the TeamList.",teamHome.getName()) );
        }

        Team teamGuest;
        if (teamTable.indexOf(game.getGuestTeam())== -1){ // Team existiert nicht, add
            teamTable.add(game.getGuestTeam());
            teamGuest = teamTable.getLast();
            System.out.println(String.format("Team %s was added to the TeamList.",teamGuest.getName()) );
        }else{
            teamGuest = teamTable.get(teamTable.indexOf(game.getGuestTeam()));
            System.out.println(String.format("Team %s was found to the TeamList.",teamGuest.getName()) );
        }

        teamHome.addGame(game);
        teamGuest.addGame(game);
        System.out.printf("Results for team %s and %s was edded.%n",teamHome.getName(),teamGuest.getName());

    }
    public LinkedList<Team> getTeamTable(){
        return this.teamTable;
    }
    public LinkedList<Game> getGameTable(){
        return this.gameTable;
    }



    public String  listTeamAsTable(){

        ListIterator new_list = teamTable.listIterator(0);
        Team tempTeam ;
        Integer teamNameLenght2;

        final StringBuilder sb = new StringBuilder("┌           Team             ┬ Points ┬ Goals Shot ┬ Goals Received ┬ Wins ┬ Defeats ┬ Draws ┐");
        sb.append("\n");

        while(new_list.hasNext()) {
            tempTeam= (Team)new_list.next();
            sb.append("│");
            teamNameLenght2 = (int)(tempTeam.getName().length()/2);

            sb.append(String.format(" ".repeat(teamNameLenght2)+ "%s"+" ".repeat(28-tempTeam.getName().length()-teamNameLenght2), tempTeam.getName()));
            sb.append("│");
            sb.append(String.format("%5d   ",tempTeam.getPoints()));
            sb.append("│");
            sb.append(String.format("%8d    ",tempTeam.getGoalsShot()));
            sb.append("│");
            sb.append(String.format("%10d      ",tempTeam.getPoints()));
            sb.append("│");
            sb.append(String.format("%4d  ",tempTeam.getWins()));
            sb.append("│");
            sb.append(String.format("%6d   ",tempTeam.getDraws()));
            sb.append("│");
            sb.append(String.format("%5d  ",tempTeam.getDefeats()));
            sb.append("│");


            sb.append("\n");
        }
        sb.append("└────────────────────────────────────────────────────────────────────────────────────────────┘");

        return sb.toString();
    } // end of listTeamAsTable()

} // end of class Main
