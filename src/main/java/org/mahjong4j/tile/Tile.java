package org.mahjong4j.tile;

import static org.mahjong4j.tile.TileType.*;

/**
 * @author yu1ro
 */
public enum Tile {
    M1(0, MANZU, 1),
    M2(1, MANZU, 2),
    M3(2, MANZU, 3),
    M4(3, MANZU, 4),
    M5(4, MANZU, 5),
    M6(5, MANZU, 6),
    M7(6, MANZU, 7),
    M8(7, MANZU, 8),
    M9(8, MANZU, 9),

    P1(9, PINZU, 1),
    P2(10, PINZU, 2),
    P3(11, PINZU, 3),
    P4(12, PINZU, 4),
    P5(13, PINZU, 5),
    P6(14, PINZU, 6),
    P7(15, PINZU, 7),
    P8(16, PINZU, 8),
    P9(17, PINZU, 9),

    S1(18, SOHZU, 1),
    S2(19, SOHZU, 2),
    S3(20, SOHZU, 3),
    S4(21, SOHZU, 4),
    S5(22, SOHZU, 5),
    S6(23, SOHZU, 6),
    S7(24, SOHZU, 7),
    S8(25, SOHZU, 8),
    S9(26, SOHZU, 9),

    TON(27, FONPAI, 0),//東
    NAN(28, FONPAI, 0),//南
    SHA(29, FONPAI, 0),//西
    PEI(30, FONPAI, 0),//北

    HAK(31, SANGEN, 0),//白
    HAT(32, SANGEN, 0),//発
    CHN(33, SANGEN, 0);//中

    private int code;
    private TileType type;
    private int number;

    Tile(int code, TileType type, int number) {
        this.code = code;
        this.type = type;
        this.number = number;
    }

    public static Tile valueOf(int c) {
        return Tile.values()[c];
    }

    public int getCode() {
        return code;
    }

    public TileType getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public boolean isYaochu() {
        return number == 0 || number == 1 || number == 9;
    }
}
