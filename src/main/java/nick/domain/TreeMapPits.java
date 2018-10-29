package nick.domain;

import nick.enums.EPlayer;
import nick.exceptions.BadPitIDException;

import java.util.*;

/**
 * Created by Nikola on 9/16/2018.
 */
public class TreeMapPits extends TreeMap<Integer, APit> {

    final int DEFAULT_PIT_STONE_SIZE = 6;

    final static int NUMBER_OF_PITS = 14;
    final int PLAYER1_PIT_POSITION = 7;
    final int PLAYER2_PIT_POSITION = 14;


    PlayerPit firstPlayerPit;
    PlayerPit secondPlayerPit;

    public TreeMapPits() {
        initPits(NUMBER_OF_PITS);
    }

    /**
     * init all pits
     *
     * @param initialPitSize initial number of stones per pit
     */
    private void initPits(int initialPitSize) {
        for (int i = 1; i <= initialPitSize; i++) {
            if (i == PLAYER1_PIT_POSITION) {
                firstPlayerPit = new PlayerPit(i, EPlayer.PLAYER1);
                this.put(i, firstPlayerPit);
            } else if (i == PLAYER2_PIT_POSITION) {
                secondPlayerPit = new PlayerPit(i, EPlayer.PLAYER2);
                this.put(i, secondPlayerPit);
            } else {
                // add player ids to pits
                if (i <= PLAYER1_PIT_POSITION) {
                    this.put(i, new NormalPit(DEFAULT_PIT_STONE_SIZE, i, EPlayer.PLAYER1));
                } else {
                    this.put(i, new NormalPit(DEFAULT_PIT_STONE_SIZE, i, EPlayer.PLAYER2));
                }
            }
        }
        connectParallelPits();
    }

    /**
     * We will need to keep references to pits which are 'parallel'
     * each parallel pit has it's position by pattern (1->13, 2->12, 3->11)
     */
    private void connectParallelPits() {
        // iterator 1 starts from beginning
        Iterator iterator1 = entrySet().iterator();

        NavigableMap<Integer, APit> descendingMap = descendingMap();
        Iterator iterator2 = descendingMap.entrySet().iterator();
        // iterator 2 starts with position 13, so we move it for 1 place
        iterator2.next();

        int counter = 0;
        //we know that there is 6 parallel pits available
        while (counter < 6) {
            //pointing at each oder
            NormalPit selectedPit1 = (NormalPit) ((Map.Entry) iterator1.next()).getValue();
            NormalPit selectedPit2 = (NormalPit) ((Map.Entry) iterator2.next()).getValue();
            selectedPit1.setParalelPit(selectedPit2);
            selectedPit2.setParalelPit(selectedPit1);
            counter++;
        }

    }

    /**
     * Moving stones is done here
     *
     * @param pitID    starting pit
     * @param playerID player id:1 or id:2
     */
    public void playStones(int pitID, EPlayer playerID) {
        if (pitID == PLAYER1_PIT_POSITION || pitID == PLAYER2_PIT_POSITION) {
            throw new BadPitIDException("Bad pit id: " + pitID);
        }
        SortedMap<Integer, APit> map = tailMap(pitID);
        Iterator it = map.entrySet().iterator();
        APit startingPit = map.get(map.firstKey());
        int stonesAmount = startingPit.getNumberOfStones();
        //remove all stones from selected pit
        startingPit.cleanStones();
        int counter = 1;
        // skip starting element
        it.next();
        while (counter <= stonesAmount) {
            APit pit;
            if (it.hasNext()) {
                pit = (APit) ((Map.Entry) it.next()).getValue();
            } else {
                //start from beginning as it is last element
                it = entrySet().iterator();
                pit = (APit) ((Map.Entry) it.next()).getValue();
            }
            if (pit.isAllowedToAddStones(playerID)) {
                // check if empty and move all to player 'house'
                boolean lastStone = stonesAmount - counter == 0;
                if (lastStone && pit.isEmpty() && pit.getPlayerID() == playerID && pit instanceof NormalPit) {
                    pit.incrementStones();
                    moveAllToHouse(playerID, (NormalPit) pit);
                } else {
                    pit.incrementStones();
                }


                counter++;
            }
        }

    }

    /**
     * Check if game is ended
     *
     * @return true if game is ended
     */
    public boolean isGameEnded() {
        Iterator it1 = entrySet().iterator();
        int counter = 1;

        NavigableMap<Integer, APit> descendingMap = descendingMap();
        Iterator it2 = descendingMap.entrySet().iterator();
        it2.next();
        boolean stonesAvailable = false;
        boolean stones2Available = false;
        while (counter <= 6) {
            NormalPit selectedPit1 = (NormalPit) ((Map.Entry) it1.next()).getValue();
            NormalPit selectedPit2 = (NormalPit) ((Map.Entry) it2.next()).getValue();
            if (!selectedPit1.isEmpty()) {
                stonesAvailable = true;
            }
            if (!selectedPit2.isEmpty()) {
                stones2Available = true;
            }
            counter++;
        }
        return !stonesAvailable || !stones2Available;
    }

    /**
     * Moving of parallel pits when last pit is empty is done here
     *
     * @param player where to move all stones
     * @param pit    last pit
     */
    private void moveAllToHouse(EPlayer player, NormalPit pit) {
        if (player == EPlayer.PLAYER1) {
            firstPlayerPit.addToPit(pit.getNumberOfStones() + pit.getParalelPit().getNumberOfStones());
        } else {
            secondPlayerPit.addToPit(pit.getNumberOfStones() + pit.getParalelPit().getNumberOfStones());
        }
        pit.cleanStones();
        pit.getParalelPit().cleanStones();
    }

    public PlayerPit getFirstPlayerPit() {
        return firstPlayerPit;
    }

    public PlayerPit getSecondPlayerPit() {
        return secondPlayerPit;
    }

}
