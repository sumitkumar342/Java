# IPL Data Analysis Output

## 1. Number of matches played per year of all the years in IPL.

    http://localhost:8080/ipl/totalmatch (get)
    "statusCode": 200,
    "message": "Data founded year wise",
    "data": {
        "2008": 58,
        "2009": 57,
        "2010": 60,
        "2011": 73,
        "2012": 74,
        "2013": 76,
        "2014": 60,
        "2015": 59,
        "2016": 60,
        "2017": 59,
    }

## 2. Number of matches won of all teams over all the years of IPL.

    http://localhost:8080/ipl/matchwonteamwise (get)

    "statusCode": 200,
    "message": "Team wise list founded",
    "data": {
        "Chennai Super Kings": {
            "2008": 9,
            "2009": 8,
            "2010": 9,
            "2011": 11,
            "2012": 10,
            "2013": 12,
            "2014": 10,
            "2015": 10
        },
        "Deccan Chargers": {
            "2008": 2,
            "2009": 9,
            "2010": 8,
            "2011": 6,
            "2012": 4
        },
        "Delhi Daredevils": {
            "2008": 7,
            "2009": 10,
            "2010": 7,
            "2011": 4,
            "2012": 11,
            "2013": 3,
            "2014": 2,
            "2015": 5,
            "2016": 7,
            "2017": 6
        },
        "Gujarat Lions": {
            "2016": 9,
            "2017": 4
        },
        "Kings XI Punjab": {
            "2008": 10,
            "2009": 7,
            "2010": 4,
            "2011": 7,
            "2012": 8,
            "2013": 8,
            "2014": 12,
            "2015": 3,
            "2016": 4,
            "2017": 7
        },
        "Kochi Tuskers Kerala": {
            "2011": 6
        },
        "Kolkata Knight Riders": {
            "2008": 6,
            "2009": 3,
            "2010": 7,
            "2011": 8,
            "2012": 12,
            "2013": 6,
            "2014": 11,
            "2015": 7,
            "2016": 8,
            "2017": 9
        },
        "Mumbai Indians": {
            "2008": 7,
            "2009": 5,
            "2010": 11,
            "2011": 10,
            "2012": 10,
            "2013": 13,
            "2014": 7,
            "2015": 10,
            "2016": 7,
            "2017": 12
        },
        "Pune Warriors": {
            "2011": 4,
            "2012": 4,
            "2013": 4
        },
        "Rajasthan Royals": {
            "2008": 13,
            "2009": 6,
            "2010": 6,
            "2011": 6,
            "2012": 7,
            "2013": 11,
            "2014": 7,
            "2015": 7
        },
        "Rising Pune Supergiant": {
            "2017": 10
        },
        "Rising Pune Supergiants": {
            "2016": 5
        },
        "Royal Challengers Bangalore": {
            "2008": 4,
            "2009": 9,
            "2010": 8,
            "2011": 10,
            "2012": 8,
            "2013": 9,
            "2014": 5,
            "2015": 8,
            "2016": 9,
            "2017": 3
        },
        "Sunrisers Hyderabad": {
            "2013": 10,
            "2014": 6,
            "2015": 7,
            "2016": 11,
            "2017": 8
        }
    }


## 3. For the year 2016 get the extra runs conceded per team.

http://localhost:8080/match-summary/extra-run/2016 (get)

    "statusCode": 200,
    "message": "Data founded",
    "data": {
        "Delhi Daredevils": 106,
        "Gujarat Lions": 98,
        "Kings XI Punjab": 100,
        "Kolkata Knight Riders": 122,
        "Mumbai Indians": 102,
        "Rising Pune Supergiants": 108,
        "Royal Challengers Bangalore": 156,
        "Sunrisers Hyderabad": 107
    }

## 4. For the year 2015 get the top economical bowlers.

    http://localhost:8080/match-summary/besteconomical/2014 (Get)
    "statusCode": 200,
    "message": "Data founded",
    "data": "Ankit Sharma (5.50)"

## 5. Get match details by match id.

    http://localhost:8080/ipl/636 (get)

    {
        "statusCode": 200,
        "message": "Match founded by id",
        "data": {
            "date": "29-05-2016",
            "venue": "M Chinnaswamy Stadium",
            "win_by_wickets": "0",
            "city": "Bangalore",
            "team1": "Sunrisers Hyderabad",
            "team2": "Royal Challengers Bangalore",
            "result": "normal",
            "dl_applied": "0",
            "winner": "Sunrisers Hyderabad",
            "player_of_match": "BCJ Cutting",
            "umpire1": "HDPK Dharmasena",
            "season": "2016",
            "toss_winner": "Sunrisers Hyderabad",
            "id": "636",
            "umpire2": "BNJ Oxenford",
            "toss_decision": "bat",
            "win_by_runs": "8"
        }
    }

## 6. Home page give last 6 match details.

    http://localhost:8080/ipl (get)
    {
        "statusCode": 302,
        "message": "Founded the data",
        "data": [
            {
                "Matches": 636
            },
            {
                "date": "29-05-2016",
                "result": "Sunrisers Hyderabad",
                "toss decision": "bat",
                "city": "Bangalore",
                "team1": "Sunrisers Hyderabad",
                "team2": "Royal Challengers Bangalore",
                "toss win": "Sunrisers Hyderabad",
                "Match id": "636"
            },
            {
                "date": "27-05-2016",
                "result": "Sunrisers Hyderabad",
                "toss decision": "field",
                "city": "Delhi",
                "team1": "Gujarat Lions",
                "team2": "Sunrisers Hyderabad",
                "toss win": "Sunrisers Hyderabad",
                "Match id": "635"
            },
            {
                "date": "25-05-2016",
                "result": "Sunrisers Hyderabad",
                "toss decision": "field",
                "city": "Delhi",
                "team1": "Sunrisers Hyderabad",
                "team2": "Kolkata Knight Riders",
                "toss win": "Kolkata Knight Riders",
                "Match id": "634"
            },
            {
                "date": "24-05-2016",
                "result": "Royal Challengers Bangalore",
                "toss decision": "field",
                "city": "Bangalore",
                "team1": "Gujarat Lions",
                "team2": "Royal Challengers Bangalore",
                "toss win": "Royal Challengers Bangalore",
                "Match id": "633"
            },
            {
                "date": "22-05-2016",
                "result": "Royal Challengers Bangalore",
                "toss decision": "field",
                "city": "Raipur",
                "team1": "Delhi Daredevils",
                "team2": "Royal Challengers Bangalore",
                "toss win": "Royal Challengers Bangalore",
                "Match id": "632"
            },
            {
                "date": "22-05-2016",
                "result": "Kolkata Knight Riders",
                "toss decision": "field",
                "city": "Kolkata",
                "team1": "Kolkata Knight Riders",
                "team2": "Sunrisers Hyderabad",
                "toss win": "Sunrisers Hyderabad",
                "Match id": "631"
            }
        ]
    }

## 7. Get match details by stadium name.

    http://localhost:8080/ipl/place/rajkot (get)

    {
        "statusCode": 200,
        "message": "Data available location wise",
        "data": [
            {
                "date": "07-04-2017",
                "venue": "Saurashtra Cricket Association Stadium",
                "win_by_wickets": "10",
                "city": "Rajkot",
                "team1": "Gujarat Lions",
                "team2": "Kolkata Knight Riders",
                "result": "normal",
                "dl_applied": "0",
                "winner": "Kolkata Knight Riders",
                "player_of_match": "CA Lynn",
                "umpire1": "Nitin Menon",
                "season": "2017",
                "toss_winner": "Kolkata Knight Riders",
                "id": "3",
                "umpire2": "CK Nandan",
                "toss_decision": "field",
                "win_by_runs": "0"
            },
            {
                "date": "14-04-2017",
                "venue": "Saurashtra Cricket Association Stadium",
                "win_by_wickets": "7",
                "city": "Rajkot",
                "team1": "Rising Pune Supergiant",
                "team2": "Gujarat Lions",
                "result": "normal",
                "dl_applied": "0",
                "winner": "Gujarat Lions",
                "player_of_match": "AJ Tye",
                "umpire1": "A Nand Kishore",
                "season": "2017",
                "toss_winner": "Gujarat Lions",
                "id": "13",
                "umpire2": "S Ravi",
                "toss_decision": "field",
                "win_by_runs": "0"
            },
            {
                "date": "18-04-2017",
                "venue": "Saurashtra Cricket Association Stadium",
                "win_by_wickets": "0",
                "city": "Rajkot",
                "team1": "Royal Challengers Bangalore",
                "team2": "Gujarat Lions",
                "result": "normal",
                "dl_applied": "0",
                "winner": "Royal Challengers Bangalore",
                "player_of_match": "CH Gayle",
                "umpire1": "S Ravi",
                "season": "2017",
                "toss_winner": "Gujarat Lions",
                "id": "20",
                "umpire2": "VK Sharma",
                "toss_decision": "field",
                "win_by_runs": "21"
            },
            {
                "date": "23-04-2017",
                "venue": "Saurashtra Cricket Association Stadium",
                "win_by_wickets": "0",
                "city": "Rajkot",
                "team1": "Kings XI Punjab",
                "team2": "Gujarat Lions",
                "result": "normal",
                "dl_applied": "0",
                "winner": "Kings XI Punjab",
                "player_of_match": "HM Amla",
                "umpire1": "AK Chaudhary",
                "season": "2017",
                "toss_winner": "Gujarat Lions",
                "id": "26",
                "umpire2": "M Erasmus",
                "toss_decision": "field",
                "win_by_runs": "26"
            },
            {
                "date": "29-04-2017",
                "venue": "Saurashtra Cricket Association Stadium",
                "win_by_wickets": "0",
                "city": "Rajkot",
                "team1": "Gujarat Lions",
                "team2": "Mumbai Indians",
                "result": "tie",
                "dl_applied": "0",
                "winner": "Mumbai Indians",
                "player_of_match": "KH Pandya",
                "umpire1": "AK Chaudhary",
                "season": "2017",
                "toss_winner": "Gujarat Lions",
                "id": "34",
                "umpire2": "CB Gaffaney",
                "toss_decision": "bat",
                "win_by_runs": "0"
            },
            {
                "date": "14-04-2016",
                "venue": "Saurashtra Cricket Association Stadium",
                "win_by_wickets": "7",
                "city": "Rajkot",
                "team1": "Rising Pune Supergiants",
                "team2": "Gujarat Lions",
                "result": "normal",
                "dl_applied": "0",
                "winner": "Gujarat Lions",
                "player_of_match": "AJ Finch",
                "umpire1": "VA Kulkarni",
                "season": "2016",
                "toss_winner": "Rising Pune Supergiants",
                "id": "582",
                "umpire2": "CK Nandan",
                "toss_decision": "bat",
                "win_by_runs": "0"
            },
            {
                "date": "21-04-2016",
                "venue": "Saurashtra Cricket Association Stadium",
                "win_by_wickets": "10",
                "city": "Rajkot",
                "team1": "Gujarat Lions",
                "team2": "Sunrisers Hyderabad",
                "result": "normal",
                "dl_applied": "0",
                "winner": "Sunrisers Hyderabad",
                "player_of_match": "B Kumar",
                "umpire1": "K Bharatan",
                "season": "2016",
                "toss_winner": "Sunrisers Hyderabad",
                "id": "591",
                "umpire2": "HDPK Dharmasena",
                "toss_decision": "field",
                "win_by_runs": "0"
            },
            {
                "date": "24-04-2016",
                "venue": "Saurashtra Cricket Association Stadium",
                "win_by_wickets": "6",
                "city": "Rajkot",
                "team1": "Royal Challengers Bangalore",
                "team2": "Gujarat Lions",
                "result": "normal",
                "dl_applied": "0",
                "winner": "Gujarat Lions",
                "player_of_match": "V Kohli",
                "umpire1": "K Bharatan",
                "season": "2016",
                "toss_winner": "Royal Challengers Bangalore",
                "id": "595",
                "umpire2": "BNJ Oxenford",
                "toss_decision": "bat",
                "win_by_runs": "0"
            },
            {
                "date": "01-05-2016",
                "venue": "Saurashtra Cricket Association Stadium",
                "win_by_wickets": "0",
                "city": "Rajkot",
                "team1": "Kings XI Punjab",
                "team2": "Gujarat Lions",
                "result": "normal",
                "dl_applied": "0",
                "winner": "Kings XI Punjab",
                "player_of_match": "AR Patel",
                "umpire1": "BNJ Oxenford",
                "season": "2016",
                "toss_winner": "Gujarat Lions",
                "id": "604",
                "umpire2": "VK Sharma",
                "toss_decision": "field",
                "win_by_runs": "23"
            },
            {
                "date": "03-05-2016",
                "venue": "Saurashtra Cricket Association Stadium",
                "win_by_wickets": "8",
                "city": "Rajkot",
                "team1": "Gujarat Lions",
                "team2": "Delhi Daredevils",
                "result": "normal",
                "dl_applied": "0",
                "winner": "Delhi Daredevils",
                "player_of_match": "RR Pant",
                "umpire1": "CB Gaffaney",
                "season": "2016",
                "toss_winner": "Delhi Daredevils",
                "id": "607",
                "umpire2": "BNJ Oxenford",
                "toss_decision": "field",
                "win_by_runs": "0"
            }
        ]
    }

## 8. Match-winning teams by year.

    http://localhost:8080/ipl/matchwonteamwise (get)
    {
        "statusCode": 200,
        "message": "Team wise list founded",
        "data": {
            "Chennai Super Kings": {
                "2008": 9,
                "2009": 8,
                "2010": 9,
                "2011": 11,
                "2012": 10,
                "2013": 12,
                "2014": 10,
                "2015": 10
            },
            "Deccan Chargers": {
                "2008": 2,
                "2009": 9,
                "2010": 8,
                "2011": 6,
                "2012": 4
            },
            "Delhi Daredevils": {
                "2008": 7,
                "2009": 10,
                "2010": 7,
                "2011": 4,
                "2012": 11,
                "2013": 3,
                "2014": 2,
                "2015": 5,
                "2016": 7,
                "2017": 6
            },
            "Gujarat Lions": {
                "2016": 9,
                "2017": 4
            },
            "Kings XI Punjab": {
                "2008": 10,
                "2009": 7,
                "2010": 4,
                "2011": 7,
                "2012": 8,
                "2013": 8,
                "2014": 12,
                "2015": 3,
                "2016": 4,
                "2017": 7
            },
            "Kochi Tuskers Kerala": {
                "2011": 6
            },
            "Kolkata Knight Riders": {
                "2008": 6,
                "2009": 3,
                "2010": 7,
                "2011": 8,
                "2012": 12,
                "2013": 6,
                "2014": 11,
                "2015": 7,
                "2016": 8,
                "2017": 9
            },
            "Mumbai Indians": {
                "2008": 7,
                "2009": 5,
                "2010": 11,
                "2011": 10,
                "2012": 10,
                "2013": 13,
                "2014": 7,
                "2015": 10,
                "2016": 7,
                "2017": 12
            },
            "Pune Warriors": {
                "2011": 4,
                "2012": 4,
                "2013": 4
            },
            "Rajasthan Royals": {
                "2008": 13,
                "2009": 6,
                "2010": 6,
                "2011": 6,
                "2012": 7,
                "2013": 11,
                "2014": 7,
                "2015": 7
            },
            "Rising Pune Supergiant": {
                "2017": 10
            },
            "Rising Pune Supergiants": {
                "2016": 5
            },
            "Royal Challengers Bangalore": {
                "2008": 4,
                "2009": 9,
                "2010": 8,
                "2011": 10,
                "2012": 8,
                "2013": 9,
                "2014": 5,
                "2015": 8,
                "2016": 9,
                "2017": 3
            },
            "Sunrisers Hyderabad": {
                "2013": 10,
                "2014": 6,
                "2015": 7,
                "2016": 11,
                "2017": 8
            }
        }
    }

## 9. Year-wise team names.

    http://localhost:8080/ipl/teams (get)
    {
        "statusCode": 200,
        "message": "Data founded year wise.",
        "data": {
            "2016": [
                "Gujarat Lions",
                "Mumbai Indians",
                "Sunrisers Hyderabad",
                "Kings XI Punjab",
                "Delhi Daredevils",
                "Rising Pune Supergiants",
                "Kolkata Knight Riders",
                "Royal Challengers Bangalore"
            ],
            "2017": [
                "Gujarat Lions",
                "Mumbai Indians",
                "Rising Pune Supergiant",
                "Sunrisers Hyderabad",
                "Kings XI Punjab",
                "Delhi Daredevils",
                "Royal Challengers Bangalore",
                "Kolkata Knight Riders"
            ],
            "2008": [
                "Mumbai Indians",
                "Kings XI Punjab",
                "Deccan Chargers",
                "Rajasthan Royals",
                "Delhi Daredevils",
                "Kolkata Knight Riders",
                "Royal Challengers Bangalore",
                "Chennai Super Kings"
            ],
            "2009": [
                "Mumbai Indians",
                "Kings XI Punjab",
                "Deccan Chargers",
                "Rajasthan Royals",
                "Delhi Daredevils",
                "Royal Challengers Bangalore",
                "Kolkata Knight Riders",
                "Chennai Super Kings"
            ],
            "2010": [
                "Mumbai Indians",
                "Kings XI Punjab",
                "Deccan Chargers",
                "Rajasthan Royals",
                "Delhi Daredevils",
                "Kolkata Knight Riders",
                "Royal Challengers Bangalore",
                "Chennai Super Kings"
            ],
            "2011": [
                "Mumbai Indians",
                "Kochi Tuskers Kerala",
                "Pune Warriors",
                "Kings XI Punjab",
                "Deccan Chargers",
                "Rajasthan Royals",
                "Delhi Daredevils",
                "Kolkata Knight Riders",
                "Royal Challengers Bangalore",
                "Chennai Super Kings"
            ],
            "2012": [
                "Mumbai Indians",
                "Pune Warriors",
                "Kings XI Punjab",
                "Deccan Chargers",
                "Delhi Daredevils",
                "Rajasthan Royals",
                "Kolkata Knight Riders",
                "Royal Challengers Bangalore",
                "Chennai Super Kings"
            ],
            "2013": [
                "Mumbai Indians",
                "Sunrisers Hyderabad",
                "Pune Warriors",
                "Kings XI Punjab",
                "Delhi Daredevils",
                "Rajasthan Royals",
                "Kolkata Knight Riders",
                "Royal Challengers Bangalore",
                "Chennai Super Kings"
            ],
            "2014": [
                "Mumbai Indians",
                "Sunrisers Hyderabad",
                "Kings XI Punjab",
                "Delhi Daredevils",
                "Rajasthan Royals",
                "Kolkata Knight Riders",
                "Royal Challengers Bangalore",
                "Chennai Super Kings"
            ],
            "2015": [
                "Mumbai Indians",
                "Sunrisers Hyderabad",
                "Kings XI Punjab",
                "Delhi Daredevils",
                "Rajasthan Royals",
                "Kolkata Knight Riders",
                "Royal Challengers Bangalore",
                "Chennai Super Kings"
            ]
        }
    }


## 10. Player profiles.

    http://localhost:8080/match-summary/player/S Dhawan (get)
    {
        "statusCode": 200,
        "message": "Data founded by name",
        "data": {
            "player": "S Dhawan",
            "Match": 126,
            "totalRuns": 3561,
            "highestRunsInMatch": 95,
            "strikeRate": 118.50249584026622,
            "economy": 9.0,
            "totalWickets": 4,
            "bestBowling": 1
        }
    }

## 11. Extra runs conceded by teams year-wise.

    http://localhost:8080/match-summary/extra-run/2016 (get)
    {
        "statusCode": 200,
        "message": "Data founded",
        "data": {
            "Delhi Daredevils": 106,
            "Gujarat Lions": 98,
            "Kings XI Punjab": 100,
            "Kolkata Knight Riders": 122,
            "Mumbai Indians": 102,
            "Rising Pune Supergiants": 108,
            "Royal Challengers Bangalore": 156,
            "Sunrisers Hyderabad": 107
        }
    }

## 12. Best Economical Bowler – 2014

    http://localhost:8080/match-summary/besteconomical/2014 (get)
    {
        "statusCode": 200,
        "message": "Data founded",
        "data": "Ankit Sharma (5.50)"
    }

