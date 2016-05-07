package org.mahjong4j.yaku.normals;

import org.mahjong4j.tile.MahjongTileType;

import static org.mahjong4j.tile.MahjongTileType.*;

/**
 * @author yu1ro
 */
public abstract class SanshokuResolver implements NormalYakuResolver {
    protected boolean manzu = false;
    protected boolean pinzu = false;
    protected boolean sohzu = false;

    protected void detectType(MahjongTileType shuntsuType) {
        if (shuntsuType == MANZU) {
            manzu = true;
        } else if (shuntsuType == PINZU) {
            pinzu = true;
        } else if (shuntsuType == SOHZU) {
            sohzu = true;
        }
    }
}
