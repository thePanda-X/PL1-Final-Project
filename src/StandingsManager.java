import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;


 /*
 * this script is responsible for Managing the standings of the teams.
 * it will store the teams in an arraylist and will update the teams based on the user input.
 * it will also print the standings to the user.
 */
public class StandingsManager {
    ArrayList<Team> teams = new ArrayList<>();

    //this method recollects the user data for a match and adds or updates the data.
    public void addScores()
    {
        //declare the necessary variables
        Scanner sc = new Scanner(System.in);
        String home, visitor;
        int homeGoals = 0, visitingGoals=0;


        //ask user for the information
        System.out.print("Home team: ");
        home = sc.nextLine();
        System.out.print("Visiting team: ");
        visitor = sc.nextLine();
        homeGoals = safeInt("Goals for home team: ");
        visitingGoals = safeInt("Goals for Visiting team: ");

        //if the score is a draw store a 2 if home wins store a 1 if visitor wins store a 0
        //int resultOfGame = (homeGoals == visitingGoals) ? 2 : ((homeGoals > visitingGoals) ? 1 : 0);
        int resultOfGame = 0;

        //print the result of the game to the user and give feedback.
        if(homeGoals == visitingGoals){
            resultOfGame=2; //draw
            System.out.println("The game was a Draw! Score added!");
        }else if(homeGoals > visitingGoals){
            resultOfGame = 1; //home team wins
            System.out.println(home + " won " + homeGoals + " - " + visitingGoals + "! Score added!");
        }else{
            resultOfGame = 0; // visitor wins
            System.out.println(visitor + " won " + visitingGoals + " - " + homeGoals + "! Score added!");
        }

        boolean updatedHome = false;
        boolean updatedVisitor = false;


        //look for the team
        for (int i = 0; i < teams.size(); i++)
        {
            Team currTeam = teams.get(i); // the current team we are looking at.

            //check if the team exists not case-sensitive.
            if(currTeam.getName().equalsIgnoreCase(home)){
                //the home team exists, so we update it
                currTeam.addGoalsReceived(visitingGoals);
                currTeam.addGoalsScored(homeGoals);

                if(resultOfGame == 1){ currTeam.addWin(); }
                else if (resultOfGame == 0) { currTeam.addLoss(); }
                else { currTeam.addDraw(); }

                updatedHome = true;
            }

            //check if team exists not case-sensitive
            if(currTeam.getName().equalsIgnoreCase(visitor)){
                //the visiting team exists, so we update it
                currTeam.addGoalsReceived(homeGoals);
                currTeam.addGoalsScored(visitingGoals);

                if(resultOfGame == 0){ currTeam.addWin(); }
                else if (resultOfGame == 1) { currTeam.addLoss(); }
                else { currTeam.addDraw(); }

                updatedVisitor = true;
            }
        }

        if(!updatedHome){
            //if the home team doesn't exist then create it update it and add it to the list of teams.
            Team homeTeam = new Team (home);
            if(resultOfGame == 1){ homeTeam.addWin(); }
            else if (resultOfGame == 0) { homeTeam.addLoss(); }
            else { homeTeam.addDraw(); }

            homeTeam.addGoalsScored(homeGoals);
            homeTeam.addGoalsReceived(visitingGoals);

            teams.add(homeTeam);
        }

        if(!updatedVisitor){
            // if the visitor team doesn't exist then create it update it and add it to the list of teams.
            Team visitorTeam = new Team (visitor);
            if(resultOfGame == 0){ visitorTeam.addWin(); }
            else if (resultOfGame == 1) { visitorTeam.addLoss(); }
            else { visitorTeam.addDraw(); }

            visitorTeam.addGoalsScored(visitingGoals);
            visitorTeam.addGoalsReceived(homeGoals);

            teams.add(visitorTeam);
        }

        System.out.println("press ENTER to continue....");
        sc.nextLine();
    }

    public void ShowTeamsRaw(){
        for (int i = 0; i < teams.size(); i++)
        {
            teams.get(i).ShowRaw();
        }
    }


    public void showResults(){

        Scanner sc = new Scanner(System.in);
        // sort the list based on points
        boolean sorted = false;
        Team[] teamsToSort = new Team[teams.size()];

        //copy the teams to the array (this is extremely inefficient I know >_<)
        for(int i = 0; i < teams.size(); i++)
        {
            teamsToSort[i] = teams.get(i);
        }

        //basic bubble-sort
        while (!sorted) {
            sorted = true; // if nothing changes the array is sorted.
            for (int i = 0; i < teamsToSort.length - 1; i++)
            {
                if(teamsToSort[i].getPoints() < teamsToSort[i+1].getPoints())
                {
                    //if the current teams points is less than the next switch them
                    Team aux = teamsToSort[i];
                    teamsToSort[i] = teamsToSort[i+1];
                    teamsToSort[i+1] = aux;
                    sorted = false; // we made a change
                }
            }
        }


        System.out.println("Team\t\t\tWin\t\tDraw\tLost\tGoals\tPoints");
        // print the values
        for (int i = 0; i < teamsToSort.length; i++)
        {
            System.out.println(teamsToSort[i].toString());
        }

        System.out.println("press ENTER to continue....");
        sc.nextLine();
    }


    //method to return an in or ask for the value again if the value is not an int giving the user feedback.
    static int safeInt(String s){
        boolean failed = false;
        int input = 0;

        // ask for input until the input is valid
        do{
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(s);
                 input = sc.nextInt();
                failed = false;
            }catch (Exception e) {
                //the input was not a valid input
                System.out.println("\n please enter a valid number.\n");
                failed = true;
            }
        }while(failed);

        return input;
    }
}
