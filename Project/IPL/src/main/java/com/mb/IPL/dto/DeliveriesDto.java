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
        List<String> deliveriesData = iplDao.DeliveriesData();
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
        List<String> deliveriesData = iplDao.DeliveriesData();

        int totalRuns = 0, ballsFaced = 0;
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
        result.put("totalRuns", totalRuns);
        result.put("highestRunsInMatch", highestRuns);
        result.put("strikeRate", strikeRate);
        result.put("economy", economy);
        result.put("totalWickets", totalWickets);
        result.put("bestBowling", bestWickets);

        return result;
    }


    public Map<String, Integer> getExtraRun(int year) {
        List<String> deliveriesData = iplDao.DeliveriesData();
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
}
