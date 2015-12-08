package org.mahjong4j;

import org.mahjong4j.hands.MahjongMentsu;

import java.util.List;

/**
 * @author yu1ro
 */
public class IllegalMentsuSizeException extends Mahjong4jException {
    private List<MahjongMentsu> mentsuList;

    public IllegalMentsuSizeException(List<MahjongMentsu> mentsuList) {
        super("面子の数がおかしいです");
        this.mentsuList = mentsuList;
    }

    public String getAdvice() {
        return "面子の数は合計で5個もしくは7個でなければなりませんが" + mentsuList.size() + "個の面子が見つかりました";
    }

    public List<MahjongMentsu> getMentsuList() {
        return mentsuList;
    }
}
