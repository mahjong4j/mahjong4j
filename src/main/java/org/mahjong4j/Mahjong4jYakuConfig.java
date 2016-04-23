package org.mahjong4j;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.yaku.normals.*;
import org.mahjong4j.yaku.yakuman.*;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO:このクラスで使う役のカスタマイズができそう
 *
 * @author yu1ro
 */
public class Mahjong4jYakuConfig {
    public static Set<YakumanResolver> getYakumanResolverSet(MentsuComp comp) {
        //国士無双は特殊なので、ここでは扱いません
        Set<YakumanResolver> yakumanResolverSet = new HashSet<>(9);
        yakumanResolverSet.add(new ChinrohtohResolver(comp));
        yakumanResolverSet.add(new ChurenpohtohResolver(comp));
        yakumanResolverSet.add(new DaisangenResolver(comp));
        yakumanResolverSet.add(new DaisushiResolver(comp));
        yakumanResolverSet.add(new RyuisoResolver(comp));
        yakumanResolverSet.add(new ShosushiResolver(comp));
        yakumanResolverSet.add(new SuankoResolver(comp));
        yakumanResolverSet.add(new SukantsuResolver(comp));
        yakumanResolverSet.add(new TsuisoResolver(comp));

        return yakumanResolverSet;
    }

    public static Set<NormalYakuResolver> getNormalYakuResolverSet(MentsuComp comp) {
        Set<NormalYakuResolver> normalYakuResolverList = new HashSet<>(20);
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
        normalYakuResolverList.add(new SankantsuResolver(comp));
        normalYakuResolverList.add(new SanshokudohjunResolver(comp));
        normalYakuResolverList.add(new SanshokudohkoResolver(comp));
        normalYakuResolverList.add(new ShosangenResolver(comp));
        normalYakuResolverList.add(new TanyaoResolver(comp));
        normalYakuResolverList.add(new ToitoihoResolver(comp));

        return normalYakuResolverList;
    }
}
