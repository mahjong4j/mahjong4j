package org.mahjong4j.yaku.normals;

import org.mahjong4j.tile.TileType;

import static org.mahjong4j.tile.TileType.*;

/**
 * @author yu1ro
 */
public abstract class SanshokuResolver implements NormalYakuResolver {
    protected boolean manzu = false;
    protected boolean pinzu = false;
    protected boolean sohzu = false;

    protected void detectType(TileType shuntsuType) {
        if (shuntsuType == MANZU) {
            manzu = true;
        } else if (shuntsuType == PINZU) {
            pinzu = true;
        } else if (shuntsuType == SOHZU) {
            sohzu = true;
        }
    }
}
