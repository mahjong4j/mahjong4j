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

        yakumanResolverSet.add(new RenhoResolver(generalSituation, personalSituation));
        yakumanResolverSet.add(new ChihoResolver(generalSituation, personalSituation));
        yakumanResolverSet.add(new TenhoResolver(generalSituation, personalSituation));

        return yakumanResolverSet;
    }

    public static Set<NormalYakuResolver> getNormalYakuResolverSet(MentsuComp comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        Set<NormalYakuResolver> normalYakuResolverSet = new HashSet<>(20);
        normalYakuResolverSet.add(new ChantaResolver(comp));
        normalYakuResolverSet.add(new ChunResolver(comp));
        normalYakuResolverSet.add(new ChinitsuResolver(comp));
        normalYakuResolverSet.add(new ChitoitsuResolver(comp));
        normalYakuResolverSet.add(new HakuResolver(comp));
        normalYakuResolverSet.add(new HatsuResolver(comp));
        normalYakuResolverSet.add(new HonitsuResolver(comp));
        normalYakuResolverSet.add(new HonrohtohResolver(comp));
        normalYakuResolverSet.add(new IkkitsukanResolver(comp));
        normalYakuResolverSet.add(new IpeikoResolver(comp));
        normalYakuResolverSet.add(new JunchanResolver(comp));
        normalYakuResolverSet.add(new RyanpeikoResolver(comp));
        normalYakuResolverSet.add(new SanankoResolver(comp));
        normalYakuResolverSet.add(new SankantsuResolver(comp));
        normalYakuResolverSet.add(new SanshokudohjunResolver(comp));
        normalYakuResolverSet.add(new SanshokudohkoResolver(comp));
        normalYakuResolverSet.add(new ShosangenResolver(comp));
        normalYakuResolverSet.add(new TanyaoResolver(comp));
        normalYakuResolverSet.add(new ToitoihoResolver(comp));

        normalYakuResolverSet.add(new PinfuResolver(comp, generalSituation, personalSituation));
        normalYakuResolverSet.add(new TsumoResolver(comp, generalSituation, personalSituation));
        normalYakuResolverSet.add(new JikazeResolver(comp, generalSituation, personalSituation));
        normalYakuResolverSet.add(new BakazeResolver(comp, generalSituation, personalSituation));
        normalYakuResolverSet.add(new IppatsuResolver(generalSituation, personalSituation));
        normalYakuResolverSet.add(new HouteiResolver(generalSituation, personalSituation));
        normalYakuResolverSet.add(new HaiteiResolver(generalSituation, personalSituation));
        normalYakuResolverSet.add(new ReachResolver(generalSituation, personalSituation));
        normalYakuResolverSet.add(new RinshankaihohResolver(comp, personalSituation));
        normalYakuResolverSet.add(new ChankanResolver(generalSituation, personalSituation));
        normalYakuResolverSet.add(new DoubleReachResolver(generalSituation, personalSituation));

        return normalYakuResolverSet;
    }
}
