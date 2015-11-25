package org.mahjong4j;

import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 */
public class MahjongTileOverFlowException extends Mahjong4jException {
    //MahjongTile.code
    private int code;
    //何枚見つかり不正なのか
    private int num;

    public MahjongTileOverFlowException(int code, int num) {
        super("麻雀の牌は4枚までしかありません");
        this.code = code;
        this.num = num;
    }

    public String getAdvice() {
        return MahjongTile.valueOf(code).name() + "(code = " + code + ")が" + num + "枚見つかりました";
    }
}
