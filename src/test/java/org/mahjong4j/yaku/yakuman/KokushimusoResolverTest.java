package org.mahjong4j.yaku.yakuman;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author yu1ro
 */
public class KokushimusoResolverTest {
    private KokushimusoResolver resolver;

    @Before
    public void setUp() throws Exception {
        int[] match = {
            1, 0, 0, 0, 0, 0, 0, 0, 2,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 1, 1, 1,
            1, 1, 1
        };

        resolver = new KokushimusoResolver(match);
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}