package io.mountBlue;

import java.util.*;

public class MatchService {

    public Map<Integer, Integer> getMatchYearWise(List<Match> matchesData) {
        Map<Integer, Integer> matchMap = new HashMap<>();
        for(int i=0; i<matchesData.size(); i++){
            Match match = matchesData.get(i);
            int season = match.getSeason();
            matchMap.put(season, matchMap.getOrDefault(season, 0)+1);
        }
        return matchMap;
    }

    public Map<String, Map<Integer, Integer>> getMatchWonTeamWise(List<Match> matchData) {
        Map<String, Map<Integer, Integer>> teamData = new TreeMap<>();
        for(int i=0; i<matchData.size(); i++){
            Match match = matchData.get(i);
            int season = match.getSeason();
            String winningTeam = match.getWinner();
            if (winningTeam == null || winningTeam.trim().isEmpty()) {
                continue;
            }
            Map<Integer, Integer> yearMap = teamData.getOrDefault(winningTeam, new TreeMap<>());
            yearMap.put(season, yearMap.getOrDefault(season, 0) + 1);
            teamData.put(winningTeam, yearMap);
        }
        return teamData;
    }

    public Map<String, Object> getMatchDataById(int id, List<Match> matchData) {
        Map<String, Object> matchDetails = new HashMap<>();

        for (int i = 0; i < matchData.size(); i++) {
            Match match = matchData.get(i);
            int matchId = match.getId();

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
        return matchDetails;
    }

    public List<Map<String, String>> getLastMatch(int n, List<Match> matchData) {
        List<Map<String, String>> response = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        int numberOfLines =  matchData.size();
        map.put("Total Matches", String.valueOf(numberOfLines-1));
        response.add(map);

        for (int i = 1; i <= n && i < numberOfLines; i++) {
            Match match = matchData.get( numberOfLines - i);
            Map<String, String> data = new HashMap<>();

            data.put("Match id", String.valueOf(match.getId()));
            data.put("city", match.getCity());
            data.put("date", match.getDate());
            data.put("team1", match.getTeam1());
            data.put("team2", match.getTeam2());
            data.put("toss win", match.getTossWinner());
            data.put("toss decision", match.getTossDecision());
            data.put("result", match.getResult());

            response.add(data);
        }
        return response;
    }

    public Map<String, Integer> getExtraRun(int year, List<Match> matchesData, List<Deliveries> deliveriesData) {

        Map<String, Integer> extraRuns = new TreeMap<>();
        Set<Integer> matchIds = new TreeSet<>();

        for (int i = 0; i < matchesData.size(); i++) {
            Match match = matchesData.get(i);
            if (match.getSeason() == year) {
                matchIds.add(match.getId());
            }
        }

        for(int i=0; i<deliveriesData.size(); i++){
            Deliveries deliveries = deliveriesData.get(i);
            int id = deliveries.getMatchId();
            boolean flag = matchIds.contains(id);
            if(flag){
                String team = deliveries.getBattingTeam();
                int extraRun = deliveries.getExtraRuns();
                extraRuns.merge(team, extraRun, Integer::sum);
            }
        }
        return extraRuns;
    }

    public List<String> getBestEconomicalBowler(int year, List<Deliveries> deliveriesData, List<Match> matchesData) {
        List<String> result = new ArrayList<>();
        Set<Integer> matchIds = new HashSet<>();
        for (int i=0; i<matchesData.size(); i++) {
            Match match = matchesData.get(i);
            if (match.getSeason() == year) {
                matchIds.add(match.getId());
            }
        }
        if(matchIds.isEmpty()){
            return result;
        }
        Map<String, Integer> runsConceded = new HashMap<>();
        Map<String, Integer> ballsBowled = new HashMap<>();
        for (int i=0; i<deliveriesData.size(); i++) {
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
            if (balls == 6)
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
        return result;
    }

    public Map<String, Integer> getPartnership(String pName, List<Deliveries> deliveriesData) {
        Map<String, Integer> partnership = new TreeMap<>();
        for(Deliveries deliveries : deliveriesData){
            if(deliveries.getBatsman().equalsIgnoreCase(pName)){
                int runs = deliveries.getBatsmanRuns();
                String nonStricker = deliveries.getNonStriker();
                String key = pName + " " + nonStricker;
                partnership.put(key, partnership.getOrDefault(key, 0)+runs);
            }
        }
        return partnership;
    }
}
