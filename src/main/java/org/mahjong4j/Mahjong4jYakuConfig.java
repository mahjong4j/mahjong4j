package org.mahjong4j;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.yaku.normals.*;
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

    public static List<NormalYakuResolver> getNormalYakuResolverList(MahjongHands hands) {
        List<NormalYakuResolver> normalYakuResolverList = new ArrayList<NormalYakuResolver>(20);
        normalYakuResolverList.add(new ChantaResolver(hands));
        normalYakuResolverList.add(new ChunResolver(hands));
        normalYakuResolverList.add(new ChinitsuResolver(hands));
        normalYakuResolverList.add(new ChitoitsuResolver(hands));
        normalYakuResolverList.add(new ChunResolver(hands));
        normalYakuResolverList.add(new HakuResolver(hands));
        normalYakuResolverList.add(new HatsuResolver(hands));
        normalYakuResolverList.add(new HonitsuResolver(hands));
        normalYakuResolverList.add(new HonrohtohResolver(hands));
        normalYakuResolverList.add(new IkkitsukanResolver(hands));
        normalYakuResolverList.add(new IpeikoResolver(hands));
        normalYakuResolverList.add(new JunchanResolver(hands));
        normalYakuResolverList.add(new PinfuResolver(hands));
        normalYakuResolverList.add(new RyanpeikoResolver(hands));
        normalYakuResolverList.add(new SanankoResolver(hands));
        normalYakuResolverList.add(new SanshokudohjunResolver(hands));
        normalYakuResolverList.add(new SanshokudohkoResolver(hands));
        normalYakuResolverList.add(new ShosangenResolver(hands));
        normalYakuResolverList.add(new TanyaoResolver(hands));

        return normalYakuResolverList;
    }
}
