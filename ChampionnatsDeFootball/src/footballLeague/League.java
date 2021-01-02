/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballLeague;

import club.data.Club;
import java.util.HashMap;
import player.data.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import player.data.Defender;
import player.data.GoalKeeper;
import player.data.Midfielder;
import player.data.Striker;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author isoyturk
 */
//Point 1 : cette classe contient tout les joueurs, clubs et stades, il contient
//aussi toutes les rencontres puisque c'est elle qui les gère
//-elle récupère les données pour créer des objets et les stocker dans des 
//collections
//- elle met aussi à jour les bases de données
public class League {

    GoalKeeper goldenGoalKeeper = null;
    Player kingPlayer = null;
    Player bestAssister = null;
    String nomFichier = "data/baseDeDonnéesFoot.sqlite";
    String url = "jdbc:sqlite:" + nomFichier;
    Connection connexion;
    PreparedStatement ajout;
    int idGame = 1;
    private final String leagueName;
    //Point 7: utilisation d'une collection d'objet
    private final HashMap<Integer, Club> myclubs;
    //Point 7: utilisation d'une collection d'objet
    private final HashMap<Integer, Player> players;
    //Point 7: utilisation d'une collection d'objet
    private final HashMap<Integer, Stadium> stadiums;
    //Point 7: utilisation d'une collection d'objet
    private final HashMap<Integer, FinishedGame> games;

    /**
     * create a new league
     *
     * @param leagueName the new league name
     */
    public League(String leagueName) {
        this.leagueName = leagueName;
        this.stadiums = new HashMap<>();
        this.players = new HashMap<>();
        this.myclubs = new HashMap<>();
        this.games = new HashMap<>();
    }
//Point 9: chargement de stades depuis une base de données
    /**
     * import stadiums from database
     *
     * @throws Exception
     */
    public void importStadiumFromDataBase() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            connexion = DriverManager.getConnection(url);
            PreparedStatement searchStadiums = connexion.prepareStatement("select * from stadiums;");
            try (ResultSet results = searchStadiums.executeQuery()) {
                while (results.next()) {
                    int stadiumId = results.getInt("idStadium");
                    int myClubId = results.getInt("myClubId");
                    String stadiumName = results.getString("stadiumName");
                    int stadiumCapacity = results.getInt("capacity");
                    Stadium stadium = new Stadium(stadiumName,
                            myClubId, stadiumCapacity);
                    stadiums.put(stadiumId, stadium);
                }
            } catch (SQLException e) {
                throw new Exception(e.getMessage());
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }
//Point 9: chargement de joueurs depuis une base de données
    /**
     * import players from database
     *
     * @throws Exception
     */
    public void importPlayersFromDataBase() throws Exception {
        String pathName = "data/";
        String fileName = "baseDeDonnéesFoot.sqlite";
        String url = "jdbc:sqlite:" + pathName + fileName;
        try {
            Class.forName("org.sqlite.JDBC");
            connexion = DriverManager.getConnection(url);
            PreparedStatement searchPlayers = connexion.prepareStatement("select * from players");
            try (ResultSet results = searchPlayers.executeQuery()) {
                while (results.next()) {
                    int playerId = results.getInt("idPlayer");
                    String name = results.getString("name");
                    int myClub = results.getInt("myClubId");
                    String nationality = results.getString("nationality");
                    String position = results.getString("position");
                    int average = results.getInt("average");
                    int age = results.getInt("age");
                    int nbAssists = results.getInt("nbAssists");
                    int nbGoals = results.getInt("nbGoals");
                    int nbCleanSheets = results.getInt("nbCleanSheets");

                    switch (position) {
                        case "Goalkeeper":
                            Player g = new GoalKeeper(playerId, name, myClub, nationality,
                                    position, average, age, nbCleanSheets, false);
                            players.put(playerId, g);
                            break;
                        case "Defender":
                            Player d = new Defender(playerId, name, myClub, nationality,
                                    position, average, age, nbAssists, nbGoals, false, false);
                            players.put(playerId, d);
                            break;
                        case "Midfielder":
                            Player m = new Midfielder(playerId, name, myClub, nationality,
                                    position, average, age, nbAssists, nbGoals, false, false);
                            players.put(playerId, m);
                            break;
                        case "Striker":
                            Player s = new Striker(playerId, name, myClub, nationality,
                                    position, average, age, nbAssists, nbGoals, false, false);
                            players.put(playerId, s);
                            break;
                    }

                }
            } catch (SQLException e) {
                throw new Exception(e.getMessage());
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }
//Point 9: chargement de clubs depuis une base de données
    /**
     * imports clubs from database
     *
     * @throws Exception
     */
    public void importClubsFromDataBase() throws Exception {

        Connection connexion;
        try {
            Class.forName("org.sqlite.JDBC");
            connexion = DriverManager.getConnection(url);
            PreparedStatement searchStadiums = connexion.prepareStatement("select * from clubs");

            try (ResultSet results = searchStadiums.executeQuery()) {
                while (results.next()) {
                    Stadium stad = null;
                    HashMap<Integer, Player> myPlayers = new HashMap<>();
                    int clubId = results.getInt("idClub");
                    String name = results.getString("name");
                    int age = results.getInt("age");
                    String formation = results.getString("formation");
                    int nbTrophy = results.getInt("nbTrophy");
                    for (Stadium s : stadiums.values()) {
                        if (clubId == s.getMyClubId()) {
                            stad = s;
                        }
                    }
                    int i = 1;
                    for (Player p : players.values()) {
                        if (p.getMyClubId() == clubId) {
                            myPlayers.put(i, p);
                            i++;
                        }
                    }
                    Club c = new Club(clubId, name, age, formation, stad, nbTrophy, myPlayers, 0);
                    myclubs.put(clubId, c);
                }
            } catch (SQLException e) {
                throw new Exception(e.getMessage());
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * get club by name to play match between clubs
     *
     * @param shearchedClub clubName
     * @return object club
     */
    public Club getClubForMatch(String shearchedClub) {
        Club findClub = null;
        for (Club c : myclubs.values()) {
            if (shearchedClub.equals(c.getClubName())) {
                findClub = c;
            }
        }
        return findClub;
    }

    /**
     * add new finished game to games list
     *
     * @param homeClub
     * @param awayClub
     * @param m
     * @param scoresOfTeams
     */
    public void addFinishedMatch(Club homeClub, Club awayClub, FootballMatch m, int scoresOfTeams[]) {
        homeClub.addScoredGoals(scoresOfTeams[0]);
        homeClub.addConcededGoals(scoresOfTeams[1]);
        awayClub.addScoredGoals(scoresOfTeams[1]);
        awayClub.addConcededGoals(scoresOfTeams[0]);
        FinishedGame f = new FinishedGame(idGame++, scoresOfTeams[0], scoresOfTeams[1], homeClub, awayClub, m.getAffluence(homeClub), m.scorersOfTeam(homeClub, scoresOfTeams[0]), m.scorersOfTeam(awayClub, scoresOfTeams[1]), m.assistersOfTeam(homeClub, scoresOfTeams[0]), m.assistersOfTeam(awayClub, scoresOfTeams[1]), m.winnerOfTheGame(homeClub, awayClub, scoresOfTeams[0], scoresOfTeams[1]));
        games.put(idGame, f);
    }

    /**
     * add cleanSheet to GK
     *
     * @param homeClub
     * @param awayClub
     * @param scoresOfTeams
     * @param teamPlaceInArray
     */
    public void addCleansheetToGoalKeeper(Club homeClub, Club awayClub, int scoresOfTeams[], int teamPlaceInArray) {
        if (scoresOfTeams[teamPlaceInArray] == 0) {
            for (Player p : awayClub.getMyPlayers().values()) {
                if (p.getPosition().equals("Goalkeeper")) {
                    GoalKeeper gk = (GoalKeeper) p;
                    gk.addCleanSheet();
                }
            }
        }
    }

    /**
     * play a new match between away and home
     *
     * @param homeClub object home club
     * @param awayClub object away club
     */
    public void match(Club homeClub, Club awayClub) {
        FootballMatch m = new FootballMatch();
        int[] scoresOfTeams = m.setScoreMaxiTeam(m.getTeamAverage(homeClub), m.getTeamAverage(awayClub));
        if (m.winnerOfTheGame(homeClub, awayClub, scoresOfTeams[0], scoresOfTeams[1]).equals(homeClub.getClubName())) {
            homeClub.addWin();
            awayClub.addLose();
            addFinishedMatch(homeClub, awayClub, m, scoresOfTeams);
            addCleansheetToGoalKeeper(homeClub, awayClub, scoresOfTeams, 1);
        } else if (m.winnerOfTheGame(homeClub, awayClub, scoresOfTeams[0], scoresOfTeams[1]).equals(awayClub.getClubName())) {
            awayClub.addWin();
            homeClub.addLose();
            addFinishedMatch(homeClub, awayClub, m, scoresOfTeams);
            addCleansheetToGoalKeeper(homeClub, awayClub, scoresOfTeams, 0);
        } else {
            homeClub.addDraw();
            awayClub.addDraw();
            addFinishedMatch(homeClub, awayClub, m, scoresOfTeams);
            addCleansheetToGoalKeeper(homeClub, awayClub, scoresOfTeams, 1);
            addCleansheetToGoalKeeper(homeClub, awayClub, scoresOfTeams, 0);
        }
    }

    /**
     * play all matches of the season
     */
    public void playAllMatch() {
        for (Club home : myclubs.values()) {
            for (Club away : myclubs.values()) {
                if (!home.getClubName().equals(away.getClubName())) {
                    match(home, away);
                }
            }
        }
    }

    /**
     * display the ranking table
     *
     * @throws java.sql.SQLException
     */
    public void displayRanking() throws SQLException {
        List<Club> ranking = new ArrayList<>(myclubs.values());
        int place = 1;
        ranking.sort(Comparator.comparing(Club::getPoints).thenComparingInt(Club::getAverage).reversed());
        System.out.println("Classement de " + leagueName + " :");
        System.out.println();
        System.out.format("|%15s %5s %5s %5s %5s\n", "       Equipe       |", "Différence |", "Buts marqués |", "Buts encaissés  |", "   Points    |");
        connexion = DriverManager.getConnection(url);
        ajout = connexion.prepareStatement("insert into ranking(place,clubs,average,scoredGoals,concededGoals,points) values (?,?,?,?,?,?)");
        for (Club club : ranking) {
            System.out.format("| %4s  %12s | %10s | %12s | %15s | %12s |\n", place + ". ", club.getClubName() + " ", club.getAverage() + "    ",
                    club.getScoredGoals() + "     ", club.getConcededGoals() + "      ", club.getPoints() + "     ");
            ajout.setInt(1, place);
            ajout.setString(2, club.getClubName());
            ajout.setInt(3, club.getAverage());
            ajout.setInt(4, club.getScoredGoals());
            ajout.setInt(5, club.getConcededGoals());
            ajout.setInt(6, club.getPoints());
            ajout.executeUpdate();
            place++;
        }
    }

    /**
     * determinate best players in the league
     */
    public void getBestStrikerGKAndAssister() {
        GoalKeeper gk;
        Defender d;
        Midfielder m;
        Striker s;
        int cleanSheets = 0, goals = 0, assists = 0;
        for (Player p : players.values()) {
            switch (p.getPosition()) {
                case "Goalkeeper":
                    gk = (GoalKeeper) p;
                    if (gk.getSeasonInfosOnPlayer()[0] > cleanSheets) {
                        goldenGoalKeeper = gk;
                        cleanSheets = goldenGoalKeeper.getSeasonInfosOnPlayer()[0];
                    }
                    break;
                case "Defender":
                    d = (Defender) p;
                    if (d.getSeasonInfosOnPlayer()[1] > goals) {
                        kingPlayer = d;
                        goals = kingPlayer.getSeasonInfosOnPlayer()[1];
                    }
                    if (p.getSeasonInfosOnPlayer()[0] > assists) {
                        bestAssister = d;
                        assists = bestAssister.getSeasonInfosOnPlayer()[0];
                    }
                    break;
                case "Midfielder":
                    m = (Midfielder) p;
                    if (m.getSeasonInfosOnPlayer()[1] > goals) {
                        kingPlayer = m;
                        goals = kingPlayer.getSeasonInfosOnPlayer()[1];
                    }
                    if (m.getSeasonInfosOnPlayer()[0] > assists) {
                        bestAssister = m;
                        assists = bestAssister.getSeasonInfosOnPlayer()[0];
                    }
                    break;
                case "Striker":
                    s = (Striker) p;
                    if (s.getSeasonInfosOnPlayer()[1] > goals) {
                        kingPlayer = s;
                        goals = kingPlayer.getSeasonInfosOnPlayer()[1];
                    }
                    if (s.getSeasonInfosOnPlayer()[0] > assists) {
                        bestAssister = s;
                        assists = bestAssister.getSeasonInfosOnPlayer()[0];
                    }
                    break;
            }
        }
    }

    /**
     * do the best gk, scorer an assister with boolean
     */
    public void doBestStrikerGKAndAssister() {
        getBestStrikerGKAndAssister();
        goldenGoalKeeper.doBestGK();
        switch (kingPlayer.getPosition()) {
            case "Defender":
                Defender defenderBestScorer = (Defender) kingPlayer;
                defenderBestScorer.doBestScorer();
                break;
            case "Midfielder":
                Midfielder midfielderBestScorer = (Midfielder) kingPlayer;
                midfielderBestScorer.doBestScorer();
                break;
            case "Striker":
                Striker strikerBestScorer = (Striker) kingPlayer;
                strikerBestScorer.doBestScorer();
                break;
        }
        switch (bestAssister.getPosition()) {
            case "Defender":
                Defender defenderBestAssister = (Defender) bestAssister;
                defenderBestAssister.doBestScorer();
                break;
            case "Midfielder":
                Midfielder midfielderBestAssister = (Midfielder) bestAssister;
                midfielderBestAssister.doBestScorer();
                break;
            case "Striker":
                Striker strikerBestAssister = (Striker) bestAssister;
                strikerBestAssister.doBestScorer();
                break;
        }
    }

    /**
     * display best scorer and best goalkeeper of the league in the season
     */
    public void displayBestStrikerGKAndAssister() {

        Club bestGkClub = null;
        Club bestStrikerClub = null;
        Club bestAssisterClub = null;
        for (Club c : myclubs.values()) {
            if (c.getIdClub() == kingPlayer.getMyClubId()) {
                bestStrikerClub = c;
            }
            if (c.getIdClub() == goldenGoalKeeper.getMyClubId()) {
                bestGkClub = c;
            }
            if (c.getIdClub() == bestAssister.getMyClubId()) {
                bestAssisterClub = c;
            }
        }
        System.out.println(kingPlayer.getName() + ", joueur de " + bestStrikerClub.getClubName() + ", meilleur buteur de " + leagueName + " avec " + kingPlayer.getSeasonInfosOnPlayer()[1] + " buts.");
        System.out.println(bestAssister.getName() + ", joueur de " + bestAssisterClub.getClubName() + ", meilleur passeur de " + leagueName + " avec " + bestAssister.getSeasonInfosOnPlayer()[0] + " passes décisives.");
        System.out.println(goldenGoalKeeper.getName() + ", gardien de " + bestGkClub.getClubName() + ", meilleur gardien de " + leagueName + " avec " + goldenGoalKeeper.getSeasonInfosOnPlayer()[0] + " cleansheets.");
    }

    /**
     * display the champion club of the league
     */
    public void displayChampionClub() {
        Club champion = null;
        int points = 0;
        for (Club c : myclubs.values()) {
            if (c.getPoints() > points) {
                champion = c;
                points = c.getPoints();
            }
        }
        System.out.println("L'équipe championne est : " + champion.getClubName());
    }

    /**
     * add all football games to dataBase
     *
     * @throws SQLException
     */
    public void insertFinishedGamesToDatabase() throws SQLException {
        connexion = DriverManager.getConnection(url);
        ajout = connexion.prepareStatement(
                "insert into footballGames(idMatch,homeClub,awayClub,stadium,homeTeamGoal,awayTeamGoals,winnerClub,affluence) values (?,?,?,?,?,?,?,?)"
        );
        for (FinishedGame fg : games.values()) {
            ajout.setInt(1, fg.idMatch());
            ajout.setString(2, fg.getHomeClub().getClubName());
            ajout.setString(3, fg.getAwayClub().getClubName());
            ajout.setString(4, fg.getHomeClub().getMyStadium().getStadiumName());
            ajout.setInt(5, fg.getNbGoalsHome());
            ajout.setInt(6, fg.getNbGoalsAway());
            if (fg.getWinner() == null) {
                ajout.setString(7, "Match nul");
            } else {
                ajout.setString(7, fg.getWinner());
            }
            ajout.setInt(8, fg.getAffluence());
            ajout.executeUpdate();
        }
    }

    /**
     * insert new values of goals,clean sheets...
     *
     * @throws SQLException
     */
    public void updateDataBase() throws SQLException {
        connexion = DriverManager.getConnection(url);
        ajout = connexion.prepareStatement("UPDATE players SET nbGoals=? WHERE idPlayer=? ");
        for (Player p : players.values()) {
            if (!p.getPosition().equals("Goalkeeper") && p.getSeasonInfosOnPlayer()[1] > 0) {
                ajout.setInt(1, p.getSeasonInfosOnPlayer()[1]);
                ajout.setInt(2, p.getIdPlayer());
                ajout.executeUpdate();
            }
        }

        ajout = connexion.prepareStatement("UPDATE players SET nbAssists=? WHERE idPlayer=? ");
        for (Player p : players.values()) {
            if (!p.getPosition().equals("Goalkeeper") && p.getSeasonInfosOnPlayer()[0] > 0) {
                ajout.setInt(1, p.getSeasonInfosOnPlayer()[0]);
                ajout.setInt(2, p.getIdPlayer());
                ajout.executeUpdate();
            }
        }

        ajout = connexion.prepareStatement("UPDATE players SET nbCleanSheets=? WHERE idPlayer=? ");
        for (Player p : players.values()) {
            if (p.getPosition().equals("Goalkeeper") && p.getSeasonInfosOnPlayer()[0] > 0) {
                ajout.setInt(1, p.getSeasonInfosOnPlayer()[0]);
                ajout.setInt(2, p.getIdPlayer());
                ajout.executeUpdate();
            }
        }

        ajout = connexion.prepareStatement("UPDATE players SET bestAssister=? WHERE idPlayer=? ");
        for (Player p : players.values()) {
            if (p.getName().equals(bestAssister.getName())) {
                ajout.setString(1, "OUI");
                ajout.setInt(2, p.getIdPlayer());
            } else if (p.getPosition().equals(goldenGoalKeeper.getPosition())) {
                ajout.setNull(1, 0);
                ajout.setInt(2, p.getIdPlayer());
            } else {
                ajout.setString(1, "---");
                ajout.setInt(2, p.getIdPlayer());
            }
            ajout.executeUpdate();
        }

        ajout = connexion.prepareStatement("UPDATE players SET bestScorer=? WHERE idPlayer=? ");
        for (Player p : players.values()) {
            if (p.getName().equals(kingPlayer.getName())) {
                ajout.setString(1, "OUI");
                ajout.setInt(2, p.getIdPlayer());
            } else if (p.getPosition().equals(goldenGoalKeeper.getPosition())) {
                ajout.setNull(1, 0);
                ajout.setInt(2, p.getIdPlayer());
            } else {
                ajout.setString(1, "---");
                ajout.setInt(2, p.getIdPlayer());
            }
            ajout.executeUpdate();
        }

        ajout = connexion.prepareStatement("UPDATE players SET bestGk=? WHERE idPlayer=? ");
        for (Player p : players.values()) {
            if (p.getName().equals(goldenGoalKeeper.getName())) {
                ajout.setString(1, "OUI");
                ajout.setInt(2, p.getIdPlayer());
            } else if (p.getPosition().equals("Midfielder") && p.getPosition().equals("Defender") && p.getPosition().equals("Striker")) {
                ajout.setNull(1, 0);
                ajout.setInt(2, p.getIdPlayer());
            } else {
                ajout.setString(1, "---");
                ajout.setInt(2, p.getIdPlayer());
            }
            ajout.executeUpdate();
        }
    }
}
