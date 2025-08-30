package com.mb.IPL.dtoTest;

import com.mb.IPL.dao.IplDao;
import com.mb.IPL.dto.IplDto;
import com.mb.IPL.exception.IdNotFoundException;
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


public class MatchDtoTest {

    @Mock
    private IplDao iplDao;

    @InjectMocks
    private IplDto iplDto;

    private List<String> mockData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock CSV data (header + rows)
        mockData = Arrays.asList(
                "id,season,city,date,team1,team2,toss_winner,toss_decision,result,win_by_runs,winner",
                "1,2017,Hyderabad,2017-04-05,SRH,RCB,SRH,bat,normal,35,SRH",
                "2,2017,Pune,2017-04-06,MI,RPS,MI,bat,normal,7,MI",
                "3,2017,Rajkot,2017-04-07,GL,KKR,KKR,field,normal,10,KKR",
                "4,2017,Indore,2017-04-08,KKR,MI,MI,bat,normal,15,KKR",
                "5,2017,Bangalore,2017-04-09,RCB,DD,RCB,field,normal,21,RCB",
                "6,2015,Indore,2015-04-10,KKR,MI,MI,bat,normal,15,KKR"
        );

        when(iplDao.matchesData()).thenReturn(mockData);
    }

    @Test
    void testHomeShouldReturnMatchCountAndLastFiveMatches() {
        List<Map<String, Object>> result = iplDto.home();

        assertNotNull(result);
        assertEquals(7, result.size()); // 1 for "Matches" + 6 matches
        assertEquals(6L, result.get(0).get("Matches")); // total matches count
    }

    @Test
    void testGetMatchByIdValid() {
        Map<String, Object> match = iplDto.getMatchById(2);

        assertNotNull(match);
        assertEquals("2", match.get("id"));
        assertEquals("Pune", match.get("city"));
        assertEquals("MI", match.get("team1"));
        assertEquals("RPS", match.get("team2"));
    }

    @Test
    void testGetMatchByIdInvalidShouldThrowException() {
        assertThrows(IdNotFoundException.class, () -> iplDto.getMatchById(99));
    }

    @Test
    void testGetMatchByPlace(){
        List<Map<String, String>> match = iplDto.getPlaced("Indore");
        assertNotNull(match);
        assertEquals(2,match.size());
    }
    @Test
    void testGetMatchYearWise(){
        Map<Integer, Integer> result = iplDto.getMatchYearWise();
        assertEquals(5, result.get(2017));
//        assertEquals(null, result.get(2025));
        assertNull(result.get(2025));
        assertEquals(1, result.get(2015));
    }

    @Test
    void testMatchWinTeamWise(){
        Map<String, Map<Integer, Integer>> result = iplDto.getMatchWonTeamWise();
        System.err.println(result);
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");

        assertTrue(result.containsKey("KKR"), "KKR should be in the result");
        assertTrue(result.containsKey("MI"), "MI should be in the result");


        Map<Integer, Integer> kkrData = result.get("KKR");
        assertNotNull(kkrData, "KKR data should not be null");
        assertTrue(kkrData.containsKey(2017), "KKR should have matches in 2017");
        assertEquals(2, kkrData.get(2017), "KKR should have won 2 matches in 2017");

        Map<Integer, Integer> miData = result.get("MI");
        assertNotNull(miData, "MI data should not be null");
        assertEquals(1, miData.get(2017), "MI should have won 2 matches in 2017");

        assertTrue(result.get("SRH").containsKey(2017));
        assertEquals(1, result.get("SRH").get(2017));
    }
}
