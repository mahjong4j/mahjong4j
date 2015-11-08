package org.mahjong4j.mahjong;


public class ShosangenResolver implements MahjongResolver {
    final int HAN = MahjongYakuList.SHOSANGEN.getHan();

    boolean haku;
    boolean hatsu;
    boolean chun;

    public static void main(String[] args) {
        ShosangenResolver sho = new ShosangenResolver();

        MahjongTile j = MahjongTile.HAT;

        MahjongTile ko[] = {
                MahjongTile.CHN,
                MahjongTile.TON,
                MahjongTile.S3
        };

        if (sho.isShosangen(ko, j)) {
            System.out.println("True!!");
        } else {
            System.out.println("False...");
        }


    }

    public int howHan() {
        return HAN;
    }

    public void switching(MahjongTile s) {
        switch (s) {
            case HAK:
                haku = true;
                break;
            case HAT:
                hatsu = true;
                break;
            case CHN:
                chun = true;
                break;

            default:
                break;
        }
    }

    public boolean isShosangen(MahjongTile[] kohtsu, MahjongTile janto) {
        if (janto.getType() != MahjongTileType.SANGEN) {
            return false;
        }
        //初期化
        haku = false;
        hatsu = false;
        chun = false;

        switching(janto);

        for (int i = 0; i < kohtsu.length && kohtsu[i] != null; i++) {
            switching(kohtsu[i]);
        }

        return haku && hatsu && chun;
    }
}
