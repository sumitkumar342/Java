package com.mb.IPL.dto;

import com.mb.IPL.dao.IplDao;
import com.mb.IPL.exception.IdNotFoundException;
import com.mb.IPL.exception.PlaceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class IplDto {
    @Autowired
    private  IplDao iplDao;

    public List<Map<String, Object>> home() {
        List<Map<String, Object>> response = new ArrayList<>();

        List<String> matchData = iplDao.matchesData();
        Map<String, Object> map = new HashMap<>();
        Long numberOfLines = (long) matchData.size();
        map.put("Matches", numberOfLines-1);
        response.add(map);

        for (int i = 1; i <= 6 && i < numberOfLines; i++) {
            String match = matchData.get((int) (numberOfLines - i));
            response.add(setMatchData(match));
        }

        return response;
    }

    private Map<String, Object> setMatchData(String s) {
        Map<String, Object> data = new HashMap<>();
        String[] ar = s.split(",");

        if (ar.length > 10) {  // ensure enough columns
            data.put("Match id", ar[0]);
            data.put("city", ar[2]);
            data.put("date", ar[3]);
            data.put("team1", ar[4]);
            data.put("team2", ar[5]);
            data.put("toss win", ar[6]);
            data.put("toss decision", ar[7]);
            data.put("result", ar[10]);
        }
        return data;
    }


    public Map<String, Object> getMatchById(int id) {
        List<String> matchData = iplDao.matchesData();
        Map<String, Object> map = new HashMap<>();

        String header = matchData.get(0);
        String[] columns = header.split(",");

        for (int i = 1; i < matchData.size(); i++) {
            String[] row = matchData.get(i).split(",");
            int matchId = Integer.parseInt(row[0]);

            if (matchId == id) {
                for (int j = 0; j < columns.length && j < row.length; j++) {
                    map.put(columns[j], row[j]);
                }
                break;
            }
        }
        if(map.isEmpty()){
            throw new IdNotFoundException("Match id: " + id);
        } else {
            return map;
        }
    }

    public List<Map<String, String>> getPlaced(String place) {
        List<String> matchData = iplDao.matchesData();
        List<Map<String, String>> listMap = new ArrayList<>();
        String header = matchData.get(0);
        String[] columns = header.split(",");

        for (int i = 1; i < matchData.size(); i++) {
            String[] row = matchData.get(i).split(",");
            String matchPlace = row[2];
            if (matchPlace.equalsIgnoreCase(place)) {
                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < columns.length && j < row.length; j++) {
                    map.put(columns[j], row[j]);
                }
                listMap.add(map);
            }
        }
        if(listMap.isEmpty()){
            throw new PlaceNotFoundException("City: " + place);
        } else {
            return listMap;
        }
    }

    public Map<Integer, Integer> getMatchYearWise() {
        List<String> matchData = iplDao.matchesData();
        Map<Integer, Integer> matchMap = new HashMap<>();
        for(int i=1; i<matchData.size(); i++){
            String[] row = matchData.get(i).split(",");
            int season = Integer.valueOf(row[1]);
            matchMap.put(season, matchMap.getOrDefault(season, 0)+1);

        }

        return matchMap;
    }

    public Map<String, Map<Integer, Integer>> getMatchWonTeamWise() {
        List<String> matchData = iplDao.matchesData();
        Map<String, Map<Integer, Integer>> teamData = new TreeMap<>();
        for(int i=1; i<matchData.size(); i++){
            String[] row = matchData.get(i).split(",");
            int season = Integer.parseInt(row[1]);
            String winningTeam = row[10];
            if (winningTeam == null || winningTeam.trim().isEmpty()) {
                continue;
            }
            Map<Integer, Integer> yearMap = teamData.getOrDefault(winningTeam, new TreeMap<>());

            yearMap.put(season, yearMap.getOrDefault(season, 0) + 1);

            teamData.put(winningTeam, yearMap);

        }
        return teamData;
    }
}
