package com.mb.IPL.dto;

import com.mb.IPL.dao.IplDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DeliveriesDto {
    private static final Logger logger = LoggerFactory.getLogger(DeliveriesDto.class);

    @Autowired
    private IplDao iplDao;

    public Map<String, Object> getSummary(int matchId) {
        List<String> deliveriesData = iplDao.deliveriesData();
        List<String> matchesData = iplDao.matchesData();

        String team1 = null, team2 = null, winner = null, manOfTheMatch = null;

        for (int i=1; i< matchesData.size(); i++) {
            String[] cols = matchesData.get(i).split(",");
            int mId = Integer.parseInt(cols[0]);
            if (mId == matchId) {
                team1 = cols[4];
                team2 = cols[5];
                winner = cols[10];
                manOfTheMatch= cols[13];
                break;
            }
        }

        Map<String, Integer> batsmanRuns1 = new HashMap<>();
        Map<String, Integer> batsmanRuns2 = new HashMap<>();
        Map<String, Set<Integer>> bowlerOvers1 = new HashMap<>();
        Map<String, Set<Integer>> bowlerOvers2 = new HashMap<>();

        for (int i=1; i<deliveriesData.size(); i++) {
            String[] cols = deliveriesData.get(i).split(",");
            int mId = Integer.parseInt(cols[0]);
            if (mId != matchId)
                continue;

            String battingTeam = cols[2];
            String bowlingTeam = cols[3];
            String batsman = cols[6];
            String bowler = cols[8];
            int over = Integer.parseInt(cols[4]);
            int batsmanRunsScored = Integer.parseInt(cols[15]);

            if (battingTeam.equals(team1)) {
                batsmanRuns1.merge(batsman, batsmanRunsScored, Integer::sum);
            }
            if (battingTeam.equals(team2)) {
                batsmanRuns2.merge(batsman, batsmanRunsScored, Integer::sum);
            }
            if (bowlingTeam.equals(team1)) {
                bowlerOvers1.computeIfAbsent(bowler, k -> new HashSet<>()).add(over);
            }
            if (bowlingTeam.equals(team2)) {
                bowlerOvers2.computeIfAbsent(bowler, k -> new HashSet<>()).add(over);
            }
        }

        Map<String, Integer> overs1 = new HashMap<>();
        for (var e : bowlerOvers1.entrySet()) {
            overs1.put(e.getKey(), e.getValue().size());
        }
        Map<String, Integer> overs2 = new HashMap<>();
        for (var e : bowlerOvers2.entrySet()) {
            overs2.put(e.getKey(), e.getValue().size());
        }

        Map<String, Object> summary = new HashMap<>();
        summary.put("team1", Map.of("name", team1, "batsmanRuns", batsmanRuns1, "bowlerOvers", overs1));
        summary.put("team2", Map.of("name", team2, "batsmanRuns", batsmanRuns2, "bowlerOvers", overs2));
        summary.put("winner", winner);
        summary.put("Man of the match", manOfTheMatch);

        return summary;
    }


    public Map<String, Object> getPlayer(String name) {
        List<String> deliveriesData = iplDao.deliveriesData();

        int totalRuns = 0, ballsFaced = 0,  totalMatch=0;
        Map<Integer, Integer> runsPerMatch = new HashMap<>();

        int runsConceded = 0, ballsBowled = 0;
        int totalWickets = 0;
        Map<Integer, Integer> wicketsPerMatch = new HashMap<>();

        for (int i = 1; i < deliveriesData.size(); i++) {
            String[] cols = deliveriesData.get(i).split(",");
            int matchId = Integer.parseInt(cols[0]);
            String batsman = cols[6];
            String bowler = cols[8];
            int batsmanRuns = Integer.parseInt(cols[15]);
            int totalRun = Integer.parseInt(cols[17]);

            String dismissedPlayer = cols.length > 18 ? cols[18] : null;
            String dismissalKind = cols.length > 19 ? cols[19] : null;


            if (batsman.equals(name)) {
                totalRuns += batsmanRuns;
                runsPerMatch.merge(matchId, batsmanRuns, Integer::sum);
                ballsFaced++;
            }

            if (bowler.equals(name)) {
                runsConceded += totalRun;
                int extraBall = Integer.parseInt(cols[10]) + Integer.parseInt(cols[13]);;
                if(extraBall == 0){
                    ballsBowled++;
                }

                if (dismissedPlayer != null && !dismissedPlayer.isEmpty() &&
                        dismissalKind != null &&
                        !dismissalKind.equalsIgnoreCase("stumped") &&
                        !dismissalKind.equalsIgnoreCase("run out") &&
                        !dismissalKind.equalsIgnoreCase("retired hurt") &&
                        !dismissalKind.equalsIgnoreCase("obstructing the field")) {

                    totalWickets++;
                    wicketsPerMatch.merge(matchId, 1, Integer::sum);
                }
            }
        }

        int highestRuns = runsPerMatch.values().stream().max(Integer::compare).orElse(0);
        int bestWickets = wicketsPerMatch.values().stream().max(Integer::compare).orElse(0);

        double oversBowled = ballsBowled / 6.0;
        double economy = (oversBowled == 0) ? 0.0 : runsConceded / oversBowled;
        double strikeRate = (ballsFaced == 0) ? 0.0 : (totalRuns * 100.0 / ballsFaced);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("player", name);
        result.put("Match", runsPerMatch.size());
        result.put("totalRuns", totalRuns);
        result.put("highestRunsInMatch", highestRuns);
        result.put("strikeRate", strikeRate);
        result.put("economy", economy);
        result.put("totalWickets", totalWickets);
        result.put("bestBowling", bestWickets);

        return result;
    }


    public Map<String, Integer> getExtraRun(int year) {
        List<String> deliveriesData = iplDao.deliveriesData();
        List<String> matchesData = iplDao.matchesData();
        Map<String, Integer> extraRuns = new TreeMap<>();
        Set<Integer> matchId = new TreeSet<>();

        for(int i=1; i<matchesData.size(); i++){
            String[] cols = matchesData.get(i).split(",");
            int session = Integer.parseInt(cols[1]);
            if(session == year){
                matchId.add(Integer.parseInt(cols[0]));
            }
        }
        for(int i=1; i<deliveriesData.size(); i++){
            String[] cols = deliveriesData.get(i).split(",");
            int id = Integer.parseInt(cols[0]);
            boolean flag = matchId.contains(id);
            if(flag){
                String team = cols[3];
                int extraRun = Integer.parseInt(cols[16]);
                extraRuns.merge(team, extraRun, Integer::sum);
            }
        }
        return extraRuns;
    }

    public List<String> getBestEconomicalBowler(int year) {
        List<String> deliveriesData = iplDao.deliveriesData();
        List<String> matchesData = iplDao.matchesData();
        Set<String> matchIds = new HashSet<>();
        for (int i=1; i<matchesData.size(); i++) {
            String[] cols = matchesData.get(i).split(",");
            if (Integer.parseInt(cols[1]) == year) {
                matchIds.add(cols[0]);
            }
        }
        Map<String, Integer> runsConceded = new HashMap<>();
        Map<String, Integer> ballsBowled = new HashMap<>();
        for (int i=1; i<deliveriesData.size(); i++) {
            String[] cols = deliveriesData.get(i).split(",");
            String matchId = cols[0];
            if (!matchIds.contains(matchId))
                continue;

            String bowler = cols[8];
            int totalRuns = Integer.parseInt(cols[17]);
            int wide = Integer.parseInt(cols[10]);
            int noBall = Integer.parseInt(cols[13]);
            runsConceded.merge(bowler, totalRuns, Integer::sum);
            if (wide == 0 && noBall == 0) {
                ballsBowled.merge(bowler, 1, Integer::sum);
            }
        }
        String bestBowler = null;
        double bestEconomy = Double.MAX_VALUE;
        TreeSet<Double> bestEconomys = new TreeSet<>();
        List<String> result = new ArrayList<>();
        for (String bowler : runsConceded.keySet()) {
            int runs = runsConceded.getOrDefault(bowler, 0);
            int balls = ballsBowled.getOrDefault(bowler, 0);
            if (balls == 0)
                continue;
            double overs = balls / 6.0;
            double economy = runs / overs;
            bestEconomys.add(economy);
            if (economy < bestEconomy) {
                bestEconomy = economy;
                bestBowler = bowler;
            }
        }
        result.add(bestBowler + " (" + String.format("%.2f", bestEconomy) + ")");

//        ArrayList<Double> sortedEco = new ArrayList<>(bestEconomys);
//        for (String bowler : runsConceded.keySet()) {
//            int runs = runsConceded.getOrDefault(bowler, 0);
//            int balls = ballsBowled.getOrDefault(bowler, 0);
//            if (balls == 0)
//                continue;
//            double overs = balls / 6.0;
//            double economy = runs / overs;
//            if (economy <= sortedEco.get(9)) {
//               result.add(bowler + " (" + String.format("%.2f", economy) + ")");
//            }
//
//        }
        return result;
    }

    public String heighestrunchess() {
        List<String> deliveriesData = iplDao.deliveriesData();
        List<String> matchData = iplDao.matchesData();
        String result = null;

        Set<Integer> matchId = new TreeSet<>();
        Map<Integer, Map<Integer, String>> teams = new HashMap<>();
        for(int i=1; i<matchData.size(); i++){
            String[] cols = matchData.get(i).split(",");
            int mId = Integer.parseInt(cols[0]);
            matchId.add(mId);
            String team1 = cols[4];
            String team2 = cols[5];
            Map<Integer, String> team = new HashMap<>();
            if(cols[7].equals("bat") && team1.equals(cols[6])){
                team.put(1, team1);
                team.put(2, team2);
            }else {
                team.put(2, team1);
                team.put(1, team2);
            }
            teams.put(mId, team);
        }

        Map<Integer, Map<Integer, Integer>> runs = new TreeMap<>();
        for(int i=1; i<deliveriesData.size(); i++){
            String[] cols = deliveriesData.get(i).split(",");
            int id = Integer.parseInt(cols[0]);
            int inning = Integer.parseInt(cols[1]);
            int run = Integer.parseInt(cols[17]);
            Map<Integer, Integer> teamWiseRun = runs.getOrDefault(id, new TreeMap<>());
            teamWiseRun.put(inning, teamWiseRun.getOrDefault(inning, 0)+run);
            runs.put(id, teamWiseRun);
        }
        int maxChess = Integer.MIN_VALUE;
        int runDiff = Integer.MAX_VALUE;
        int winningMatchId = -1;
        for(int mId: matchId){
            Map<Integer, Integer> teamWiseRun= runs.get(mId);
            if(teamWiseRun.size() == 2){
                int team1 = teamWiseRun.get(1);
                int team2 = teamWiseRun.get(2);
                if (team1 < team2 && team2 > maxChess) {
                    maxChess = team2;
                    winningMatchId = mId;
                    runDiff = team2 - team1;
                }
            }
        }
        Map<Integer, String> match = teams.get(winningMatchId);
        String winningTeam = match.get(2);
        result = "Highest run chase team is " + winningTeam + " with run " + maxChess;
        return result;
    }

//    public List<Object> getDetails(String pname, String place) {
//        List<String> matchData = iplDao.matchesData();
//        List<String> deliveryData = iplDao.DeliveriesData();
//        List<Object> result = new ArrayList<>();
//
//        Set<Integer> matchId = new TreeSet<>();
//        for(int i=1; i<matchData.size(); i++){
//            String[] cols = matchData.get(i).split(",");
//            String places = cols[2];
//            if(places.equalsIgnoreCase(place)){
//                matchId.add(Integer.parseInt(cols[0]));
//            }
//        }
//
//        HashMap<Integer, Integer> totalRunsPerMatch = new HashMap<>();
//        HashMap<Integer, Integer> typesOfRun = new HashMap<>();
//        for(int i=1; i<deliveryData.size(); i++){
//            String[] cols = deliveryData.get(i).split(",");
//            int id = Integer.parseInt(cols[0]);
//            boolean flag = matchId.contains(id);
//            if(flag && cols[6].equalsIgnoreCase(pname)){
//                int run = Integer.parseInt(cols[15]);
//                totalRunsPerMatch.put(id, totalRunsPerMatch.getOrDefault(id, 0)+run);
//                if(1 == run || 2 == run || 3 == run || 4 == run || 6==run){
//                    typesOfRun.put(run, typesOfRun.getOrDefault(run, 0)+1);
//                }
//            }
//        }
//
//        result.add("Player name is: "+pname);
//        result.add(totalRunsPerMatch);
//        result.add(typesOfRun);
//
//        return result;
//    }
}
