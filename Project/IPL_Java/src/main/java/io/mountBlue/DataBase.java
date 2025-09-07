package io.mountBlue;

import java.io.*;
import java.util.*;

public class DataBase {
    private static final String MATCH_PATH = "meta-data/matches.csv";
    private static final String DELIVERIES_PATH = "meta-data/deliveries.csv";

    public List<Match> getMatchData() {
        List<Match> matchesData = new ArrayList<>();
        File file = new File(MATCH_PATH);

        if (!file.exists()) {
            System.out.println("File not found: " + MATCH_PATH);
            return matchesData;
        }

        try (BufferedReader ref = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;

            while ((line = ref.readLine()) != null) {
                if (isHeader) { // skip header row
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(",");

                if (fields.length < 15) continue; // safeguard

                Match match = new Match();
                match.setId(Integer.parseInt(fields[0]));
                match.setSeason(Integer.parseInt(fields[1]));
                match.setCity(fields[2].isEmpty() ? null : fields[2]);
                match.setDate(fields[3]); // still String in your class
                match.setTeam1(fields[4]);
                match.setTeam2(fields[5]);
                match.setTossWinner(fields[6]);
                match.setTossDecision(fields[7]);
                match.setResult(fields[8]);
                match.setDlApplied(Integer.parseInt(fields[9]));
                match.setWinner(fields[10]);
                match.setWinByRuns(Integer.parseInt(fields[11]));
                match.setWinByWickets(Integer.parseInt(fields[12]));
                match.setPlayerOfMatch(fields[13]);
                match.setVenue(fields[14]);

                if (fields.length > 15) {
                    match.setUmpire1(fields[15].isEmpty() ? null : fields[15]);
                }
                if (fields.length > 16) {
                    match.setUmpire2(fields[16].isEmpty() ? null : fields[16]);
                }
                if (fields.length > 17) {
                    match.setUmpire3(fields[17].isEmpty() ? null : fields[17]);
                }

                matchesData.add(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchesData;
    }

    public List<Deliveries> getDeliveriesData() {
        List<Deliveries> deliveriesData = new ArrayList<>();
        File file = new File(DELIVERIES_PATH);

        if (!file.exists()) {
            System.out.println("File not found: " + DELIVERIES_PATH);
            return deliveriesData;
        }

        try (BufferedReader ref = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;

            while ((line = ref.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(",");

//                if (fields.length < 21) continue; // safeguard

                Deliveries delivery = new Deliveries();
                delivery.setMatchId(Integer.parseInt(fields[0]));
                delivery.setInning(Integer.parseInt(fields[1]));
                delivery.setBattingTeam(fields[2]);
                delivery.setBowlingTeam(fields[3]);
                delivery.setOver(Integer.parseInt(fields[4]));
                delivery.setBall(Integer.parseInt(fields[5]));
                delivery.setBatsman(fields[6]);
                delivery.setNonStriker(fields[7]);
                delivery.setBowler(fields[8]);
                delivery.setSuperOver(Integer.parseInt(fields[9]));
                delivery.setWideRuns(Integer.parseInt(fields[10]));
                delivery.setByeRuns(Integer.parseInt(fields[11]));
                delivery.setLegbyeRuns(Integer.parseInt(fields[12]));
                delivery.setNoballRuns(Integer.parseInt(fields[13]));
                delivery.setPenaltyRuns(Integer.parseInt(fields[14]));
                delivery.setBatsmanRuns(Integer.parseInt(fields[15]));
                delivery.setExtraRuns(Integer.parseInt(fields[16]));
                delivery.setTotalRuns(Integer.parseInt(fields[17]));
                if (fields.length > 18){
                    delivery.setPlayerDismissed(fields[18].isEmpty() ? null : fields[18]);
                }
                if(fields.length > 19){
                    delivery.setDismissalKind(fields[19].isEmpty() ? null : fields[19]);
                }
                if(fields.length > 20){
                    delivery.setDismissalKind(fields[19].isEmpty() ? null : fields[19]);
                }

                deliveriesData.add(delivery);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return deliveriesData;
    }
}
