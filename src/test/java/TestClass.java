import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestClass {

    League testLeague;
    Game testGame;
    Team testTeamHome;
    Team testTeamGuest;


    @BeforeEach
    public void init(){
        System.out.println("Before each");
        this.testLeague = new League();
        this.testTeamHome = new Team("HomeTeam");
        this.testTeamGuest = new Team("GuestTeam");

    }
    @Test
    @DisplayName("Testing functionality ....")
    public void testLeague(){
        System.out.println("Running test Liga");

        System.out.println("Before each");
        this.testLeague = new League();
        this.testTeamHome = new Team("HomeTeam");
        this.testTeamGuest = new Team("GuestTeam");

        this.testGame = new Game(testTeamHome,testTeamGuest,0,3);
        testLeague.addGameResult(this.testGame); // this should result in modifying attributes in Team .... will check this nou for functionality

        // team 1 should receive points .... let's test that
        assertEquals(this.testTeamHome.getGoalsShot(),Integer.valueOf(0));
        assertEquals(this.testTeamGuest.getGoalsShot(),Integer.valueOf(3));
        // check if the points are okey ... Home should have 0 points, guest 3 points.
        assertEquals(this.testTeamHome.getPoints(),Integer.valueOf(0));
        assertEquals(this.testTeamGuest.getPoints(),Integer.valueOf(3));

        //  --------------     add a new Game and check the results
        testLeague.addGameResult(new Game(testTeamHome,testTeamGuest,2,1));

        assertEquals(this.testTeamHome.getGoalsShot(),Integer.valueOf(2));
        assertEquals(this.testTeamGuest.getGoalsShot(),Integer.valueOf(4));
        // we also check the points.
        assertEquals(this.testTeamHome.getPoints(),Integer.valueOf(3));
        assertEquals(this.testTeamGuest.getPoints(),Integer.valueOf(3));
        //  --------------     END OF TEST add a new Game and check the results

        // --------------- test: add a result as a new team with an existing name.
        testLeague.addGameResult(new Game(new Team("HomeTeam"),testTeamGuest,1,2));


        // goals should be now H:3 (2+1) and G: 4
        assertEquals(this.testTeamHome.getGoalsShot(),Integer.valueOf(3));
        assertEquals(this.testTeamGuest.getGoalsShot(),Integer.valueOf(6));
        // we also check the points.
        assertEquals(this.testTeamHome.getPoints(),Integer.valueOf(3));
        assertEquals(this.testTeamGuest.getPoints(),Integer.valueOf(6));





        assertTrue(true);

        System.out.println( this.testTeamHome.getPointsUsingLambda(this.testLeague) );
    }


}
