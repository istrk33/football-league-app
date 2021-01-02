/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballLeague;

import club.data.Club;
import player.data.Player;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import player.data.Defender;
import player.data.Midfielder;
import player.data.Striker;

/**
 *
 * @author isoyturk
 */
//Point 1: creation de match de football, détermination des vainqeurs, buteurs
//et passeurs
public class FootballMatch {


    /**
     * create a new match
     */
    public FootballMatch() {

    }

    /**
     * get the average of the club in param
     * @param c required team
     * @return int average
     */
    public int getTeamAverage(Club c) {
        int sumClubAverage = 0;
        int teamAverage;
        for (Player p : c.getMyPlayers().values()) {
            sumClubAverage += p.getAverage();
        }
        teamAverage = sumClubAverage / c.getMyPlayers().size();
        return teamAverage;
    }

    /**
     * get th affluence of the match
     * @param c home club
     * @return affluence
     */
    public int getAffluence(Club c) {
        Random r = new Random();
        return r.nextInt(c.getMyStadium().getCapacity());
    }

    /**
     * get winner of the match
     * @param homeClub home team
     * @param awayClub away team
     * @param homeTeamGoals home team goals
     * @param awayTeamGoals away team goals
     * @return club winner
     */
    public String winnerOfTheGame(Club homeClub, Club awayClub, int homeTeamGoals, int awayTeamGoals) {
        if (homeTeamGoals > awayTeamGoals) {
            return homeClub.getClubName();
        } else if (homeTeamGoals < awayTeamGoals) {
            return awayClub.getClubName();
        } else {
            return "Match nul";
        }
    }

    /**
     * set score of two teams
     * @param homeClubAverage average of home team
     * @param awayClubAverage average of away team
     * @return array of two teams goals number in the match
     */
    public int[] setScoreMaxiTeam(int homeClubAverage, int awayClubAverage) {
        Random r = new Random();
        int nbGoalsHomeTeam, nbGoalsAwayTeam;
        int[] scores = new int[2];
        if (homeClubAverage > awayClubAverage) {
            nbGoalsHomeTeam = r.nextInt(5 + 1);
            nbGoalsAwayTeam = r.nextInt(2 + 1);
        } else {
            nbGoalsAwayTeam = r.nextInt(5 + 1);
            nbGoalsHomeTeam = r.nextInt(2 + 1);
        }
        scores[0] = nbGoalsHomeTeam;
        scores[1] = nbGoalsAwayTeam;
        return scores;
    }

    /**
     * add scorers
     * @param c
     * @param nbGoals
     * @return 
     */
    public ArrayList<Player> scorersOfTeam(Club c, int nbGoals) {
        Random r = new Random();
        HashMap<Integer, Player> clubsPossibleScorerPlayers = c.getMyPlayers();
        ArrayList<Player> possibleScorers = new ArrayList<>();
        ArrayList<Player> scorers = new ArrayList<>();
        for (Player p : clubsPossibleScorerPlayers.values()) {
            if (p.getPosition().equals("Goalkeeper")) {
                clubsPossibleScorerPlayers.remove(p);
            } else {
                possibleScorers.add(p);
            }
        }
        for (int n = nbGoals; n > 0; n--) {
            int i = r.nextInt(possibleScorers.size());
            switch (possibleScorers.get(i).getPosition()) {
                case "Defender":
                    Defender d = (Defender) possibleScorers.get(i);
                    d.addGoal();
                    scorers.add(d);
                    break;
                case "Midfielder":
                    Midfielder m = (Midfielder) possibleScorers.get(i);
                    m.addGoal();
                    scorers.add(m);
                    break;
                case "Striker":
                    Striker s = (Striker) possibleScorers.get(i);
                    s.addGoal();
                    scorers.add(s);
                    break;
            }
        }
        return scorers;
    }
    
    
     /**
     * add assisters
     * @param c
     * @param nbGoals
     * @return 
     */
    public ArrayList<Player> assistersOfTeam(Club c, int nbGoals) {
        int assists=nbGoals/2;
        Random r = new Random();
        HashMap<Integer, Player> clubsPossibleAssisterPlayers = c.getMyPlayers();
        ArrayList<Player> possibleAssisters = new ArrayList<>();
        ArrayList<Player> assisters = new ArrayList<>();
        for (Player p : clubsPossibleAssisterPlayers.values()) {
            if (p.getPosition().equals("Goalkeeper")) {
                clubsPossibleAssisterPlayers.remove(p);
            } else {
                possibleAssisters.add(p);
            }
        }
        for (int n = assists; n > 0; n--) {
            int i = r.nextInt(possibleAssisters.size());
            switch (possibleAssisters.get(i).getPosition()) {
                case "Defender":
                    Defender d = (Defender) possibleAssisters.get(i);
                    d.addAssist();
                    assisters.add(d);
                    break;
                case "Midfielder":
                    Midfielder m = (Midfielder) possibleAssisters.get(i);
                    m.addAssist();
                    assisters.add(m);
                    break;
                case "Striker":
                    Striker s = (Striker) possibleAssisters.get(i);
                    s.addAssist();
                    assisters.add(s);
                    break;
            }
        }
        return assisters;
    }
}
