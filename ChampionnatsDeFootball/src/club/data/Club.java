/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club.data;

import player.data.Player;
import java.util.HashMap;
import footballLeague.Stadium;

/**
 *
 * @author isoyturk
 */

//Point 1: creation d'objet club et getteur et setteur présent
public class Club {

    private final int idClub;
    private final String clubName;
    private final int clubAge;
    private final String formation;
    private final Stadium myStadium;
    private final int nbOfTrophy;
    private int points = 0;
    private int scoredGoals = 0;
    private int concededGoals = 0;
    private int average;
    //Point 7: utilisation d'une collection d'objet
    private final HashMap<Integer, Player> myPlayers;

    /**
     * create new club
     *
     * @param idClub
     * @param clubName
     * @param clubAge
     * @param formation
     * @param myStadium
     * @param nbOfTrophy
     * @param myPlayers list of player
     * @param points
     */
    public Club(int idClub, String clubName, int clubAge, String formation,
            Stadium myStadium, int nbOfTrophy, HashMap<Integer, Player> myPlayers, int points) {
        this.clubName = clubName;
        this.clubAge = clubAge;
        this.formation = formation;
        this.myStadium = myStadium;
        this.nbOfTrophy = nbOfTrophy;
        this.myPlayers = myPlayers;
        this.points = points;
        this.idClub = idClub;
    }

    /**
     *
     * @return id club
     */
    public int getIdClub() {
        return idClub;
    }

    /**
     *
     * @return nb trophy of the history of the club
     */
    public int getNbTrophy() {
        return nbOfTrophy;
    }

    /**
     *
     * @return club name
     */
    public String getClubName() {
        return clubName;
    }

    /**
     *
     * @return club age
     */
    public int getClubAge() {
        return clubAge;
    }

    /**
     *
     * @return formation
     */
    public String getFormation() {
        return formation;
    }

    /**
     *
     * @return the stadium of the club
     */
    public Stadium getMyStadium() {
        return myStadium;
    }

    /**
     *
     * @return the list of players
     */
    public HashMap<Integer, Player> getMyPlayers() {
        return myPlayers;
    }

    /**
     * add 3 points if club win
     */
    public void addWin() {
        points += 3;
    }

    /**
     * add 1 points if club do draw
     */
    public void addDraw() {
        points += 1;
    }

    /**
     * add nothing if club lose
     */
    public void addLose() {
        points += 0;
    }

    /**
     *
     * @return number of points
     */
    public int getPoints() {
        return points;
    }

    /**
     * add numbers of goals of a match
     *
     * @param goals nb of goals
     */
    public void addScoredGoals(int goals) {
        scoredGoals += goals;
    }

    /**
     * add conceded goals
     *
     * @param goals
     */
    public void addConcededGoals(int goals) {
        concededGoals += goals;
    }

    /**
     *
     * @return total scoreedgoals
     */
    public int getScoredGoals() {
        return scoredGoals;
    }

    /**
     *
     * @return total conceded goals
     */
    public int getConcededGoals() {
        return concededGoals;
    }

    /**
     *
     * @return average
     */
    public int getAverage() {
        average = scoredGoals - concededGoals;
        return average;
    }
}
