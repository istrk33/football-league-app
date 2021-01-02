/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.data;

/**
 *
 * @author isoyturk
 */
//Point 1: creation d'objet Midfielder avec getteurs et setteurs
//Point 8: Héritage, cette classe hérite de Player.java
//Point 3 : utilisation de l'interface PlayerActions
public class Midfielder extends Player implements PlayerActions {

    boolean bestScorer = false;
    boolean bestAssister = false;

    /**
     * *
     * midfielder
     *
     * @param idPlayer
     * @param name
     * @param myClubId
     * @param nationality
     * @param position
     * @param average
     * @param age
     * @param assists
     * @param goals
     * @param bestScorer
     * @param bestAssister
     */
    //Point 6:Utilisation du constructeur de Player dans la classe fille Midfielder
    public Midfielder(int idPlayer, String name, int myClubId, String nationality, String position, int average, int age, int assists, int goals, boolean bestScorer, boolean bestAssister) {
        super(idPlayer, name, myClubId, nationality, position, average, age, assists, goals);
        this.bestScorer = bestScorer;
        this.bestAssister = bestAssister;
    }

    /**
     * to do the midfielder best scorer
     */
    @Override
    public void doBestScorer() {
        bestScorer = true;
    }

    /**
     * to do the midfielder best assister
     */
    @Override
    public void doBestAssister() {
        bestScorer = true;
    }

    public boolean getIfBestScorer() {
        return bestScorer;
    }

    public boolean getIfBestAssister() {
        return bestAssister;
    }

    /**
     * add goal
     */
    @Override
    public void addGoal() {
        goals++;
    }

    /**
     * add assist
     */
    @Override
    public void addAssist() {
        assists++;
    }

}
