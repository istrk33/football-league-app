/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package championnatBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Random;

/**
 *
 * @author isoyturk
 */
public class championnatBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
        String nomFichier = "/mnt/roost/users/isoyturk/NetBeansProjects/ChampionnatsDeFootball/data/baseDeDonnéesFoot.sqlite";

        String url = "jdbc:sqlite:" + nomFichier;
        Random r = new Random();
        Connection connexion;

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
                    + " nbCleanSheets int"
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
                    + " idStadium integer not null primary key,"
                    + " homeClub integer not null references clubs(idClub) ,"
                    + " awayClub integer not null references clubs(idClub) ,"
                    + " awayTeamGoals integer not null  ,"
                    + " homeTeamGoals integer not null  ,"
                    + "strikers integer not null references players(idPlayer),"
                    + "assisters integer not null references players(idPlayer)"
                    + ");"
            );
            creation.execute();

            //Création des CLUBS
            PreparedStatement ajout;

            ajout = connexion.prepareStatement(
                    "insert into clubs(idClub,name,age,formation,nbTrophy) values (?,?,?,?,?)"
            );
            ajout.setInt(1, 1);
            ajout.setString(2, "Galatasaray");
            ajout.setInt(3, (2019 - 1905));
            ajout.setString(4, "4-3-3");
            ajout.setInt(5, 21);
            ajout.executeUpdate();

            ajout.setInt(1, 2);
            ajout.setString(2, "Fenerbahçe");
            ajout.setInt(3, (2019 - 1907));
            ajout.setString(4, "4-4-2");
            ajout.setInt(5, 19);
            ajout.executeUpdate();

            ajout.setInt(1, 3);
            ajout.setString(2, "Besiktas");
            ajout.setInt(3, (2019 - 1903));
            ajout.setString(4, "4-4-1-1");
            ajout.setInt(5, 15);
            ajout.executeUpdate();

            ajout.setInt(1, 4);
            ajout.setString(2, "Medipol Basaksehir");
            ajout.setInt(3, (2019 - 2014));
            ajout.setString(4, "3-5-2");
            ajout.setInt(5, 0);
            ajout.executeUpdate();

            ajout.setInt(1, 5);
            ajout.setString(2, "Trabzonspor");
            ajout.setInt(3, (2019 - 1967));
            ajout.setString(4, "5-2-3");
            ajout.setInt(5, 7);
            ajout.executeUpdate();

            ajout.setInt(1, 6);
            ajout.setString(2, "Sivasspor");
            ajout.setInt(3, (2019 - 1967));
            ajout.setString(4, "4-3-3");
            ajout.setInt(5, 0);
            ajout.executeUpdate();

            ajout.setInt(1, 7);
            ajout.setString(2, "Kayserispor");
            ajout.setInt(3, (2019 - 1966));
            ajout.setString(4, "4-4-2");
            ajout.setInt(5, 0);
            ajout.executeUpdate();

            ajout.setInt(1, 8);
            ajout.setString(2, "Bursaspor");
            ajout.setInt(3, (2019 - 1963));
            ajout.setString(4, "5-2-3");
            ajout.setInt(5, 0);
            ajout.executeUpdate();

            ajout.setInt(1, 9);
            ajout.setString(2, "Konyaspor");
            ajout.setInt(3, (2019 - 1922));
            ajout.setString(4, "5-3-2");
            ajout.setInt(5, 0);
            ajout.executeUpdate();

            ajout.setInt(1, 10);
            ajout.setString(2, "Rizespor");
            ajout.setInt(3, (2019 - 1953));
            ajout.setString(4, "4-2-3-1");
            ajout.setInt(5, 0);
            ajout.executeUpdate();

            ajout = connexion.prepareStatement(
                    "insert into players(idPlayer,name,myClubId,nationality,position,average,age,nbAssists,nbGoals,nbCleanSheets) values (?,?,?,?,?,?,?,?,?,?)"
            );
            int idClub = 1;
            //joueurs de GALATASARAY
            int idPlayer = 1;
            ajout.setInt(1, idPlayer);
            ajout.setString(2, "Fernando Muslera");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Uruguay");
            ajout.setString(5, "Goalkeeper");
            ajout.setInt(6, 87);
            ajout.setInt(7, 32);
            ajout.setNull(8, 0);
            ajout.setNull(9, 0);
            ajout.setInt(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Mariano");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Brasil");
            ajout.setString(5, "Defender");
            ajout.setInt(6, 82);
            ajout.setInt(7, 32);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Marcao");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Brasil");
            ajout.setString(5, "Defender");
            ajout.setInt(6, 83);
            ajout.setInt(7, 22);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Christian Luyindama");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Congo");
            ajout.setString(5, "Defender");
            ajout.setInt(6, 85);
            ajout.setInt(7, 25);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Yuto Nagatomo");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Japon");
            ajout.setString(5, "Defender");
            ajout.setInt(6, 87);
            ajout.setInt(7, 32);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Fernando Reges");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Brasil");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, 81);
            ajout.setInt(7, 31);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Badou Ndiaye");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Senegal");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, 85);
            ajout.setInt(7, 28);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Younes Belhanda");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Morrocco");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, 82);
            ajout.setInt(7, 29);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Henry Onyekuru");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Nigeria");
            ajout.setString(5, "Striker");
            ajout.setInt(6, 83);
            ajout.setInt(7, 21);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Sofiane Feghouli");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Algeria");
            ajout.setString(5, "Striker");
            ajout.setInt(6, 86);
            ajout.setInt(7, 29);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Mbaye Diagne");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Senegal");
            ajout.setString(5, "Striker");
            ajout.setInt(6, 87);
            ajout.setInt(7, 27);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            //joueurs de FENERBAHCE
            idClub = ++idClub;
            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Harun Tekin");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Goalkeeper");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 29);
            ajout.setNull(8, 0);
            ajout.setNull(9, 0);
            ajout.setInt(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Serdar Aziz");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 28);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Martin Skrtel");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Slovakia");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 34);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Sadik Ciftpinar");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 26);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Hasan Ali Kaldirim");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 29);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Mehmet Topal");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 33);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Eljif Elmas");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Northern Macedoine");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 19);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Tolgay Arslan");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 28);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Victor Moses");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Nigeria");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 28);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Mathieu Valbuena");
            ajout.setInt(3, idClub);
            ajout.setString(4, "France");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 34);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Roberto Soldado");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Spain");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 33);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            //joueurs de BESIKTAS
            idClub = ++idClub;
            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Loris Karius");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Germany");
            ajout.setString(5, "Goalkeeper");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 25);
            ajout.setNull(8, 0);
            ajout.setNull(9, 0);
            ajout.setInt(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Domagoj Vida");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Croatia");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 29);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Caner Erkin");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 30);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Adriano");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Brasil");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 34);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Gökhan Gönül");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 34);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Gary Medel");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Chile");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 31);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Adem Ljajic");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Serbia");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 27);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Shinji Kagawa");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Japon");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 30);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Burak Yilmaz");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 33);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Ricardo Quaresma");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Portugal");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 35);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Jeremain Lens");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Netherlands");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 31);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            //joueurs de BASAKSEHIR
            idClub = ++idClub;
            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Mert Günok");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Goalkeeper");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 30);
            ajout.setNull(8, 0);
            ajout.setNull(9, 0);
            ajout.setInt(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Gaël Clichy");
            ajout.setInt(3, idClub);
            ajout.setString(4, "France");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 33);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Joseph Attamah");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Ghana");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 24);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Junior Caiçara");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Brasil");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 29);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Irfan Can Kahveci");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 23);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Emre Belezoglu");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 38);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Milos Jojic");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Serbia");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 27);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Edin Visca");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Bosnia-Herzegovina");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 30);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Arda Turan");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 32);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Demba Ba");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Senegal");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 33);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Emmanuel Adebayor");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Togo");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 35);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            //joueurs de TRABZONSPOR
            idClub = ++idClub;
            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Ugurcan Cakir");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Goalkeeper");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 23);
            ajout.setNull(8, 0);
            ajout.setNull(9, 0);
            ajout.setInt(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Majid Hosseini");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Iran");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 22);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Joao Peireira");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Portugal");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 35);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Filip Novak");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Cezch Republic");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 28);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Luis Ibanez");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Argentina");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 30);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Zargo Touré");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Senegal");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 29);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "José Sosa");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Argentina");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 33);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Yusuf Yazici");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 22);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Anthony Nwakaeme");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Nigeria");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 30);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Hugo Rodallega");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Colombie");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 33);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Caleb Ekuban");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Ghana");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 25);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            //joueurs de SIVASSPOR
            idClub = ++idClub;
            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Ali Sasal Vural");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Goalkeeper");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 28);
            ajout.setNull(8, 0);
            ajout.setNull(9, 0);
            ajout.setInt(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "David Braz");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Brasil");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 31);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Douglas");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Brasil");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 28);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Ugur Ciftci");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 26);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Fatih Aksoy");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 21);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Emre Kilinc");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 24);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Hakan Arslan");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 30);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Ozer Hurmaci");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 32);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Arouna Koné");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Ivory Coast");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 35);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Fosseni Diabaté");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Mali");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 23);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Muhammet Demir");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 27);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            //joueurs de KAYSERISPOR
            idClub = ++idClub;
            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Vedat Karakus");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Goalkeeper");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 21);
            ajout.setNull(8, 0);
            ajout.setNull(9, 0);
            ajout.setInt(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Levent Gülen");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 25);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Christian Sapunaru");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Roumania");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 35);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Atilla Turan");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 27);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Oleksandr Kucher");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Ukrania");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 36);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Bernard Mensah");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Ghana");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 24);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Samil Cinaz");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 33);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Deniz Türüç");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 26);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Hakan Citak");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 20);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Umut Bulut");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 36);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Asamoah Gyan");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Ghana");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 33);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            //joueurs de BURSASPOR
            idClub = ++idClub;
            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Okan Kocuk");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Goalkeeper");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 23);
            ajout.setNull(8, 0);
            ajout.setNull(9, 0);
            ajout.setInt(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Ertugrul Ersoy");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 22);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Santiago Vergini");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Argentina");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 30);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Aurélien Chedjou");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Cameroun");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 33);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Furkan Özcan");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 20);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Iasmin Latovlevici");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Romania");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 32);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Aytaç Kara");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 23);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Yusuf Erdogan");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 26);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Diafra Sakho");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Senegal");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 29);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Henri Saivet");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Senegal");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 28);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Kubilay Kanatsizkus");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 22);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            //joueurs de KONYASPOR
            idClub = ++idClub;
            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Serkan Kirintili");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Goalkeeper");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 34);
            ajout.setNull(8, 0);
            ajout.setNull(9, 0);
            ajout.setInt(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Fallou Diagne");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Senegal");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 29);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Ugur Demirok");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 30);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Selim Ay");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 27);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Ali Turan");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 35);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Leonard Zuta");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Sweden");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 24);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Deni Milosevic");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Belgium");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 24);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Volkan Findikli");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 28);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Paolo Hurtado");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Peru");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 28);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Moryké Fofana");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Ivory Coast");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 27);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Adis Jahovic");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Bosnia-Herzegovina");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 32);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            //joueurs de RIZESPOR
            idClub = ++idClub;
            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Gökhan Akkan");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Goalkeeper");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 24);
            ajout.setNull(8, 0);
            ajout.setNull(9, 0);
            ajout.setInt(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Chidozie Awaziem");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Nigeria");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 22);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Montassar Talbi");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Tunisia");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 20);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Mohamed Abarhoun");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Morrocco");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 29);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Mehmet Uslu");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Defender");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 31);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Musa Cagiran");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 26);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Aatif Chahechouche");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Morrocco");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 32);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Fernando Boldrin");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Brasil");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 30);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Okechukwu Azubuike");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Nigeria");
            ajout.setString(5, "Midfielder");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 21);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Mustafa Saymak");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Turkey");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 26);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            ajout.setInt(1, ++idPlayer);
            ajout.setString(2, "Aminu Umar");
            ajout.setInt(3, idClub);
            ajout.setString(4, "Nigeria");
            ajout.setString(5, "Striker");
            ajout.setInt(6, r.nextInt(11 + 1) + 73);
            ajout.setInt(7, 24);
            ajout.setInt(8, 0);
            ajout.setInt(9, 0);
            ajout.setNull(10, 0);
            ajout.executeUpdate();

            //STADES
            ajout = connexion.prepareStatement(
                    "insert into stadiums(idStadium,myClubId,stadiumName,capacity) values (?,?,?,?)"
            );
            int stadiumId = 1;
            //GALATASARAY
            ajout.setInt(1, stadiumId);
            ajout.setInt(2, stadiumId);
            ajout.setString(3, "Türk Telekom Arena");
            ajout.setInt(4, 52650);
            ajout.executeUpdate();

            //FENERBAHCE
            stadiumId = ++stadiumId;
            ajout.setInt(1, stadiumId);
            ajout.setInt(2, stadiumId);
            ajout.setString(3, "Sükrü Saraçoglu");
            ajout.setInt(4, 46200);
            ajout.executeUpdate();

            //BESIKTAS
            stadiumId = ++stadiumId;
            ajout.setInt(1, stadiumId);
            ajout.setInt(2, stadiumId);
            ajout.setString(3, "Vodafone Arena");
            ajout.setInt(4, 41903);
            ajout.executeUpdate();

            //BASAKSEHIR
            stadiumId = ++stadiumId;
            ajout.setInt(1, stadiumId);
            ajout.setInt(2, stadiumId);
            ajout.setString(3, "3.Istanbul Basaksehir Fatih Terim");
            ajout.setInt(4, 17300);
            ajout.executeUpdate();

            //TRABZONSPOR
            stadiumId = ++stadiumId;
            ajout.setInt(1, stadiumId);
            ajout.setInt(2, stadiumId);
            ajout.setString(3, "Akyazi Arena");
            ajout.setInt(4, 40775);
            ajout.executeUpdate();

            //SIVASSPOR
            stadiumId = ++stadiumId;
            ajout.setInt(1, stadiumId);
            ajout.setInt(2, stadiumId);
            ajout.setString(3, "Yeni 4 Eylül Stadi");
            ajout.setInt(4, 27732);
            ajout.executeUpdate();

            //KAYSERISPOR
            stadiumId = ++stadiumId;
            ajout.setInt(1, stadiumId);
            ajout.setInt(2, stadiumId);
            ajout.setString(3, "Kadir Has Stadyumu");
            ajout.setInt(4, 32864);
            ajout.executeUpdate();

            //BURSASPOR
            stadiumId = ++stadiumId;
            ajout.setInt(1, stadiumId);
            ajout.setInt(2, stadiumId);
            ajout.setString(3, "Timsah Arena");
            ajout.setInt(4, 43331);
            ajout.executeUpdate();

            //KONYASPOR
            stadiumId = ++stadiumId;
            ajout.setInt(1, stadiumId);
            ajout.setInt(2, stadiumId);
            ajout.setString(3, "Konya Büyüksehir Belediye Stadyumu");
            ajout.setInt(4, 41981);
            ajout.executeUpdate();

            //RIZESPOR
            stadiumId = ++stadiumId;
            ajout.setInt(1, stadiumId);
            ajout.setInt(2, stadiumId);
            ajout.setString(3, "Caykur Didi");
            ajout.setInt(4, 15332);
            ajout.executeUpdate();
            
            
             PreparedStatement rechercheJoueurDeGalatasaray;
            rechercheJoueurDeGalatasaray = connexion.prepareStatement("select * from clubs\n"
                    +"inner join players on players.myClubId=clubs.idClub\n" 
                    +"where\n"
                    + "   players.name=\"Fernando Muslera\"\n"
                    );
            try (ResultSet resultats = rechercheJoueurDeGalatasaray.executeQuery()) {
                while (resultats.next()) {
                    String name = resultats.getString("name");
                    System.out.println(  name );

                }
            } catch (SQLException ex) {
                throw new Exception(ex.getMessage());
            }
            
            connexion = DriverManager.getConnection(url);
            PreparedStatement searchStadiums = connexion.prepareStatement(""
                    + "select * from stadiums");

            try (ResultSet results = searchStadiums.executeQuery()) {
                while (results.next()) {
                    int stadiumIdd = results.getInt("idStadium");
                    int myClubId = results.getInt("myClubId");
                    String stadiumName = results.getString("stadiumName");
                    int stadiumCapacity = results.getInt("capacity");
                    System.out.println(stadiumIdd+" "+stadiumName+" "+stadiumCapacity);
                    
                }
            } catch (SQLException e) {
                throw new Exception(e.getMessage());
            }

        } catch (ClassNotFoundException | SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }

}
