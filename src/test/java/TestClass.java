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
        assertEquals((long)this.testTeamHome.getGoalsShot(),0L);

        assertTrue(true);
    }


}
