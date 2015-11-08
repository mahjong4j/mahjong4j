package org.mahjong4j.mahjong;


public class JikazeResolver implements MahjongResolver {
    final int HAN = MahjongYakuList.JIKAZE.getHan();

    MahjongTile jikaze;

    public JikazeResolver(MahjongTile jikaze) {
        this.jikaze = jikaze;
    }

    @Override
    public int howHan() {
        return HAN;
    }

    public boolean isJikaze(MahjongTile[] kotsu) {
        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i] == jikaze) {
                return true;
            }

        }
        return false;
    }

}
