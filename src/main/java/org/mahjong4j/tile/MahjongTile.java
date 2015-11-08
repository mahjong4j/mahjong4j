package org.mahjong4j.tile;

public enum MahjongTile {
    M1(0, MahjongTileType.MANZU, 1),
    M2(1, MahjongTileType.MANZU, 2),
    M3(2, MahjongTileType.MANZU, 3),
    M4(3, MahjongTileType.MANZU, 4),
    M5(4, MahjongTileType.MANZU, 5),
    M6(5, MahjongTileType.MANZU, 6),
    M7(6, MahjongTileType.MANZU, 7),
    M8(7, MahjongTileType.MANZU, 8),
    M9(8, MahjongTileType.MANZU, 9),

    P1(9, MahjongTileType.PINZU, 1),
    P2(10, MahjongTileType.PINZU, 2),
    P3(11, MahjongTileType.PINZU, 3),
    P4(12, MahjongTileType.PINZU, 4),
    P5(13, MahjongTileType.PINZU, 5),
    P6(14, MahjongTileType.PINZU, 6),
    P7(15, MahjongTileType.PINZU, 7),
    P8(16, MahjongTileType.PINZU, 8),
    P9(17, MahjongTileType.PINZU, 9),

    S1(18, MahjongTileType.SOHZU, 1),
    S2(19, MahjongTileType.SOHZU, 2),
    S3(20, MahjongTileType.SOHZU, 3),
    S4(21, MahjongTileType.SOHZU, 4),
    S5(22, MahjongTileType.SOHZU, 5),
    S6(23, MahjongTileType.SOHZU, 6),
    S7(24, MahjongTileType.SOHZU, 7),
    S8(25, MahjongTileType.SOHZU, 8),
    S9(26, MahjongTileType.SOHZU, 9),

    TON(27, MahjongTileType.FONPAI, 0),
    NAN(28, MahjongTileType.FONPAI, 0),
    SHA(29, MahjongTileType.FONPAI, 0),
    PEI(30, MahjongTileType.FONPAI, 0),

    HAK(31, MahjongTileType.SANGEN, 0),
    HAT(32, MahjongTileType.SANGEN, 0),
    CHN(33, MahjongTileType.SANGEN, 0);

    private int code;
    private MahjongTileType type;
    private int number;

    MahjongTile(int code, MahjongTileType type, int number) {
        this.code = code;
        this.type = type;
        this.number = number;
    }

    public static MahjongTile getTile(int c) {
        return MahjongTile.values()[c];
    }

    public int getCode() {
        return code;
    }

    public MahjongTileType getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }
}
