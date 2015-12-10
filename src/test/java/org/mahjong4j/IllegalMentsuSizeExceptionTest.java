package org.mahjong4j;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.tile.MahjongTile.*;

/**
 * @author yu1ro
 */
public class IllegalMentsuSizeExceptionTest {
    private List<MahjongMentsu> less;
    private List<MahjongMentsu> more;

    @Before
    public void setUp() throws Exception {
        less = new ArrayList<>(4);
        less.add(new Toitsu(P7));
        less.add(new Shuntsu(false, S3));
        less.add(new Shuntsu(false, S3));
        less.add(new Kotsu(false, NAN));

        more = new ArrayList<>(8);
        more.add(new Toitsu(P7));
        more.add(new Shuntsu(false, S3));
        more.add(new Shuntsu(false, S3));
        more.add(new Kotsu(false, NAN));
        more.add(new Shuntsu(false, S3));
        more.add(new Shuntsu(false, S3));
        more.add(new Kotsu(false, NAN));
    }

    @Test
    public void testSetLessMentsu() throws Exception {
        try {
            new MentsuComp(less);
        } catch (IllegalMentsuSizeException e) {
            assertEquals("面子の組が和了の形になっていません", e.getMessage());
            assertEquals("面子の数は合計で5個もしくは七対子の場合のみ7個でなければなりませんが4個の面子が見つかりました", e.getAdvice());
            assertThat(e.getMentsuList(), hasItems(
                new Toitsu(P7),
                new Shuntsu(false, S3),
                new Kotsu(false, NAN),
                new Shuntsu(false, S3)
            ));
        }
    }

    @Test
    public void testSetMoreMentsu() throws Exception {
        try {
            new MentsuComp(more);
        } catch (IllegalMentsuSizeException e) {
            assertEquals("面子の組が和了の形になっていません", e.getMessage());
            assertEquals("面子の数は合計で5個もしくは七対子の場合のみ7個でなければなりませんが7個の面子が見つかりました", e.getAdvice());
            assertThat(e.getMentsuList(), hasItems(
                new Toitsu(P7),
                new Shuntsu(false, S3),
                new Kotsu(false, NAN),
                new Shuntsu(false, S3),
                new Shuntsu(false, S3),
                new Kotsu(false, NAN),
                new Shuntsu(false, S3)
            ));
        }
    }
}