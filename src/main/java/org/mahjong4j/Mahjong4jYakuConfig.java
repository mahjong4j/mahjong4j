package org.mahjong4j;

import org.mahjong4j.hands.MentsuComp;
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
    public static List<YakumanResolver> getYakumanResolverList(MentsuComp comp) {
        //国士無双は特殊なので、ここでは扱いません
        List<YakumanResolver> yakumanResolverList = new ArrayList<>(9);
        yakumanResolverList.add(new ChinrohtohResolver(comp));
        yakumanResolverList.add(new ChurenpohtohResolver(comp));
        yakumanResolverList.add(new DaisangenResolver(comp));
        yakumanResolverList.add(new DaisushiResolver(comp));
        yakumanResolverList.add(new RyuisoResolver(comp));
        yakumanResolverList.add(new ShosushiResolver(comp));
        yakumanResolverList.add(new SuankoResolver(comp));
        yakumanResolverList.add(new TsuisoResolver(comp));

        return yakumanResolverList;
    }

    public static List<NormalYakuResolver> getNormalYakuResolverList(MentsuComp comp) {
        List<NormalYakuResolver> normalYakuResolverList = new ArrayList<>(20);
        normalYakuResolverList.add(new ChantaResolver(comp));
        normalYakuResolverList.add(new ChunResolver(comp));
        normalYakuResolverList.add(new ChinitsuResolver(comp));
        normalYakuResolverList.add(new ChitoitsuResolver(comp));
        normalYakuResolverList.add(new HakuResolver(comp));
        normalYakuResolverList.add(new HatsuResolver(comp));
        normalYakuResolverList.add(new HonitsuResolver(comp));
        normalYakuResolverList.add(new HonrohtohResolver(comp));
        normalYakuResolverList.add(new IkkitsukanResolver(comp));
        normalYakuResolverList.add(new IpeikoResolver(comp));
        normalYakuResolverList.add(new JunchanResolver(comp));
        normalYakuResolverList.add(new PinfuResolver(comp));
        normalYakuResolverList.add(new RyanpeikoResolver(comp));
        normalYakuResolverList.add(new SanankoResolver(comp));
        normalYakuResolverList.add(new SanshokudohjunResolver(comp));
        normalYakuResolverList.add(new SanshokudohkoResolver(comp));
        normalYakuResolverList.add(new ShosangenResolver(comp));
        normalYakuResolverList.add(new TanyaoResolver(comp));

        return normalYakuResolverList;
    }
}
