package ru.geekbrains;

@TestC
public class Football {

    @TestF
    private int scoreA;

    @TestF
    private int scoreB;

    @TestF
    public int countTime;

    @TestF
    private int judgeFactor;

    public final String nameTeamA = "Spartac";

    public final String nameTeamB = "Loko";

    public Football(int judgeFactor){
        this.judgeFactor = judgeFactor;
    }

    @BeforeSuite
    public void startGame(String msgStartGame){
        System.out.println(msgStartGame + ", " + nameTeamA + " vs " + nameTeamB);
    }

    private void goal (String nameTeam, int goal){
        switch (nameTeam){
            case nameTeamA:
                scoreA += goal;
                break;
            case nameTeamB:
                scoreB += goal;
                break;
        }
    }

    @TestM(priority = 1)
    private void playGame (){
        for (int i = 0; i < 3 ; i++){
            goal(nameTeamA, (int) (Math.random()*10));
            goal(nameTeamB, (int) (Math.random()*10));
            countTime++;
        }
    }

    @TestM(priority = 2)
    private void fines (int judgeFactor){
        getScore();
        scoreA -= judgeFactor;
        scoreB += judgeFactor;
        countTime++;
    }

    private String winner (){
        if(countTime == 0 ) return "game wasn't started";
        if(scoreA > scoreB) return nameTeamA;
        if(scoreB > scoreA) return nameTeamB;
        return "draw";
    }


    public void getScore() {
        if (countTime > 0) System.out.println(scoreA + ":" + scoreB);
    }

    @AfterSuite
    public void getWinner(){
        System.out.println(winner());
        getScore();
    }

}
