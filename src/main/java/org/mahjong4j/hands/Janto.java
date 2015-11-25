package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;

import java.util.ArrayList;
import java.util.List;

/**
 * 雀頭は厳密にはメンツではありませんがここでは含めることにします
 *
 * @author yu1ro
 */
public class Janto implements MahjongMentsu {
    private MahjongTile identifierTile;
    private boolean isMentsu;

    /**
     * 雀頭であることがわかっている場合に使います
     *
     * @param identifierTile 雀頭の種類
     */
    public Janto(MahjongTile identifierTile) {
        this.identifierTile = identifierTile;
        this.isMentsu = true;
    }

    /**
     * 雀頭であるかのチェックを伴います
     *
     * @param tile1 1枚目
     * @param tile2 2枚目
     */
    public Janto(MahjongTile tile1, MahjongTile tile2) {
        if (this.isMentsu = Janto.check(tile1, tile2)) {
            this.identifierTile = tile1;
        }
    }

    /**
     * @param tile1 1枚目
     * @param tile2 2枚目
     * @return 2枚が一致すればtrue
     */
    public static boolean check(MahjongTile tile1, MahjongTile tile2) {
        return tile1 == tile2;
    }

    public MahjongTile getTile() {
        return identifierTile;
    }

    public boolean getIsMentsu() {
        return isMentsu;
    }

    /**
     * 雀頭は常に見せないので常にfalse
     *
     * @return false
     */
    public boolean getIsOpen() {
        return false;
    }

    /**
     * 雀頭になりうる牌をリストにして返す
     * @param tiles 手牌
     * @return 雀頭候補
     */
    public static List<Janto> findJantoCandidate(int[] tiles) {
        List<Janto> result = new ArrayList<Janto>(7);
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] >= 2) {
                result.add(new Janto(MahjongTile.valueOf(i)));
            }
        }
        return result;
    }
}
