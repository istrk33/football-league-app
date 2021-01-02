/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importCsvFilesToDataBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;
import league.exception.NoSuchFileException;

/**
 *
 * @author Administrateur
 */
//Point 1: importation des données des fichiers csv et les placer dans une base
//de données
public class ImportDataFromCsv {

    String nomFichier = "data/baseDeDonnéesFoot.sqlite";
    String url = "jdbc:sqlite:" + nomFichier;
    Connection connexion;
    PreparedStatement ajout;

    public ImportDataFromCsv() {
    }

    /**
     * delete old file of sqlite
     */
    public void deleteOldDataBaseFile() {
        File file = new File("data/baseDeDonnéesFoot.sqlite");
        file.delete();
    }

    /**
     * create table and insert data into by a csv files
     *
     * @throws Exception
     */
    public void createTableOfData() throws Exception {

        try {
            //Création des tables
            Class.forName("org.sqlite.JDBC");
            connexion = DriverManager.getConnection(url);
            PreparedStatement creation = connexion.prepareStatement(
                    "create table if not exists clubs ("
                    + "idClub integer not null primary key ,"
                    + "name text not null,"
                    + "age int not null,"
                    + "formation text not null,"
                    + "nbTrophy int "
                    + ");"
            );
            creation.execute();

            creation = connexion.prepareStatement(
                    "create table if not exists players ("
                    + " idPlayer integer not null primary key,"
                    + " name text not null ,"
                    + " myClubId integer not null references clubs(idClub) ,"
                    + " nationality text not null,"
                    + " position text not null,"
                    + " average int not null,"
                    + " age int not null,"
                    + " nbAssists int,"
                    + " nbGoals int,"
                    + " nbCleanSheets int,"
                    + " bestAssister text,"
                    + " bestScorer text,"
                    + " bestGk text"
                    + ");"
            );
            creation.execute();

            creation = connexion.prepareStatement(
                    "create table if not exists stadiums ("
                    + " idStadium integer not null primary key,"
                    + " myClubId integer not null references clubs(idClub) ,"
                    + " stadiumName text not null ,"
                    + " capacity int not null"
                    + ");"
            );
            creation.execute();

            creation = connexion.prepareStatement(
                    "create table if not exists footballGames ("
                    + " idMatch integer not null primary key,"
                    + " homeClub text not null ,"
                    + " awayClub text not null ,"
                    + "stadium text not null,"
                    + " homeTeamGoal integer not null  ,"
                    + " awayTeamGoals integer not null  ,"
                    + "winnerClub text ,"
                    + "affluence integer not null"
                    + ");"
            );
            creation.execute();

            creation = connexion.prepareStatement(
                    "create table if not exists ranking ("
                    + " place integer not null,"
                    + " clubs text not null,"
                    + " average int ,"
                    + " scoredGoals int,"
                    + "concededGoals int,"
                    + " points int  "
                    + ");"
            );
            creation.execute();

            ajout = connexion.prepareStatement(
                    "insert into stadiums(myClubId,stadiumName,capacity) values (?,?,?)"
            );
            //Point 9: chargement des stades vers la base de données depuis un
            //fichier .csv
            String path = "csvFiles/stadiums.csv";
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                int myClubId, capacity;
                for (String line : reader.lines().collect(Collectors.toList())) {
                    String[] eachInfos = line.split(";");
                    myClubId = Integer.parseInt(eachInfos[1].trim());
                    capacity = Integer.parseInt(eachInfos[3].trim());
                    ajout.setInt(1, myClubId);
                    ajout.setString(2, eachInfos[2]);
                    ajout.setInt(3, capacity);
                    ajout.executeUpdate();
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new NoSuchFileException("pas de fichier csv contenant les stades");
            }

            ajout = connexion.prepareStatement(
                    "insert into players(name,myClubId,nationality,position,average,age,nbAssists,nbGoals,nbCleanSheets) values (?,?,?,?,?,?,?,?,?)"
            );
            path = "csvFiles/players.csv";
            //Point 9: chargement des joueurs vers la base de données depuis un
            //fichier .csv
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                int myClubId, average, age, nbAssists, nbGoals, nbCleanSheets;
                for (String line : reader.lines().collect(Collectors.toList())) {
                    String[] eachInfos = line.split(";");
                    myClubId = Integer.parseInt(eachInfos[2].trim());
                    average = Integer.parseInt(eachInfos[5].trim());
                    age = Integer.parseInt(eachInfos[6].trim());
                    ajout.setString(1, eachInfos[1]);
                    ajout.setInt(2, myClubId);
                    ajout.setString(3, eachInfos[3]);
                    ajout.setString(4, eachInfos[4]);
                    ajout.setInt(5, average);
                    ajout.setInt(6, age);
                    if (eachInfos[7].equals("NULL") && eachInfos[8].equals("NULL")) {
                        ajout.setNull(7, 0);
                        ajout.setNull(8, 0);
                    } else {
                        nbAssists = Integer.parseInt(eachInfos[7].trim());
                        nbGoals = Integer.parseInt(eachInfos[8].trim());
                        ajout.setInt(7, nbAssists);
                        ajout.setInt(8, nbGoals);
                    }
                    if (eachInfos[9].equals("NULL")) {
                        ajout.setNull(9, 0);
                    } else {
                        nbCleanSheets = Integer.parseInt(eachInfos[9].trim());
                        ajout.setInt(9, nbCleanSheets);
                    }
                    ajout.executeUpdate();
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new NoSuchFileException("pas de fichier csv contenant les joueurs");
            }

            ajout = connexion.prepareStatement(
                    "insert into clubs(idClub,name,age,formation,nbTrophy) values (?,?,?,?,?)"
            );
            path = "csvFiles/clubs.csv";
            //Point 9: chargement des clubs vers la base de données depuis un
            //fichier .csv
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                int idClub, age, nbTrophy;
                for (String line : reader.lines().collect(Collectors.toList())) {
                    String[] eachInfos = line.split(";");
                    idClub = Integer.parseInt(eachInfos[0].trim());
                    age = Integer.parseInt(eachInfos[2].trim());
                    nbTrophy = Integer.parseInt(eachInfos[4].trim());
                    ajout.setInt(1, idClub);
                    ajout.setString(2, eachInfos[1]);
                    ajout.setInt(3, age);
                    ajout.setString(4, eachInfos[3]);
                    ajout.setInt(5, nbTrophy);
                    ajout.executeUpdate();
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new NoSuchFileException("pas de fichiers csv clubs");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
