package geometrydash;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class GeometryDash {
    /**
     * Returns whether the given level can be completed using the given play.
     * @param level is not null and not empty
     * @param play is not null and not empty
     * @return true if the play completes the level and false otherwise
     */
    public static boolean isSuccessfulPlay(String level, String play) {
        //Turn level into string
        ArrayList<String> levelList = new ArrayList<String>();
        ArrayList<Integer> playList = new ArrayList<Integer>();
        //Convert level to list
        for(int i = 0; i < level.length(); i++) {
            levelList.add(level.substring(i, i+1));
        }
        //Convert play to list
        for(int i = 0; i < play.length(); i++) {
            playList.add(Integer.parseInt(play.substring(i, i+1)));
        }

        //Check if first square is a spike
        if(!levelList.get(0).equals("_")) return false;
        int currentPosition = 0;

        for(int i = 0; i < playList.size(); i++) {
            if(currentPosition == level.length() - 1) break;
            currentPosition += playList.get(i);
            String nextPlacement = levelList.get(currentPosition);
            if(currentPosition == (level.length() - 1) && i == (playList.size() - 1)) return true;
            if(nextPlacement.equals("^")) return false;
        }
        return false;
    }

    /**
     * Returns the subset of plays which can complete the given level ending
     * with the target resting energy
     * @param level is not null and not empty
     * @param possiblePlays is not null
     * @param startingEnergy the energy at the start of the level
     * @param targetRestingEnergy the minimum energy to end the level at
     * @return a subset of {@code possiblePlays} which complete the level with
     * {@code targetRestingEnergy} units of energy remaining
     */
    public static Set<String> successfulPlays(String level, Set<String> possiblePlays,
                                              int startingEnergy, int targetRestingEnergy) {
        Set<String> successfulPlays = new HashSet<>();
        ArrayList<String> levelList = new ArrayList<String>();
        for(int i = 0; i < level.length(); i++) {
            levelList.add(level.substring(i, i+1));
        }

        //Go through each item in Set
        for(String play : possiblePlays) {
            int currentEnergy = startingEnergy;
            //Convert play to list
            ArrayList<Integer> playList = new ArrayList<Integer>();
            for(int i = 0; i < play.length(); i++) {
                playList.add(Integer.parseInt(play.substring(i, i+1)));
            }

            //Check if first square is a spike
            if(!levelList.get(0).equals("_")) continue;
            int currentPosition = 0;

            for(int i = 0; i < playList.size(); i++) {
                if(currentPosition == level.length() - 1) break;
                int currentMove = playList.get(i);
                currentPosition += currentMove; //Move

                //Adjust energy
                if(currentMove == 0 && currentPosition != (level.length() - 1)) currentEnergy++;
                currentEnergy -= currentMove;

                String nextPlacement = levelList.get(currentPosition); //Gets the spot it lands on

                if(nextPlacement.equals("^")) break;
                if(nextPlacement.equals("*")) {
                    currentPosition += 4;
                    if(currentPosition > level.length()) break; //Unsuccessful if jumps out of the level
                }
                if(currentPosition == (level.length() - 1) && currentEnergy >= targetRestingEnergy && i == (playList.size() - 1)) {
                    successfulPlays.add(play);
                    break;
                }
            }

        }
        return successfulPlays;
    }

    /**
     * Returns the shortest play that completes the given level
     * @param level is not null and not empty
     * @param startingEnergy the energy at the start of the level
     * @param targetRestingEnergy the minimum energy to end the level at
     * @return the shortest play that allows a player to complete the given level
     * @throws UnplayableLevelException if no play can complete the level
     */
    public static String shortestPlay(String level, int startingEnergy, int targetRestingEnergy)
            throws UnplayableLevelException {
        // TODO: Implement this method
        return null;
    }

    /**
     * Returns the total number of plays which allow a player to complete the given level
     * @param level is not null and not empty
     * @param startingEnergy the energy at the start of the level
     * @param targetRestingEnergy the minimum energy to end the level at
     * @return the total number of plays which allow a player to complete the given level
     * with target resting energy {@code targetRestingEnergy}
     */
    public static int numberOfPlays(String level, int startingEnergy, int targetRestingEnergy) {
        // TODO: Implement this method
        return -1;
    }
}
