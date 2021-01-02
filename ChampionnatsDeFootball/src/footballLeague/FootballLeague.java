/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballLeague;

import importCsvFilesToDataBase.ImportDataFromCsv;

/**
 *
 * @author isoyturk
 */
public class FootballLeague {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    //Point 1: main
    public static void main(String[] args) throws Exception {
        League superlig = new League("Süper Lig");
        ImportDataFromCsv bdd = new ImportDataFromCsv();
        System.out.println("Veuillez patienter (requêtes en cour)...");
        bdd.deleteOldDataBaseFile();
        bdd.createTableOfData();
        superlig.importPlayersFromDataBase();
        superlig.importStadiumFromDataBase();
        superlig.importClubsFromDataBase();
        superlig.playAllMatch();
        superlig.insertFinishedGamesToDatabase();
        superlig.doBestStrikerGKAndAssister();
        superlig.updateDataBase();
        superlig.displayRanking();
        superlig.displayChampionClub();
        superlig.displayBestStrikerGKAndAssister();
    }

}
