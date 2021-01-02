/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballLeague;

import club.data.Club;

/**
 *
 * @author isoyturk
 */
//Point 1: creation d'objets stades avec les getteurs et setteurs présent
public class Stadium {

    private final String stadiumName;
    private final int myClubId;
    private final int capacity;

    /**
     * create new stadium
     *
     * @param stadiumName
     * @param myClubId
     * @param capacity
     */
    public Stadium(String stadiumName, int myClubId, int capacity) {
        this.stadiumName = stadiumName;
        this.myClubId = myClubId;
        this.capacity = capacity;
    }

    /**
     *
     * @return stadiumName
     */
    public String getStadiumName() {
        return stadiumName;
    }

    /**
     *
     * @return club id of stadium
     */
    public int getMyClubId() {
        return myClubId;
    }

    /**
     *
     * @return capacity of stadium
     */
    public int getCapacity() {
        return capacity;
    }

}
