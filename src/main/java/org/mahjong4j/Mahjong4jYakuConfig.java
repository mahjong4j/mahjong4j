package org.mahjong4j;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.yaku.normals.*;
import org.mahjong4j.yaku.yakuman.*;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO: to be able to customize Yaku for use
 *
 * @author yu1ro
 */
public class Mahjong4jYakuConfig {
    public static Set<YakumanResolver> getYakumanResolverSet(MentsuComp comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        // KOKUSHIMUSO is not
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

        yakumanResolverSet.add(new RenhoResolver(comp, generalSituation, personalSituation));
        yakumanResolverSet.add(new ChihoResolver(comp, generalSituation, personalSituation));
        yakumanResolverSet.add(new TenhoResolver(comp, generalSituation, personalSituation));

        return yakumanResolverSet;
    }

    public static Set<NormalYakuResolver> getNormalYakuResolverSet(MentsuComp comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
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

        normalYakuResolverList.add(new TsumoResolver(comp, generalSituation, personalSituation));
        normalYakuResolverList.add(new JikazeResolver(comp, generalSituation, personalSituation));
        normalYakuResolverList.add(new BakazeResolver(comp, generalSituation, personalSituation));
        normalYakuResolverList.add(new IppatsuResolver(comp, generalSituation, personalSituation));
        normalYakuResolverList.add(new HouteiResolver(comp, generalSituation, personalSituation));
        normalYakuResolverList.add(new HaiteiResolver(comp, generalSituation, personalSituation));
        normalYakuResolverList.add(new ReachResolver(comp, generalSituation, personalSituation));
        normalYakuResolverList.add(new RinchankaihohResolver(comp, generalSituation, personalSituation));
        normalYakuResolverList.add(new ChankanResolver(comp, generalSituation, personalSituation));
        normalYakuResolverList.add(new DoubleReachResolver(comp, generalSituation, personalSituation));

        return normalYakuResolverList;
    }
}
