package org.mahjong4j.mahjong;


public class ChurenpohtohResolver implements MahjongResolver {

    final int[] churenManzu = {
            3, 1, 1, 1, 1, 1, 1, 1, 3,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
    };
    final int[] churenPinzu = {
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            3, 1, 1, 1, 1, 1, 1, 1, 3,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
    };

    final int[] churenSohzu = {
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            3, 1, 1, 1, 1, 1, 1, 1, 3,
            0, 0, 0, 0,
            0, 0, 0
    };


    MahjongTileType churenType;
    private int[] hands = new int[34];

    public ChurenpohtohResolver(int[] hands) {
        for (int i = 0; i < hands.length; i++) {
            this.hands[i] = hands[i];
        }
    }

    public int howHan() {
        return 0;
    }

    public boolean isChuren() {

        //どのタイプが含まれているか調べる
        for (int i = 0; i < hands.length; i++) {
            if (hands[i] > 0) {
                churenType = MahjongTile.getTile(i).getType();
                break;
            }
        }

        //タイプ毎に調べる
        int count = 0;
        switch (churenType) {
            case MANZU:
                for (int i = 0; i < hands.length; i++) {
                    hands[i] -= churenManzu[i];

                    if (hands[i] < 0 || hands[i] > 1) {
                        return false;
                    }
                    if (i < 9 && hands[i] == 1) {
                        count++;
                    }

                }

                break;
            case PINZU:
                for (int i = 0; i < hands.length; i++) {
                    hands[i] -= churenPinzu[i];

                    if (hands[i] < 0 || hands[i] > 1) {
                        return false;
                    }
                    if (i > 8 && i < 18 && hands[i] == 1) {
                        count++;
                    }

                }
                break;
            case SOHZU:
                for (int i = 0; i < hands.length; i++) {
                    hands[i] -= churenSohzu[i];

                    if (hands[i] < 0 || hands[i] > 1) {
                        return false;
                    }
                    if (i > 17 && i < 27 && hands[i] == 1) {
                        count++;
                    }

                }

            default:
                return false;
        }
        if (count == 1) {
            return true;
        }
        return false;


    }

}
