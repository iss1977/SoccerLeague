import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class Main {

//    static League myLeague;
    public static void main(String[] args) {
        League myLeague;
        myLeague = readScoreFromFile();

//        System.out.println(myLeague.getTeamTable());
        System.out.println(myLeague.listTeamAsTable());

    }

    // ----------------------    Read the information from csv file ---------------------------------
    public static League readScoreFromFile(){
        String[] res;
        Team teamHome, teamGuest;
        Game gameRead;
        League myLeague = new League();
        boolean firstLine = true; //  the first line in our css file contains the header, this will be not read....
        try {
            File myObj = new File("SoccerLeague.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (firstLine){ // skip the first line with headers.
                    firstLine = false;
                    continue;
                }

                res = data.split("[,]", 0);
                teamHome = new Team(res[0].trim());
                teamGuest = new Team (res[1].trim());
                gameRead = new Game (teamHome,teamGuest,Integer.parseInt(res[2]),Integer.parseInt(res[3]));
                System.out.printf("Adding game result via myLeague.addGameResult. Home Team : %s , Guest Team: %s, Score %d - %d %n",teamHome.getName(),teamGuest.getName(),Integer.parseInt(res[2]),Integer.parseInt(res[3]));
                myLeague.addGameResult(gameRead);


            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



        return myLeague;
    }
}
