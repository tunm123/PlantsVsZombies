package top.tunm.xmut.tunmpvz;

import java.util.ArrayList;

/**
 * Created by jingyuyan on 2018/12/6.
 */

public class LineSet {
    private static ArrayList<CombatLine> combatLines;

    public static ArrayList<CombatLine> getCombatLines() {
        return combatLines;
    }

    public static void setCombatLines(ArrayList<CombatLine> combatLines) {
        LineSet.combatLines = combatLines;
    }
}
