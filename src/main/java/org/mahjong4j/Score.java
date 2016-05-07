package org.mahjong4j;

/**
 * @author yu1ro
 */
public enum Score {
    SCORE0(0, 0, 0, 0),
    SCORE1000(1000, 0, 500, 300),
    SCORE1300(1300, 0, 700, 400),
    SCORE1500(1500, 500, 0, 0),
    SCORE1600(1600, 0, 800, 400),
    SCORE2000(2000, 700, 1000, 500),
    SCORE2300(2300, 0, 1200, 600),
    SCORE2400(2400, 800, 0, 0),
    SCORE2600(2600, 0, 1300, 700),
    SCORE2900(2900, 1000, 1500, 800),
    SCORE3200(3200, 0, 1600, 800),
    SCORE3400(3400, 1200, 0, 0),
    SCORE3600(3600, 0, 1800, 900),
    SCORE3900(3900, 1300, 2000, 1000),
    SCORE4400(4400, 1500, 0, 0),
    SCORE4500(4500, 0, 2300, 1200),
    SCORE4800(4800, 1600, 0, 0),
    SCORE5200(5200, 0, 2600, 1300),
    SCORE5300(5300, 1800, 0, 0),
    SCORE5800(5800, 2000, 2900, 1500),
    SCORE6400(6400, 0, 3200, 1600),
    SCORE6800(6800, 2300, 0, 0),
    SCORE7100(7100, 0, 3600, 1800),
    SCORE7700(7700, 2600, 3900, 2000),
    SCORE8000(8000, 0, 4000, 2000),
    SCORE8700(8700, 2900, 0, 0),
    SCORE9600(9600, 3200, 0, 0),
    SCORE10600(10600, 3600, 0, 0),
    SCORE11600(11600, 3900, 0, 0),
    SCORE12000(12000, 4000, 6000, 3000),
    SCORE16000(16000, 0, 8000, 4000),
    SCORE18000(18000, 6000, 0, 0),
    SCORE24000(24000, 8000, 12000, 6000),
    SCORE32000(32000, 0, 16000, 8000),
    SCORE36000(36000, 12000, 0, 0),
    SCORE48000(48000, 16000, 0, 0),;
    private final int ron;
    private final int parentTsumo;
    private final int parent;
    private final int child;

    Score(int ron, int parentTsumo, int parent, int child) {
        this.ron = ron;
        this.parentTsumo = parentTsumo;
        this.parent = parent;
        this.child = child;
    }

    public static Score calculateYakumanScore(boolean isParent, int yakumanSize) {
        switch (yakumanSize) {
            case 1:
                return isParent ? SCORE48000 : SCORE32000;
            // TODO: ダブル役満, トリプル役満, etc...
        }
        return SCORE0;
    }

    public static Score calculateScore(boolean isParent, int han, int fu) {
        if (han >= 13) {
            return isParent ? SCORE48000 : SCORE32000;
        }
        switch (han) {
            case 12:
            case 11:
                return isParent ? SCORE36000 : SCORE24000;
            case 10:
            case 9:
            case 8:
                return isParent ? SCORE24000 : SCORE16000;
            case 7:
            case 6:
                return isParent ? SCORE18000 : SCORE12000;
            case 5:
                return isParent ? SCORE12000 : SCORE8000;
            case 4:
                return calc4Han(isParent, fu);
            case 3:
                return calc3Han(isParent, fu);
            case 2:
                return calc2Han(isParent, fu);
            case 1:
                return calc1Han(isParent, fu);
        }
        return SCORE0;
    }

    private static Score calc4Han(boolean isParent, int fu) {
        if (fu == 25) return isParent ? SCORE9600 : SCORE6400;
        if (fu > 30) return isParent ? SCORE12000 : SCORE8000;
        if (fu > 20) return isParent ? SCORE11600 : SCORE7700;
        return isParent ? SCORE7700 : SCORE5200;
    }

    private static Score calc3Han(boolean isParent, int fu) {
        if (fu == 25) return isParent ? SCORE4800 : SCORE3200;
        if (fu > 60) return isParent ? SCORE12000 : SCORE8000;
        if (fu > 50) return isParent ? SCORE11600 : SCORE7700;
        if (fu > 40) return isParent ? SCORE9600 : SCORE6400;
        if (fu > 30) return isParent ? SCORE7700 : SCORE5200;
        if (fu > 20) return isParent ? SCORE5800 : SCORE3900;
        return isParent ? SCORE3900 : SCORE2600;
    }

    private static Score calc2Han(boolean isParent, int fu) {
        if (fu == 25) return isParent ? SCORE2400 : SCORE1600;
        if (fu > 100) return isParent ? SCORE10600 : SCORE7100;
        if (fu > 90) return isParent ? SCORE9600 : SCORE6400;
        if (fu > 80) return isParent ? SCORE8700 : SCORE5800;
        if (fu > 70) return isParent ? SCORE7700 : SCORE5200;
        if (fu > 60) return isParent ? SCORE6800 : SCORE4500;
        if (fu > 50) return isParent ? SCORE5800 : SCORE3900;
        if (fu > 40) return isParent ? SCORE4800 : SCORE3200;
        if (fu > 30) return isParent ? SCORE3900 : SCORE2600;
        if (fu > 20) return isParent ? SCORE2900 : SCORE2000;
        return isParent ? SCORE2000 : SCORE1300;
    }

    private static Score calc1Han(boolean isParent, int fu) {
        if (fu > 100) return isParent ? SCORE5300 : SCORE3600;
        if (fu > 90) return isParent ? SCORE4800 : SCORE3200;
        if (fu > 80) return isParent ? SCORE4400 : SCORE2900;
        if (fu > 70) return isParent ? SCORE3900 : SCORE2600;
        if (fu > 60) return isParent ? SCORE3400 : SCORE2300;
        if (fu > 50) return isParent ? SCORE2900 : SCORE2000;
        if (fu > 40) return isParent ? SCORE2400 : SCORE1600;
        if (fu > 30) return isParent ? SCORE2000 : SCORE1300;
        return isParent ? SCORE1500 : SCORE1000;
    }

    public int getRon() {
        return ron;
    }

    public int getParentTsumo() {
        return parentTsumo;
    }

    public int getParent() {
        return parent;
    }

    public int getChild() {
        return child;
    }
}
