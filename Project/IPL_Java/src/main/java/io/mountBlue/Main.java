package io.mountBlue;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome in my IPL project..!!");
        Scanner sc = new Scanner(System.in);
        DataBase dataBase = new DataBase();
        MatchService matchService = new MatchService();
        DeliveriesService deliveriesService = new DeliveriesService();

        List<Match> matchData = dataBase.getMatchData();
        List<Deliveries> deliveriesData = dataBase.getDeliveriesData();

        boolean flag = true;
        while(flag) {
            System.out.println("\n---------------------Menu------------------------------------");
            System.out.println("1. Number of matches played per year of all the years in IPL ");
            System.out.println("2. Number of matches won of all teams over all the years of IPL.");
            System.out.println("3. For the year 2016 get the extra runs conceded per team.");
            System.out.println("4. For the year 2015 get the top economical bowlers.");
            System.out.println("5. Get match details by match id.");
            System.out.println("6. Last 5 match details");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice){
                case 0:
                    flag = false;
                    System.out.println("Thank you for using..!!");
                    break;

                case 1:
                    Map<Integer, Integer> result1 = matchService.getMatchYearWise(matchData);
                    System.out.println(result1);
                    break;

                case 2:
                    Map<String, Map<Integer, Integer>> result2 = matchService.getMatchWonTeamWise(matchData);
                    for (Map.Entry<String, Map<Integer, Integer>> teamEntry : result2.entrySet()) {
                        String team = teamEntry.getKey();
                        System.out.println("Team: " + team);

                        Map<Integer, Integer> yearWiseWins = teamEntry.getValue();
                        for (Map.Entry<Integer, Integer> yearEntry : yearWiseWins.entrySet()) {
                            System.out.println("   Year: " + yearEntry.getKey() + " → Wins: " + yearEntry.getValue());
                        }
                        System.out.println();
                    }
                    break;

                case 3:
                    Map<String, Integer> result3 = deliveriesService.getExtraRun(2016, matchData, deliveriesData);
                    for(Map.Entry<String, Integer> entry : result3.entrySet()){
                        System.out.println("Team: "+ entry.getKey() +" -> extra: " + entry.getValue());
                    }
                    break;

                case 4:
                    List<String> result = deliveriesService.getBestEconomicalBowler(2017, deliveriesData, matchData);
                    System.out.println(result);
                    break;

                case 5:
                    Map<String, Object> matchDataById = matchService.getMatchDataById(635, matchData);
                    for(Map.Entry<String, Object> entry : matchDataById.entrySet()){
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;

                case 6:
                    List<Map<String, String>> matchDetails = matchService.getLastMatch(5, matchData);
                    for(Map<String, String> match: matchDetails){
                        System.out.println(match);
                    }
                    break;
                default:
                    System.err.println("wrong input..!!");
                    break;
            }
        }

    }
}