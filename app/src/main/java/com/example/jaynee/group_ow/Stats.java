package com.example.jaynee.group_ow;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

/**
 * Stats class stores each statistical information from the JSON file retrieved from the API.
 */
public class Stats
{
//    @SerializedName("SoloKills")
//    int soloKills;

    private String MeleeFinalBlows;
    private String SoloKills;
    private String ObjectiveKills;
    private String FinalBlows;
    private String DamageDone;
    private String Eliminations;
    private String EnvironmentalKills;
    private String Multikills;
    private String HealingDone;
    private String TeleporterPadsDestroyed;

//    @SerializedName("Eliminations-MostinGame")
//    String EliminationsMostinGame;
//
//    @SerializedName("FinalBlows-MostinGame")
//    String FinalBlowsMostinGame;
//
//    @SerializedName("DamageDone-MostinGame")
//    String DamageDoneMostinGame;
//
//    @SerializedName("HealingDone-MostinGame")
//    String HealingDoneMostinGame;
//
//    @SerializedName("DefensiveAssists-MostinGame")
//    String DefensiveAssistsMostinGame;
//
//    @SerializedName("OffensiveAssists-MostinGame")
//    String OffensiveAssistsMostinGame;
//
//    @SerializedName("ObjectiveKills-MostinGame")
//    String ObjectiveKillsMostinGame;
//
//    @SerializedName("ObjectiveTime-MostinGame")
//    String ObjectiveTimeMostinGame;
//
//    @SerializedName("Multikill-Best")
//    String MultikillBest;
//
//    @SerializedName("SoloKills-MostinGame")
//    String SoloKillsMostinGame;
//
//    @SerializedName("TimeSpentonFire-MostinGame")
//    String TimeSpentonFireMostinGame;
//
//    @SerializedName("MeleeFinalBlows-Average")
//    String MeleeFinalBlowsAverage;
//
//    @SerializedName("TimeSpentonFire-Average")
//    String TimeSpentonFireAverage;
//
//    @SerializedName("SoloKills-Average")
//    String SoloKillsAverage;
//
//    @SerializedName("ObjectiveTime-Average")
//    String ObjectiveTimeAverage;
//
//    @SerializedName("ObjectiveKills-Average")
//    String ObjectiveKillsAverage;
//
//    @SerializedName("HealingDone-Average")
//    String HealingDoneAverage;
//
//    @SerializedName("FinalBlows-Average")
//    String FinalBlowsAverage;
//
//    @SerializedName("Deaths-Average")
//    String DeathsAverage;
//
//    @SerializedName("DamageDone-Average")
//    String DamageDoneAverage;
//
//    @SerializedName("Deaths-Average")
//    String DeathsAverage;
//
//    @SerializedName("Eliminations-Average")
//    String EliminationsAverage;

    private String Deaths;
    private String EnvironmentalDeaths;
    private String Cards;
    private String Medals;

    @SerializedName("Medals-Gold")
    private String MedalsGold;

    @SerializedName("Medals-Silver")
    private String MedalsSilver;

    @SerializedName("Medals-Bronze")
    private String MedalsBronze;

    private String GamesPlayed;
    private String GamesWon;

//    @SerializedName("MeleeFinalBlows-MostinGame")
//    String MeleeFinalBlowsMostinGame;

    private String GamesTied;
    private String GamesLost;
    private String DefensiveAssists;

//    @SerializedName("DefensiveAssists-Average")
//    String DefensiveAssistsAverage;

    String OffensiveAssists;

//    @SerializedName("OffensiveAssists-Average")
//    String OffensiveAssistsAverage;

    String winRate;

    /**
     * Returns an array of all the stats properties.
     *
     * @return A String array of the stats properties
     */
    public String[] getStatsProperties()
    {
        final String properties[] =  new String[] {"Melee Final Blows: ", "Solo Kills: ",
                "Objective Kills: ", "Final Blows: ", "Damage Done: ", "Eliminations: ",
                "Environmental Kills: ", "Multi Kills: ", "Healing Done: ",
                "Teleporter Pads Destroyed: ",  "Deaths: ", "Environmental Deaths: ", "Cards: ",
                "Medals: ", "Gold Medals: ",  "Silver Medals: ", "Bronze Medals: ",
                "Games Played: ",  "Games Won: ", "Games Tied: ", "Games Lost: ",
                "Defensive Assists: ", "Offensive Assists: ", "Win Rate: "};

        return properties;
    }

    /**
     * Returns an array of the the class variables' value.
     *
     * @return A String array of the class variables' value
     */
    public String[] getStatsValues()
    {
        final String values[] = new String[] {MeleeFinalBlows, SoloKills, ObjectiveKills, FinalBlows,
                DamageDone, Eliminations, EnvironmentalKills, Multikills, HealingDone,
                TeleporterPadsDestroyed, Deaths, EnvironmentalDeaths, Cards, Medals, MedalsGold,
                MedalsSilver, MedalsBronze, GamesPlayed, GamesWon, GamesTied, GamesLost,
                DefensiveAssists, OffensiveAssists, getWinRate()};


        return values;
    }

    public boolean statsAvailable()
    {
        if (GamesPlayed == null)
        {
            return false;
        }
        return true;
    }

    /**
     * Calculates the winning rate of the player.
     *
     * @return String value of the rate
     */
    public String getWinRate()
    {
        final double winPercent = (Double.parseDouble(GamesWon) / Double.parseDouble(GamesPlayed))
                * 100;

        winRate = String.format("%.2f",winPercent) + "%";
        return winRate;
    }
}
