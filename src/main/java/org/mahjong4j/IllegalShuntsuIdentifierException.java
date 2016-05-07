package org.mahjong4j;

import org.mahjong4j.tile.Tile;

/**
 * @author yu1ro
 */
public class IllegalShuntsuIdentifierException extends Mahjong4jException {
    private Tile tile;

    public IllegalShuntsuIdentifierException(Tile tile) {
        super("順子識別牌としてありえない牌を検出しました");
        this.tile = tile;
    }

    public String getAdvice() {
        String entry = tile.name() + "を識別牌として保存しようとしました\n";
        if (tile.getNumber() == 0) {
            return entry + "字牌は順子になりえません";
        }
        return entry + "2番目の牌を順子識別牌とするため、1・9牌は識別牌になりえません";
    }
}
