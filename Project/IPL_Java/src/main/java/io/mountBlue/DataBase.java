package io.mountBlue;

import java.io.*;
import java.util.*;

public class DataBase {
    private static final String MATCH_PATH = "meta-data/matches.csv";
    private static final String DELIVERIES_PATH = "meta-data/deliveries.csv";
    private static final int MATCH_ID = 0;
    private static final int SESSION = 1;
    private static final int CITY = 2;
    private static final int DATE = 3;
    private static final int TEAM1 = 4;
    private static final int TEAM2 = 5;
    private static final int TOSS_WINNER = 6;
    private static final int TOSS_DECISION = 7;
    private static final int RESULT = 8;
    private static final int DL_APPLIED = 9;
    private static final int WINNER = 10;
    private static final int WIN_BY_RUNS = 11;
    private static final int WIN_BY_WICKETS = 12;
    private static final int PLAYER_OF_MATCH = 13;
    private static final int VENUE = 14;
    private static final int UMPIRE1 = 15;
    private static final int UMPIRE2 = 16;
    private static final int UMPIRE3 = 17;

    private static final int INNING = 1;
    private static final int BATTING_TEAM = 2;
    private static final int BOWLING_TEAM = 3;
    private static final int OVER = 4;
    private static final int BALL = 5;
    private static final int BATSMAN = 6;
    private static final int NON_STRIKER = 7;
    private static final int BOWLER = 8;
    private static final int SUPER_OVER = 9;
    private static final int WIDE_RUNS = 10;
    private static final int BYE_RUNS = 11;
    private static final int LEG_BY_RUNS = 12;
    private static final int NO_BALL_RUNS = 13;
    private static final int PENALTY_RUNS = 14;
    private static final int BATSMAN_RUNS = 15;
    private static final int EXTRA_RUNS = 16;
    private static final int TOTAL_RUNS = 17;
    private static final int PLAYER_DISMISSED = 18;
    private static final int DISMISSAL_KIND = 19;
    private static final int FIELDER = 20;

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
                Match match = new Match();

                match.setId(Integer.parseInt(fields[MATCH_ID]));
                match.setSeason(Integer.parseInt(fields[SESSION]));
                match.setCity(fields[CITY].isEmpty() ? null : fields[CITY]);
                match.setDate(fields[DATE]);
                match.setTeam1(fields[TEAM1]);
                match.setTeam2(fields[TEAM2]);
                match.setTossWinner(fields[TOSS_WINNER]);
                match.setTossDecision(fields[TOSS_DECISION]);
                match.setResult(fields[RESULT]);
                match.setDlApplied(Integer.parseInt(fields[DL_APPLIED]));
                match.setWinner(fields[WINNER]);
                match.setWinByRuns(Integer.parseInt(fields[WIN_BY_RUNS]));
                match.setWinByWickets(Integer.parseInt(fields[WIN_BY_WICKETS]));
                match.setPlayerOfMatch(fields[PLAYER_OF_MATCH]);
                match.setVenue(fields[VENUE]);

                if (fields.length > 15) {
                    match.setUmpire1(fields[UMPIRE1].isEmpty() ? null : fields[UMPIRE1]);
                }
                if (fields.length > 16) {
                    match.setUmpire2(fields[UMPIRE2].isEmpty() ? null : fields[UMPIRE2]);
                }
                if (fields.length > 17) {
                    match.setUmpire3(fields[UMPIRE3].isEmpty() ? null : fields[UMPIRE3]);
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
                Deliveries delivery = new Deliveries();

                delivery.setMatchId(Integer.parseInt(fields[MATCH_ID]));
                delivery.setInning(Integer.parseInt(fields[INNING]));
                delivery.setBattingTeam(fields[BATTING_TEAM]);
                delivery.setBowlingTeam(fields[BOWLING_TEAM]);
                delivery.setOver(Integer.parseInt(fields[OVER]));
                delivery.setBall(Integer.parseInt(fields[BALL]));
                delivery.setBatsman(fields[BATSMAN]);
                delivery.setNonStriker(fields[NON_STRIKER]);
                delivery.setBowler(fields[BOWLER]);
                delivery.setSuperOver(Integer.parseInt(fields[SUPER_OVER]));
                delivery.setWideRuns(Integer.parseInt(fields[WIDE_RUNS]));
                delivery.setByeRuns(Integer.parseInt(fields[BYE_RUNS]));
                delivery.setLegbyeRuns(Integer.parseInt(fields[LEG_BY_RUNS]));
                delivery.setNoballRuns(Integer.parseInt(fields[NO_BALL_RUNS]));
                delivery.setPenaltyRuns(Integer.parseInt(fields[PENALTY_RUNS]));
                delivery.setBatsmanRuns(Integer.parseInt(fields[BATSMAN_RUNS]));
                delivery.setExtraRuns(Integer.parseInt(fields[EXTRA_RUNS]));
                delivery.setTotalRuns(Integer.parseInt(fields[TOTAL_RUNS]));
                if (fields.length > 18){
                    delivery.setPlayerDismissed(fields[PLAYER_DISMISSED].isEmpty() ? null : fields[PLAYER_DISMISSED]);
                }
                if(fields.length > 19){
                    delivery.setDismissalKind(fields[DISMISSAL_KIND].isEmpty() ? null : fields[DISMISSAL_KIND]);
                }
                if(fields.length > 20){
                    delivery.setFielder(fields[FIELDER].isEmpty() ? null : fields[FIELDER]);
                }

                deliveriesData.add(delivery);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return deliveriesData;
    }
}
