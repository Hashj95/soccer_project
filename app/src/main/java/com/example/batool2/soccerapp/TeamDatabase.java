package com.example.batool2.soccerapp;

public class TeamDatabase {

    private String team_name, manager_name, year_established;

    public TeamDatabase(String name, String established, String managerName)
    {
        team_name = name;
        year_established = established;
        manager_name = managerName;
    }
    public String getTeamName()
    {

        return team_name;
    }
    public String getYearEstablished()
    {

        return year_established;
    }
    public String getManagerName()
    {

        return manager_name;
    }
}
