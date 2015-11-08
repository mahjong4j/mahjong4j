package org.mahjong4j.mahjong;


public class PinfuResolver implements MahjongResolver {

    final int KUISAGARI = MahjongYakuList.PINFU.getHan();
    private final int HAN = MahjongYakuList.PINFU.getHan();
    private MahjongTile jikazeHai, bakazeHai;
    //boolean naki = false;

    public PinfuResolver(MahjongTile jikaze, MahjongTile bakaze) {
        this.jikazeHai = jikaze;
        this.bakazeHai = bakaze;
    }

    @Override
    public int howHan() {
        //コメントは鳴きをゲームに組み込む時用

        //if(naki){
        //return KUISAGARI;
        //}else{
        return HAN;
        //}
    }

    public boolean isPinfu(MahjongTile[] shuntsu, MahjongTile janto) {
        if (shuntsu[3] == null) {
            return false;
        } else {
            if (janto != jikazeHai && janto != bakazeHai && janto.getNumber() != 0) {
                return true;
            } else {
                return false;
            }
        }

    }

}
