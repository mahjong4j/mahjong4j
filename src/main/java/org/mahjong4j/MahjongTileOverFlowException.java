package org.mahjong4j;

import org.mahjong4j.tile.Tile;

/**
 * @author yu1ro
 */
public class MahjongTileOverFlowException extends Mahjong4jException {
    //Tile.code
    private int code;
    //何枚見つかり不正なのか
    private int num;

    public MahjongTileOverFlowException(int code, int num) {
        super("麻雀の牌は4枚までしかありません");
        this.code = code;
        this.num = num;
    }

    public String getAdvice() {
        return Tile.valueOf(code).name() + "(code = " + code + ")が" + num + "枚見つかりました";
    }
}
