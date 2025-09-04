package com.mb.IPL.dtoTest;

import com.mb.IPL.dao.IplDao;
import com.mb.IPL.dto.DeliveriesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DeliveriesDtoTest {
    @Mock
    private IplDao iplDao;

    @InjectMocks
    private DeliveriesDto deliveriesDto;

    private List<String> matchesData;
    private List<String> deliveriesData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock matches CSV data
        matchesData = Arrays.asList(
                "id,season,city,date,team1,team2,toss_winner,toss_decision,result,dl_applied,winner,win_by_runs,win_by_wickets,player_of_match,venue,umpire1,umpire2,umpire3",
                "1,2017,Hyderabad,05-04-2017,Sunrisers Hyderabad,Royal Challengers Bangalore,Royal Challengers Bangalore,field,normal,0,Sunrisers Hyderabad,35,0,Yuvraj Singh,Rajiv Gandhi International Stadium, Uppal,AY Dandekar,NJ Llong",
                "2,2017,Pune,06-04-2017,Mumbai Indians,Rising Pune Supergiant,Rising Pune Supergiant,field,normal,0,Rising Pune Supergiant,0,7,SPD Smith,Maharashtra Cricket Association Stadium,A Nand Kishore,S Ravi",
                "3,2017,Rajkot,07-04-2017,Gujarat Lions,Kolkata Knight Riders,Kolkata Knight Riders,field,normal,0,Kolkata Knight Riders,0,10,CA Lynn,Saurashtra Cricket Association Stadium,Nitin Menon,CK Nandan"
        );

        // Mock deliveries CSV data
        deliveriesData = Arrays.asList(
                "match_id,inning,batting_team,bowling_team,over,ball,batsman,non_striker,bowler,is_super_over,wide_runs,bye_runs,legbye_runs,noball_runs,penalty_runs,batsman_runs,extra_runs,total_runs,player_dismissed,dismissal_kind,fielder",
                "1,1,Sunrisers Hyderabad,Royal Challengers Bangalore,1,1,DA Warner,S Dhawan,TS Mills,0,0,0,0,0,0,0,0,0,,,",
                "1,1,Sunrisers Hyderabad,Royal Challengers Bangalore,1,2,DA Warner,S Dhawan,TS Mills,0,0,0,0,0,0,0,0,0,,,",
                "1,1,Sunrisers Hyderabad,Royal Challengers Bangalore,1,3,DA Warner,S Dhawan,TS Mills,0,0,0,0,0,0,4,0,4,,,",
                "1,1,Sunrisers Hyderabad,Royal Challengers Bangalore,1,4,DA Warner,S Dhawan,TS Mills,0,0,0,0,0,0,0,0,0,,,",
                "1,1,Sunrisers Hyderabad,Royal Challengers Bangalore,1,5,DA Warner,S Dhawan,TS Mills,0,2,0,0,0,0,0,2,2,,,",
                "1,1,Sunrisers Hyderabad,Royal Challengers Bangalore,1,6,S Dhawan,DA Warner,TS Mills,0,0,0,0,0,0,0,0,0,,,",
                "1,1,Sunrisers Hyderabad,Royal Challengers Bangalore,1,7,S Dhawan,DA Warner,TS Mills,0,0,0,1,0,0,0,1,1,,,",
                "1,1,Sunrisers Hyderabad,Royal Challengers Bangalore,2,1,S Dhawan,DA Warner,A Choudhary,0,0,0,0,0,0,1,0,1,,,",
                "1,1,Sunrisers Hyderabad,Royal Challengers Bangalore,2,2,DA Warner,S Dhawan,A Choudhary,0,0,0,0,0,0,4,0,4,,,",
        //2nd inning
                "1,2,Royal Challengers Bangalore,Sunrisers Hyderabad,1,1,CH Gayle,Mandeep Singh,A Nehra,0,0,0,0,0,0,1,0,1,,,",
                "1,2,Royal Challengers Bangalore,Sunrisers Hyderabad,1,2,Mandeep Singh,CH Gayle,A Nehra,0,0,0,0,0,0,0,0,0,,,",
                "1,2,Royal Challengers Bangalore,Sunrisers Hyderabad,1,3,Mandeep Singh,CH Gayle,A Nehra,0,0,0,0,0,0,0,0,0,,,",
                "1,2,Royal Challengers Bangalore,Sunrisers Hyderabad,1,4,Mandeep Singh,CH Gayle,A Nehra,0,0,0,0,0,0,2,0,2,,,",
                "1,2,Royal Challengers Bangalore,Sunrisers Hyderabad,1,5,Mandeep Singh,CH Gayle,A Nehra,0,0,0,0,0,0,4,0,4,,,"
        );

        when(iplDao.matchesData()).thenReturn(matchesData);
        when(iplDao.DeliveriesData()).thenReturn(deliveriesData);
    }

    @Test
    void testPlayerWise() {
        // Example: calculate total runs for "R Ashwin"
        Map<String, Object> result= deliveriesDto.getPlayer("S Dhawan");
        assertEquals(1,result.get("totalRuns"));
        assertEquals(1,result.get("highestRunsInMatch"));
        assertEquals(33.33, (double) result.get("strikeRate"), 0.01);

        Map<String, Object> result2= deliveriesDto.getPlayer("Mandeep Singh");
        assertEquals(6,result2.get("totalRuns"));
        assertEquals(150.00, (double) result2.get("strikeRate"), 0.01);

    }

    @Test
    void testGetSummary() {
        Map<String, Object> result = deliveriesDto.getSummary(1);
        assertEquals("Sunrisers Hyderabad", result.get("winner"));
        Map<String, Object> team = (Map<String, Object>) result.get("team2");
        assertEquals("Royal Challengers Bangalore", team.get("name"));
    }

    @Test
    void testBestEconomical(){
        List<String> result = deliveriesDto.getBestEconomicalBowler(2017);
        assertEquals("TS Mills (7.00)", result.get(0));
    }
}
