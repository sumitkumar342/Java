package io.mountBlue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final int MATCH_ID = 0;
    private static final int MATCH_SESSION = 1;
    private static final int MATCH_CITY = 2;
    private static final int MATCH_DATE = 3;
    private static final int MATCH_TEAM1 = 4;
    private static final int MATCH_TEAM2 = 5;
    private static final int MATCH_TOSS_WINNER = 6;
    private static final int MATCH_TOSS_DECISION = 7;
    private static final int MATCH_RESULT = 8;
    private static final int MATCH_DL_APPLIED = 9;
    private static final int MATCH_WINNER = 10;
    private static final int MATCH_WIN_BY_RUNS = 11;
    private static final int MATCH_WIN_BY_WICKETS = 12;
    private static final int MATCH_PLAYER_OF_MATCH = 13;
    private static final int MATCH_VENUE = 14;
    private static final int MATCH_UMPIRE1 = 15;
    private static final int MATCH_UMPIRE2 = 16;
    private static final int MATCH_UMPIRE3 = 17;

    private static final int DELIVERIES_MATCH_ID = 0;
    private static final int DELIVERIES_INNING = 1;
    private static final int DELIVERIES_BATTING_TEAM = 2;
    private static final int DELIVERIES_BOWLING_TEAM = 3;
    private static final int DELIVERIES_OVER = 4;
    private static final int DELIVERIES_BALL = 5;
    private static final int DELIVERIES_BATSMAN = 6;
    private static final int DELIVERIES_NON_STRIKER = 7;
    private static final int DELIVERIES_BOWLER = 8;
    private static final int DELIVERIES_SUPER_OVER = 9;
    private static final int DELIVERIES_WIDE_RUNS = 10;
    private static final int DELIVERIES_BYE_RUNS = 11;
    private static final int DELIVERIES_LEG_BY_RUNS = 12;
    private static final int DELIVERIES_NO_BALL_RUNS = 13;
    private static final int DELIVERIES_PENALTY_RUNS = 14;
    private static final int DELIVERIES_BATSMAN_RUNS = 15;
    private static final int DELIVERIES_EXTRA_RUNS = 16;
    private static final int DELIVERIES_TOTAL_RUNS = 17;
    private static final int DELIVERIES_PLAYER_DISMISSED = 18;
    private static final int DELIVERIES_DISMISSAL_KIND = 19;
    private static final int DELIVERIES_FIELDER = 20;

    public static void main(String[] args) {
        List<Match> matchData = fetchMatchData();
        List<Deliveries> deliveriesData = fetchDeliveriesData();

        findMatchYearWise(matchData);
        findMatchWonTeamWise(matchData);
        findExtraRun(2016, matchData, deliveriesData);
        findBestEconomicalBowler(2016, deliveriesData, matchData);
        findMatchDataById(2, matchData);
        findLast_5_Match(5, matchData);
        findPartnership("S Dhawan", deliveriesData);
        findTopScorePerTeamPerSession(deliveriesData, matchData);
        findPlayerHeighestRunLast5OverAgainstMI(matchData, deliveriesData, "Mumbai Indians", 2017);

    }

    private static int getIntField(String[] fields, int index) {
        if (fields.length > index && !fields[index].isEmpty()) {
            return Integer.parseInt(fields[index]);
        }
        return 0;
    }

    private static String getStringField(String[] fields, int index) {
        if (fields.length > index && !fields[index].isEmpty()) {
            return fields[index];
        }
        return null;
    }

    private static List<Match> fetchMatchData() {
        final String matchPath = "meta-data/matches.csv";
        List<Match> matchesData = new ArrayList<>();
        File file = new File(matchPath);

        if (!file.exists()) {
            System.out.println("File not found: " + matchPath);
            return null;
        }

        try {
            BufferedReader ref = new BufferedReader(new FileReader(file));
            String line;
            boolean isHeader = true;

            while ((line = ref.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(",");
                Match match = new Match();

                match.setId(getIntField(fields, MATCH_ID));
                match.setSeason(getIntField(fields, MATCH_SESSION));
                match.setCity(getStringField(fields, MATCH_CITY));
                match.setDate(getStringField(fields, MATCH_DATE));
                match.setTeam1(getStringField(fields, MATCH_TEAM1));
                match.setTeam2(getStringField(fields, MATCH_TEAM2));
                match.setTossWinner(getStringField(fields, MATCH_TOSS_WINNER));
                match.setTossDecision(getStringField(fields, MATCH_TOSS_DECISION));
                match.setResult(getStringField(fields, MATCH_RESULT));
                match.setDlApplied(getIntField(fields, MATCH_DL_APPLIED));
                match.setWinner(getStringField(fields, MATCH_WINNER));
                match.setWinByRuns(getIntField(fields, MATCH_WIN_BY_RUNS));
                match.setWinByWickets(getIntField(fields, MATCH_WIN_BY_WICKETS));
                match.setPlayerOfMatch(getStringField(fields, MATCH_PLAYER_OF_MATCH));
                match.setVenue(getStringField(fields, MATCH_VENUE));
                match.setUmpire1(getStringField(fields, MATCH_UMPIRE1));
                match.setUmpire2(getStringField(fields, MATCH_UMPIRE2));
                match.setUmpire3(getStringField(fields, MATCH_UMPIRE3));

                matchesData.add(match);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return matchesData;
    }

    private static List<Deliveries> fetchDeliveriesData() {
        final String deliveriesPath = "meta-data/deliveries.csv";
        List<Deliveries> deliveriesData = new ArrayList<>();
        File file = new File(deliveriesPath);

        if (!file.exists()) {
            System.out.println("File not found: " + deliveriesPath);
            return null;
        }

        try {
            BufferedReader ref = new BufferedReader(new FileReader(file));
            String line;
            boolean isHeader = true;

            while ((line = ref.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(",");
                Deliveries delivery = new Deliveries();

                delivery.setMatchId(getIntField(fields, DELIVERIES_MATCH_ID));
                delivery.setInning(getIntField(fields, DELIVERIES_INNING));
                delivery.setBattingTeam(getStringField(fields, DELIVERIES_BATTING_TEAM));
                delivery.setBowlingTeam(getStringField(fields, DELIVERIES_BOWLING_TEAM));
                delivery.setOver(getIntField(fields, DELIVERIES_OVER));
                delivery.setBall(getIntField(fields, DELIVERIES_BALL));
                delivery.setBatsman(getStringField(fields, DELIVERIES_BATSMAN));
                delivery.setNonStriker(getStringField(fields, DELIVERIES_NON_STRIKER));
                delivery.setBowler(getStringField(fields, DELIVERIES_BOWLER));
                delivery.setSuperOver(getIntField(fields, DELIVERIES_SUPER_OVER));
                delivery.setWideRuns(getIntField(fields, DELIVERIES_WIDE_RUNS));
                delivery.setByeRuns(getIntField(fields, DELIVERIES_BYE_RUNS));
                delivery.setLegbyeRuns(getIntField(fields, DELIVERIES_LEG_BY_RUNS));
                delivery.setNoballRuns(getIntField(fields, DELIVERIES_NO_BALL_RUNS));
                delivery.setPenaltyRuns(getIntField(fields, DELIVERIES_PENALTY_RUNS));
                delivery.setBatsmanRuns(getIntField(fields, DELIVERIES_BATSMAN_RUNS));
                delivery.setExtraRuns(getIntField(fields, DELIVERIES_EXTRA_RUNS));
                delivery.setTotalRuns(getIntField(fields, DELIVERIES_TOTAL_RUNS));
                delivery.setPlayerDismissed(getStringField(fields, DELIVERIES_PLAYER_DISMISSED));
                delivery.setDismissalKind(getStringField(fields, DELIVERIES_DISMISSAL_KIND));
                delivery.setFielder(getStringField(fields, DELIVERIES_FIELDER));

                deliveriesData.add(delivery);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return deliveriesData;
    }

    private static void findMatchYearWise(List<Match> matchesData) {
        Map<Integer, Integer> matchMap = new HashMap<>();

        if (matchesData == null || matchesData.isEmpty()) {
            return ;
        }

        for (int i = 0; i < matchesData.size(); i++) {
            Match match = matchesData.get(i);
            int season = match.getSeason();
            matchMap.put(season, matchMap.getOrDefault(season, 0) + 1);
        }

        System.out.println("-".repeat(40));
        if (matchMap.isEmpty()) {
            System.err.println("Data not found..!!");
        }else {
            System.out.println(matchMap);
        }
    }

    private static void findMatchWonTeamWise(List<Match> matchData) {
        Map<String, Map<Integer, Integer>> matchWonTeamWise = new TreeMap<>();

        if (matchData == null || matchData.isEmpty()) {
            return;
        }

        for (int i = 0; i < matchData.size(); i++) {
            Match match = matchData.get(i);
            int season = match.getSeason();
            String winningTeam = match.getWinner();
            if (winningTeam == null || winningTeam.trim().isEmpty()) {
                continue;
            }

            Map<Integer, Integer> yearMap = matchWonTeamWise.getOrDefault(winningTeam, new TreeMap<>());
            yearMap.put(season, yearMap.getOrDefault(season, 0) + 1);
            matchWonTeamWise.put(winningTeam, yearMap);
        }

        System.out.println("-".repeat(40));
        if (matchWonTeamWise.isEmpty()) {
            System.err.println("Data not found..!!");
        }

        for (Map.Entry<String, Map<Integer, Integer>> teamEntry : matchWonTeamWise.entrySet()) {
            String team = teamEntry.getKey();
            System.out.println("Team: " + team);

            Map<Integer, Integer> yearWiseWins = teamEntry.getValue();
            for (Map.Entry<Integer, Integer> yearEntry : yearWiseWins.entrySet()) {
                System.out.println("   Year: " + yearEntry.getKey() + " → Wins: " + yearEntry.getValue());
            }

            System.out.println();
        }
    }

    private static void findMatchDataById(int id, List<Match> matchData) {
        Map<String, Object> matchDetails = new HashMap<>();

        if (matchData == null || matchData.isEmpty()) {
            return;
        }

        for (int i = 0; i < matchData.size(); i++) {
            Match match = matchData.get(i);

            if (match.getId() == id) {
                matchDetails.put("id", match.getId());
                matchDetails.put("season", match.getSeason());
                matchDetails.put("city", match.getCity());
                matchDetails.put("date", match.getDate());
                matchDetails.put("team1", match.getTeam1());
                matchDetails.put("team2", match.getTeam2());
                matchDetails.put("toss_winner", match.getTossWinner());
                matchDetails.put("toss_decision", match.getTossDecision());
                matchDetails.put("result", match.getResult());
                matchDetails.put("winner", match.getWinner());

                break;
            }
        }

        System.out.println("-".repeat(40));
        if (matchDetails.isEmpty()) {
            System.out.println("Data not found..!!");
        }

        for (Map.Entry<String, Object> entry : matchDetails.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void findLast_5_Match(int n, List<Match> matchData) {
        List<Map<String, String>> matchDetails = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        if (matchData == null || matchData.isEmpty()) {
            return;
        }

        int numberOfLines = matchData.size();
        map.put("Total Matches", String.valueOf(numberOfLines - 1));
        matchDetails.add(map);

        for (int i = 1; i <= n && i < numberOfLines; i++) {
            Match match = matchData.get(numberOfLines - i);
            Map<String, String> data = new HashMap<>();

            data.put("Match id", String.valueOf(match.getId()));
            data.put("city", match.getCity());
            data.put("date", match.getDate());
            data.put("team1", match.getTeam1());
            data.put("team2", match.getTeam2());
            data.put("toss win", match.getTossWinner());
            data.put("toss decision", match.getTossDecision());
            data.put("result", match.getResult());

            matchDetails.add(data);
        }

        System.out.println("-".repeat(40));
        if (matchDetails.isEmpty()) {
            System.err.println("Data not found..!!");
        }
        for (Map<String, String> match : matchDetails) {
            System.out.println(match);
        }
    }

    private static void findExtraRun(int year, List<Match> matchesData, List<Deliveries> deliveriesData) {
        Map<String, Integer> extraRuns = new TreeMap<>();

        if (matchesData == null || deliveriesData == null || matchesData.isEmpty() || deliveriesData.isEmpty()) {
            return;
        }

        Set<Integer> matchIds = new TreeSet<>();

        for (int i = 0; i < matchesData.size(); i++) {
            Match match = matchesData.get(i);

            if (match.getSeason() == year) {
                matchIds.add(match.getId());
            }
        }

        for (int i = 0; i < deliveriesData.size(); i++) {
            Deliveries deliveries = deliveriesData.get(i);
            int id = deliveries.getMatchId();
            boolean flag = matchIds.contains(id);

            if (flag) {
                String team = deliveries.getBattingTeam();
                int extraRun = deliveries.getExtraRuns();
                extraRuns.merge(team, extraRun, Integer::sum);
            }
        }

        System.out.println("-".repeat(40));
        if (extraRuns.isEmpty()) {
            System.err.println("Data not found..!!");
        }

        for (Map.Entry<String, Integer> entry : extraRuns.entrySet()) {
            System.out.println("Team: " + entry.getKey() + " -> extra: " + entry.getValue());
        }
    }

    private static void findBestEconomicalBowler(int year, List<Deliveries> deliveriesData, List<Match> matchesData) {

        if (deliveriesData == null || matchesData == null || matchesData.isEmpty() || deliveriesData.isEmpty()) {
            return;
        }

        Set<Integer> matchIds = new HashSet<>();
        for (int i = 0; i < matchesData.size(); i++) {
            Match match = matchesData.get(i);

            if (match.getSeason() == year) {
                matchIds.add(match.getId());
            }
        }

        if (matchIds.isEmpty()) {
            return;
        }

        Map<String, Integer> runsConceded = new HashMap<>();
        Map<String, Integer> ballsBowled = new HashMap<>();

        for (int i = 0; i < deliveriesData.size(); i++) {
            Deliveries deliveries = deliveriesData.get(i);
            int matchId = deliveries.getMatchId();
            if (!matchIds.contains(matchId))
                continue;

            String bowler = deliveries.getBowler();
            int totalRuns = deliveries.getTotalRuns();
            int wide = deliveries.getWideRuns();
            int noBall = deliveries.getNoballRuns();
            runsConceded.merge(bowler, totalRuns, Integer::sum);

            if (wide == 0 && noBall == 0) {
                ballsBowled.merge(bowler, 1, Integer::sum);
            }
        }

        String bestBowler = null;
        double bestEconomy = Double.MAX_VALUE;
        TreeSet<Double> bestEconomys = new TreeSet<>();

        for (String bowler : runsConceded.keySet()) {
            int runs = runsConceded.getOrDefault(bowler, 0);
            int balls = ballsBowled.getOrDefault(bowler, 0);
            if (balls <= 6)
                continue;
            double overs = balls / 6.0;
            double economy = runs / overs;
            bestEconomys.add(economy);

            if (economy < bestEconomy) {
                bestEconomy = economy;
                bestBowler = bowler;
            }
        }

        System.out.println("-".repeat(40));
        System.out.println(bestBowler + " (" + String.format("%.2f", bestEconomy) + ")");

    }

    private static void findPartnership(String pName, List<Deliveries> deliveriesData) {
        Map<String, Integer> partnership = new TreeMap<>();

        if (deliveriesData == null || deliveriesData.isEmpty()) {
            return;
        }

        for (Deliveries deliveries : deliveriesData) {
            if (deliveries.getBatsman().equalsIgnoreCase(pName)) {
                int runs = deliveries.getBatsmanRuns();
                String nonStricker = deliveries.getNonStriker();
                String key = pName + " " + nonStricker;
                partnership.put(key, partnership.getOrDefault(key, 0) + runs);
            }
        }

        System.out.println("-".repeat(40));
        if (partnership.isEmpty()) {
            System.err.println("Data not found ..!!");
        }
        for (Map.Entry<String, Integer> entry : partnership.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void findTopScorePerTeamPerSession(List<Deliveries> deliveriesData, List<Match> matchData) {
        Map<Integer, Map<String, String>> topScorePerTeam = new HashMap<>();
        Map<Integer, Integer> matchIdToSeason = new HashMap<>();

        if (deliveriesData == null || matchData == null || deliveriesData.isEmpty() || matchData.isEmpty()) {
            return;
        }

        for (Match match : matchData) {
            matchIdToSeason.put(match.getId(), match.getSeason());
        }

        Map<Integer, Map<String, Map<String, Integer>>> runsMap = new HashMap<>();

        for (Deliveries d : deliveriesData) {
            Integer season = matchIdToSeason.get(d.getMatchId());
            if (season == null)
                continue;

            String battingTeam = d.getBattingTeam();
            String batsman = d.getBatsman();
            int runs = d.getBatsmanRuns();

            Map<String, Map<String, Integer>> teamMap = runsMap.get(season);
            if (teamMap == null) {
                teamMap = new HashMap<>();
                runsMap.put(season, teamMap);
            }

            Map<String, Integer> batsmanMap = teamMap.get(battingTeam);
            if (batsmanMap == null) {
                batsmanMap = new HashMap<>();
                teamMap.put(battingTeam, batsmanMap);
            }

            int totalRuns = batsmanMap.getOrDefault(batsman, 0) + runs;
            batsmanMap.put(batsman, totalRuns);
        }

        for (Map.Entry<Integer, Map<String, Map<String, Integer>>> seasonEntry : runsMap.entrySet()) {
            int season = seasonEntry.getKey();
            Map<String, String> teamTopScorers = new HashMap<>();

            for (Map.Entry<String, Map<String, Integer>> teamEntry : seasonEntry.getValue().entrySet()) {
                String team = teamEntry.getKey();
                Map<String, Integer> batsmanRuns = teamEntry.getValue();

                String topBatsman = null;
                int maxRuns = -1;

                for (Map.Entry<String, Integer> br : batsmanRuns.entrySet()) {
                    if (br.getValue() > maxRuns) {
                        maxRuns = br.getValue();
                        topBatsman = br.getKey();
                    }
                }

                if (topBatsman != null) {
                    teamTopScorers.put(team, topBatsman + " (" + maxRuns + ")");
                }
            }
            topScorePerTeam.put(season, teamTopScorers);
        }

        System.out.println("-".repeat(40));
        if (topScorePerTeam.isEmpty()) {
            System.err.println("Data not found..!!");
        }

        for (Map.Entry<Integer, Map<String, String>> sessionEntry : topScorePerTeam.entrySet()) {
            Integer session = sessionEntry.getKey();
            System.out.println("Season: " + session);
            Map<String, String> teamTopScorer = sessionEntry.getValue();

            for (Map.Entry<String, String> teamEntry : teamTopScorer.entrySet()) {
                System.out.println(teamEntry.getKey() + " -> " + teamEntry.getValue());
            }

            System.out.println();
        }
    }

    private static void findPlayerHeighestRunLast5OverAgainstMI(List<Match> matchData, List<Deliveries> deliveriesData, String teamBol, int season) {

        Map<String, String> result = new HashMap<>();
        Map<String, Set<Integer>> teamMatches = new HashMap<>();

        if (matchData == null || deliveriesData == null || matchData.isEmpty() || deliveriesData.isEmpty() || teamBol == null || season == 0) {
            return;
        }

        for (Match match : matchData) {
            if ((match.getSeason() == season) && (match.getTeam1().equalsIgnoreCase(teamBol) || match.getTeam2().equalsIgnoreCase(teamBol))) {
                String opponent = match.getTeam1().equalsIgnoreCase(teamBol) ? match.getTeam2() : match.getTeam1();

                if (!teamMatches.containsKey(opponent)) {
                    teamMatches.put(opponent, new HashSet<>());
                }

                teamMatches.get(opponent).add(match.getId());
            }
        }

        for (String team : teamMatches.keySet()) {
            Set<Integer> matchIds = teamMatches.get(team);

            Map<String, Integer> batsmanRuns = new HashMap<>();

            for (Deliveries d : deliveriesData) {
                if (matchIds.contains(d.getMatchId()) && d.getBattingTeam().equalsIgnoreCase(team) && d.getOver() >= 16) {
                    String batsman = d.getBatsman();
                    int runs = d.getBatsmanRuns();
                    int totalRuns = batsmanRuns.getOrDefault(batsman, 0) + runs;
                    batsmanRuns.put(batsman, totalRuns);
                }
            }

            String topBatsman = null;
            int maxRuns = -1;
            for (Map.Entry<String, Integer> entry : batsmanRuns.entrySet()) {
                if (entry.getValue() > maxRuns) {
                    maxRuns = entry.getValue();
                    topBatsman = entry.getKey();
                }
            }

            if (topBatsman != null) {
                result.put(team, topBatsman + " (" + maxRuns + " runs) ");
            }
        }

        System.out.println("-".repeat(40));
        if (result.isEmpty()) {
            System.err.println("Data not found..!!");
        }

        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println(entry.getKey() + "-> " + entry.getValue());
        }

    }
}