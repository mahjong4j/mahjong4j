package org.mahjong4j;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.yaku.normals.NormalYakuResolver;
import org.mahjong4j.yaku.yakuman.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:このクラスで使う役のカスタマイズができそう
 *
 * @author yu1ro
 */
public class Mahjong4jYakuConfig {
    //---------------役満------------------
    private List<YakumanResolver> yakumanResolverList = new ArrayList<YakumanResolver>(9);
    //--------------通常役-----------------
    private List<NormalYakuResolver> normalResolverList = new ArrayList<NormalYakuResolver>(19);

    public static List<YakumanResolver> getYakumanResolverList(MahjongHands hands) {
        List<YakumanResolver> yakumanResolverList = new ArrayList<YakumanResolver>(9);
        yakumanResolverList.add(new ChinrohtohResolver(hands));
        yakumanResolverList.add(new ChurenpohtohResolver(hands));
        yakumanResolverList.add(new DaisangenResolver(hands));
        yakumanResolverList.add(new DaisushiResolver(hands));
        yakumanResolverList.add(new KokushimusoResolver(hands));
        yakumanResolverList.add(new RyuisoResolver(hands));
        yakumanResolverList.add(new ShosushiResolver(hands));
        yakumanResolverList.add(new SuankoResolver(hands));
        yakumanResolverList.add(new TsuisoResolver(hands));

        return yakumanResolverList;
    }
}
