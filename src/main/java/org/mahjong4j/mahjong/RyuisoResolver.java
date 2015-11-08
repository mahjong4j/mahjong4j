package org.mahjong4j.mahjong;

public class RyuisoResolver implements MahjongResolver {

    final int[] ryuisoHhands = {
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1, 1, 1, 0, 1, 0, 1, 0,
            0, 0, 0, 0,
            0, 1, 0
    };

    int[] hands;

    public int howHan() {
        return 0;
    }

    public boolean isRyuiso(int[] hands) {
        for (int i = 0; i < hands.length; i++) {
            if (ryuisoHhands[i] == 0 && hands[i] > 0) {
                return false;
            } else if (ryuisoHhands[i] == 1 && hands[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
