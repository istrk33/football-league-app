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
//Point 1: creation d'objet Player avec getteurs et setteurs
// Point 4 : Classe abstraite avec méthodes nécessaire à ses classes dérivées
public abstract class Player {

    private final int idPlayer;
    private final String name;
    private final int myClubId;
    private final String nationality;
    private final String position;
    private final int average;
    private final int age;
    protected int assists;
    protected int goals;
    protected int cleanSheets;

    /**
     * create new possible scorer players
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
     */
    //Point 2: surcharge du constructeur de Player
    public Player(int idPlayer, String name, int myClubId, String nationality, String position, int average, int age, int assists, int goals) {
        this.idPlayer = idPlayer;
        this.name = name;
        this.myClubId = myClubId;
        this.nationality = nationality;
        this.position = position;
        this.average = average;
        this.age = age;
        this.assists = assists;
        this.goals = goals;
    }

    /**
     * create a new goalkeeper
     *
     * @param idPlayer
     * @param name
     * @param myClubId
     * @param nationality
     * @param position
     * @param average
     * @param age
     * @param cleanSheet
     */
    //Point 2: surcharge du constructeur de Player
    public Player(int idPlayer, String name, int myClubId, String nationality, String position, int average, int age, int cleanSheet) {
        this.idPlayer = idPlayer;
        this.name = name;
        this.myClubId = myClubId;
        this.nationality = nationality;
        this.position = position;
        this.average = average;
        this.age = age;
        this.cleanSheets = cleanSheet;
    }

    /**
     *
     * @return player id
     */
    public int getIdPlayer() {
        return idPlayer;
    }

    /**
     *
     * @return name and last name
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @param name complete name
     * @return only name
     */
    //Point 2: Surcharge de la méthode getName
    public String getName(String name){
        return name.split(" ").toString();
    }

    /**
     *
     * @return club id of player
     */
    public int getMyClubId() {
        return myClubId;
    }

    /**
     *
     * @return nationality of player
     */
    public String getNationality() {
        return nationality;
    }

    /**
     *
     * @return position of player
     */
    public String getPosition() {
        return position;
    }

    /**
     *
     * @return average of player
     */
    public int getAverage() {
        return average;
    }

    /**
     *
     * @return age of player
     */
    public int getAge() {
        return age;
    }

    /**
     * 
     * @return season info on the player 
     */
    public int[] getSeasonInfosOnPlayer() {
        int [] tab=new int[2];
        tab[0]=assists;
        tab[1]=goals;
        return tab;
    }
}
