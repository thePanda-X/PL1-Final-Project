/*
 * this class defines what a team is.
 * a team has a name and their scores.
 * the scores are wins, draws, losses, goals scored, goals received and points.
 * the points are calculated by the following formula:
 * points = (wins * 3) + draws
 */
public class Team {
    int wins, draws, losses, goalsScored, goalsReceived, points;
    String name = "";

    public Team (String name)
    {
        this.name = name; // store the name the first time it was inputed.

    }


    public String getName()
    {
        return this.name;
    }
    public int getWins(){
        return this.wins;
    }
    public int getDraws() {
        return this.draws;
    }

    public int getLosses(){
        return this.losses;
    }

    public int getGoalsScored(){
        return this.goalsScored;
    }

    public int getGoalsReceived(){
        return this.goalsReceived;
    }

    public int getPoints() {
        //calculate the points
        this.points = (wins * 3) + draws; //this is because wins are worth 3 points and draws 1 loses don't change anything.
        return this.points;
    }

    public void addWin(){
        this.wins ++;
    }

    public void addDraw(){
        this.draws ++;
    }

    public void addLoss(){
        this.losses ++;
    }

    public void addGoalsReceived(int goalsReceived){
        this.goalsReceived += goalsReceived;
    }

    public void addGoalsScored(int goalsScored){
        this.goalsScored += goalsScored;
    }

    public void ShowRaw()
    {
        System.out.println("-------------------------------------");
        System.out.println("Name: " + name);
        System.out.println("wins: " + wins);
        System.out.println("losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.println("goals: " + goalsScored + " - " + goalsReceived);
        System.out.println("points: " + getPoints());
        System.out.println("-------------------------------------");

    }

    public String toString(){
        return "" + name + "\t\t" + wins +  "\t\t" + draws + "\t\t" + losses + "\t\t"
                + goalsScored + "-" + goalsReceived + "\t\t" + getPoints();
    }
}
