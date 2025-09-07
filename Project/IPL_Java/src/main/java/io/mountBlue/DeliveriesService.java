package io.mountBlue;

import java.util.*;

public class DeliveriesService {
    public Map<String, Integer> getExtraRun(
            int year,
            List<Match> matchesData,
            List<Deliveries> deliveriesData) {

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
        Set<Integer> matchIds = new HashSet<>();
        for (int i=0; i<matchesData.size(); i++) {
            Match match = matchesData.get(i);
            if (match.getSeason() == year) {
                matchIds.add(match.getId());
            }
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
        List<String> result = new ArrayList<>();
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

}
