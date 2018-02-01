package AoC2015.Day22

import java.lang.Integer.max

var localMin = Int.MAX_VALUE
var hardLocalMin = Int.MAX_VALUE

fun main(args: Array<String>) {
    fight(Wizard(), Boss(71, 10), true)
    println(localMin)
    hardFight(Wizard(), Boss(71, 10), true)
    println(hardLocalMin)
}

fun fight(wizard: Wizard, boss: Boss, playerTurn: Boolean) {
    if (wizard.mana <= 0 || wizard.manaSpent >= localMin) return

    wizard.applyShield()
    wizard.applyPoison(boss)
    wizard.applyRecharge()

    if (boss.hp <= 0) {
        if (localMin > wizard.manaSpent) localMin = wizard.manaSpent
        return
    }

    if (!playerTurn) {
        wizard.hp -= max(boss.damage - wizard.armor, 1)
    }

    if (wizard.hp <= 0) {
        return
    }



    if (playerTurn) {
        fight(wizard.copy(mana = wizard.mana - 53, manaSpent = wizard.manaSpent + 53), boss.copy(hp = boss.hp - 4), false)//missile
        fight(wizard.copy(mana = wizard.mana - 73, hp = wizard.hp + 2, manaSpent = wizard.manaSpent + 73), boss.copy(hp = boss.hp - 2), false)//drain
        if (wizard.shieldLeft == 0) fight(wizard.copy(mana = wizard.mana - 113, shieldLeft = 6, manaSpent = wizard.manaSpent + 113), boss.copy(), false)//shield
        if (wizard.poisonLeft == 0) fight(wizard.copy(mana = wizard.mana - 173, poisonLeft = 6, manaSpent = wizard.manaSpent + 173), boss.copy(), false)//poison
        if (wizard.rechargeLeft == 0) fight(wizard.copy(mana = wizard.mana - 229, rechargeLeft = 5, manaSpent = wizard.manaSpent + 229), boss.copy(), false)//recharge
    } else {
        fight(wizard.copy(), boss.copy(), true)//nothing
    }
}

fun hardFight(wizard: Wizard, boss: Boss, playerTurn: Boolean) {
    if (playerTurn) {
        wizard.hp -= 1

        if (wizard.hp <= 0) {
            return
        }
    }
    if (wizard.mana <= 0 || wizard.manaSpent >= hardLocalMin) return

    wizard.applyShield()
    wizard.applyPoison(boss)
    wizard.applyRecharge()

    if (boss.hp <= 0) {
        if (hardLocalMin > wizard.manaSpent) hardLocalMin = wizard.manaSpent
        return
    }

    if (!playerTurn) {
        wizard.hp -= max(boss.damage - wizard.armor, 1)
    }

    if (wizard.hp <= 0) {
        return
    }



    if (playerTurn) {
        hardFight(wizard.copy(mana = wizard.mana - 53, manaSpent = wizard.manaSpent + 53), boss.copy(hp = boss.hp - 4), false)//missile
        hardFight(wizard.copy(mana = wizard.mana - 73, hp = wizard.hp + 2, manaSpent = wizard.manaSpent + 73), boss.copy(hp = boss.hp - 2), false)//drain
        if (wizard.shieldLeft == 0) hardFight(wizard.copy(mana = wizard.mana - 113, shieldLeft = 6, manaSpent = wizard.manaSpent + 113), boss.copy(), false)//shield
        if (wizard.poisonLeft == 0) hardFight(wizard.copy(mana = wizard.mana - 173, poisonLeft = 6, manaSpent = wizard.manaSpent + 173), boss.copy(), false)//poison
        if (wizard.rechargeLeft == 0) hardFight(wizard.copy(mana = wizard.mana - 229, rechargeLeft = 5, manaSpent = wizard.manaSpent + 229), boss.copy(), false)//recharge
    } else {
        hardFight(wizard.copy(), boss.copy(), true)//nothing
    }
}

data class Wizard(var mana: Int = 500, var hp: Int = 50, var armor: Int = 0, var shieldLeft: Int = 0, var poisonLeft: Int = 0, var rechargeLeft: Int = 0, var manaSpent: Int = 0) {

    fun applyShield() {
        if (shieldLeft > 0) {
            armor = 7
            shieldLeft--
        } else {
            armor = 0
        }
    }

    fun applyPoison(boss: Boss) {
        if (poisonLeft > 0) {
            boss.hp -= 3
            poisonLeft--
        }
    }

    fun applyRecharge() {
        if (rechargeLeft > 0) {
            mana += 101
            rechargeLeft--
        }
    }
}

data class Boss(var hp: Int, var damage: Int)
