package org.mahjong4j;

import org.mahjong4j.tile.Tile;

/**
 * @author yu1ro
 */
public class PersonalSituation {
    private boolean isParent;
    private boolean isTsumo;
    private boolean isIppatsu;
    private boolean isReach;
    private boolean isDoubleReach;
    private boolean isChankan;
    private boolean isRinshankaihoh;
    private Tile jikaze;

    public PersonalSituation(boolean isParent, boolean isTsumo, boolean isIppatsu, boolean isReach, boolean isDoubleReach, boolean isChankan, boolean isRinshankaihoh, Tile jikaze) {
        this.isParent = isParent;
        this.isTsumo = isTsumo;
        this.isIppatsu = isIppatsu;
        this.isReach = isReach;
        this.isDoubleReach = isDoubleReach;
        this.isChankan = isChankan;
        this.isRinshankaihoh = isRinshankaihoh;
        this.jikaze = jikaze;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
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

    public boolean isReach() {
        return isReach;
    }

    public void setReach(boolean reach) {
        this.isReach = reach;
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

    public boolean isRinshankaihoh() {
        return isRinshankaihoh;
    }

    public void setRinshankaihoh(boolean rinshankaihoh) {
        isRinshankaihoh = rinshankaihoh;
    }

    public Tile getJikaze() {
        return jikaze;
    }

    public void setJikaze(Tile jikaze) {
        this.jikaze = jikaze;
    }
}
