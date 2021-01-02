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
//Point 1: creation d'objet GoalKeeper avec getteurs et setteurs
//Point 8: Héritage, cette classe hérite de Player.java
public class GoalKeeper extends Player {

    boolean bestGoalKeeper = false;

    /**
     * goalkeeper
     *
     * @param idPlayer
     * @param name
     * @param myClubId
     * @param nationality
     * @param position
     * @param average
     * @param age
     * @param cleanSheet
     * @param bestGoalKeeper
     */
    //Point 6:Utilisation du constructeur de Player dans la classe fille GoalKeeper
    public GoalKeeper(int idPlayer, String name, int myClubId, String nationality, String position, int average, int age, int cleanSheet, boolean bestGoalKeeper) {
        super(idPlayer, name, myClubId, nationality, position, average, age, cleanSheet);
        this.bestGoalKeeper = bestGoalKeeper;
    }

    public void doBestGK() {
        bestGoalKeeper = true;
    }

    public boolean getIfBestGK() {
        return bestGoalKeeper;
    }

    /**
     * add cleanSheets
     */
    public void addCleanSheet() {
        cleanSheets++;
    }

    //Point 5 : Redéfinition de la méthode, uniquement pour la classe GoalKeeper
    @Override
    public int[] getSeasonInfosOnPlayer() {
        int[] tab = new int[1];
        tab[0] = cleanSheets;
        return tab;
    }
}
