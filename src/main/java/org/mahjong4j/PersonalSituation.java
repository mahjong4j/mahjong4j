package org.mahjong4j;

import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 */
public class PersonalSituation {
    private boolean isDealer;
    private boolean isTsumo;
    private boolean isIppatsu;
    private boolean hasReached;
    private boolean isDoubleReach;
    private boolean isChankan;
    private MahjongTile jikaze;

    public PersonalSituation(boolean isDealer, boolean isTsumo, boolean isIppatsu, boolean hasReached, boolean isDoubleReach, boolean isChankan, MahjongTile jikaze) {
        this.isDealer = isDealer;
        this.isTsumo = isTsumo;
        this.isIppatsu = isIppatsu;
        this.hasReached = hasReached;
        this.isDoubleReach = isDoubleReach;
        this.isChankan = isChankan;
        this.jikaze = jikaze;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void setDealer(boolean dealer) {
        isDealer = dealer;
    }

    public boolean isTsumo() {
        return isTsumo;
    }

    public void setTsumo(boolean tsumo) {
        isTsumo = tsumo;
    }

    public boolean isIppatsu() {
        return isIppatsu;
    }

    public void setIppatsu(boolean ippatsu) {
        isIppatsu = ippatsu;
    }

    public boolean isHasReached() {
        return hasReached;
    }

    public void setHasReached(boolean hasReached) {
        this.hasReached = hasReached;
    }

    public boolean isDoubleReach() {
        return isDoubleReach;
    }

    public void setDoubleReach(boolean doubleReach) {
        isDoubleReach = doubleReach;
    }

    public boolean isChankan() {
        return isChankan;
    }

    public void setChankan(boolean chankan) {
        isChankan = chankan;
    }

    public MahjongTile getJikaze() {
        return jikaze;
    }

    public void setJikaze(MahjongTile jikaze) {
        this.jikaze = jikaze;
    }
}
