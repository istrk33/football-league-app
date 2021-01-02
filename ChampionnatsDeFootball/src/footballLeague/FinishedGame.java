/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballLeague;

import club.data.Club;
import player.data.Player;
import java.util.ArrayList;

/**
 *
 * @author isoyturk
 */
//Point 1: créer un objet de match fini avec les getteurs et setteurs présent
public class FinishedGame {

    private final int idMatch;
    private final int nbGoalsHome;
    private final int nbGoalsAway;
    private final Club homeClub;
    private final Club awayClub;
    private final String winner;
    private final int affluence;
    //Point 7: utilisation d'une collection d'objet
    private final ArrayList< Player> homeTeamScorers;
    //Point 7: utilisation d'une collection d'objet
    private final ArrayList< Player> awayTeamScorers;
    //Point 7: utilisation d'une collection d'objet
    private final ArrayList< Player> homeTeamAssisters;
    //Point 7: utilisation d'une collection d'objet
    private final ArrayList< Player> awayTeamAssisters;

    /**
     * create new ended game
     *
     * @param idMatch
     * @param nbGoalsHome home goals number
     * @param nbGoalsAway away goals number
     * @param homeClub home club
     * @param awayClub away club
     * @param affluence number of spectators
     * @param HomeTeamScorers scorers of home team
     * @param AwayTeamScorers scorers of away team
     * @param homeTeamAssisters
     * @param awayTeamAssisters
     * @param winner winner of match
     */
    public FinishedGame(int idMatch, int nbGoalsHome, int nbGoalsAway, Club homeClub, Club awayClub, int affluence, ArrayList< Player> HomeTeamScorers, ArrayList<Player> AwayTeamScorers, ArrayList<Player> homeTeamAssisters, ArrayList<Player> awayTeamAssisters, String winner) {
        this.idMatch = idMatch;
        this.nbGoalsHome = nbGoalsHome;
        this.nbGoalsAway = nbGoalsAway;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.affluence = affluence;
        this.homeTeamScorers = HomeTeamScorers;
        this.awayTeamScorers = AwayTeamScorers;
        this.homeTeamAssisters = homeTeamAssisters;
        this.awayTeamAssisters = awayTeamAssisters;
        this.winner = winner;
    }

    /**
     *
     * @return match id
     */
    public int idMatch() {
        return idMatch;
    }

    /**
     *
     * @return home goals number
     */
    public int getNbGoalsHome() {
        return nbGoalsHome;
    }

    /**
     *
     * @return away goals number
     */
    public int getNbGoalsAway() {
        return nbGoalsAway;
    }

    /**
     *
     * @return home club
     */
    public Club getHomeClub() {
        return homeClub;
    }

    /**
     *
     * @return away club
     */
    public Club getAwayClub() {
        return awayClub;
    }

    /**
     *
     * @return nb spectators
     */
    public int getAffluence() {
        return affluence;
    }

    /**
     *
     * @return winner club
     */
    public String getWinner() {
        return winner;
    }

    /**
     *
     * @return home scorers of the match
     */
    public ArrayList<String> getHomeScorers() {
        ArrayList<String> nameOfHomeScorer = new ArrayList();
        for (Player p : homeTeamScorers) {
            nameOfHomeScorer.add(p.getName());
        }
        return nameOfHomeScorer;
    }

    /**
     *
     * @return away scorers of the match
     */
    public ArrayList<String> getAwayScorers() {
        ArrayList<String> nameOfAwayScorer = new ArrayList();
        for (Player p : awayTeamScorers) {
            nameOfAwayScorer.add(p.getName());
        }
        return nameOfAwayScorer;
    }

}
