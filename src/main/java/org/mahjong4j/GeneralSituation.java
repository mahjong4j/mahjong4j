package org.mahjong4j;

import org.mahjong4j.tile.MahjongTile;

import java.util.List;

/**
 * @author yu1ro
 */
public class GeneralSituation {
    private boolean isFirstRound;
    private boolean isHoutei;
    private MahjongTile jikaze;
    private List<MahjongTile> dora;
    private List<MahjongTile> uradora;

    public GeneralSituation(boolean isFirstRound, boolean isHoutei, MahjongTile jikaze, List<MahjongTile> dora, List<MahjongTile> uradora) {
        this.isFirstRound = isFirstRound;
        this.isHoutei = isHoutei;
        this.jikaze = jikaze;
        this.dora = dora;
        this.uradora = uradora;
    }

    public boolean isFirstRound() {

        return isFirstRound;
    }

    public void setFirstRound(boolean firstRound) {
        isFirstRound = firstRound;
    }

    public boolean isHoutei() {
        return isHoutei;
    }

    public void setHoutei(boolean houtei) {
        isHoutei = houtei;
    }

    public MahjongTile getJikaze() {
        return jikaze;
    }

    public void setJikaze(MahjongTile jikaze) {
        this.jikaze = jikaze;
    }

    public List<MahjongTile> getDora() {
        return dora;
    }

    public void setDora(List<MahjongTile> dora) {
        this.dora = dora;
    }

    public List<MahjongTile> getUradora() {
        return uradora;
    }

    public void setUradora(List<MahjongTile> uradora) {
        this.uradora = uradora;
    }
}
