package AoC2015.Day21

import java.util.*

fun main(args: Array<String>) {
    val weapons = listOf<Weapon>(
            Weapon(8, 4),
            Weapon(10, 5),
            Weapon(25, 6),
            Weapon(40, 7),
            Weapon(74, 8))
    val armor = listOf<Armor>(
            Armor(13, 1),
            Armor(31, 2),
            Armor(53, 3),
            Armor(75, 4),
            Armor(102, 5)
    )
    val rings = listOf<Ring>(
            Ring(25, 1, 0),
            Ring(50, 2, 0),
            Ring(100, 3, 0),
            Ring(20, 0, 1),
            Ring(40, 0, 2),
            Ring(80, 0, 3)
    )
    val presets = generatePresets(weapons, armor, rings)
    println(presets.filter { win(it.second, Creature(100, 8, 2)) }.minBy { it.first })
    println(presets.filterNot { win(it.second, Creature(100, 8, 2)) }.maxBy { it.first })
}

private fun generatePresets(weapons: List<Weapon>, armor: List<Armor>, rings: List<Ring>): List<Pair<Int, Creature>> {
    val weaponOnly = weapons.map { it.cost to Creature(100, it.damage, 0) }
    val weaponArmor = ArrayList<Pair<Int, Creature>>()
    for (w in weapons) {
        armor.mapTo(weaponArmor) { w.cost + it.cost to Creature(100, w.damage, it.armor) }
    }
    val ringOptions = createPairs(rings, rings).map { Ring(it.first.cost + it.second.cost, it.first.damage + it.second.damage, it.first.armor + it.second.armor) } + rings
    val weaponsRings = ArrayList<Pair<Int, Creature>>()
    for (w in weapons) {
        ringOptions.mapTo(weaponsRings) { w.cost + it.cost to Creature(100, w.damage + it.damage, it.armor) }
    }
    for (w in weaponArmor) {
        ringOptions.mapTo(weaponsRings) { w.first + it.cost to Creature(100, w.second.damage + it.damage, it.armor + w.second.armor) }
    }
    return weaponOnly + weaponArmor + weaponsRings
}

private fun win(player: Creature, boss: Creature): Boolean {
    var playerTurn = true
    var playerHp = player.hp
    var bossHp = boss.hp
    while (playerHp > 0 && bossHp > 0) {
        if (playerTurn) {
            bossHp -= player.damage - boss.armor
        } else {
            playerHp -= boss.damage - player.armor
        }
        playerTurn = !playerTurn
    }
    return playerHp > 0
}

private fun <E> createPairs(first: List<E>, second: List<E>): List<Pair<E, E>> {
    val out = HashSet<Pair<E, E>>()
    for (e in first) {
        second.filter { e != it }.mapTo(out) { e to it }
    }
    return out.toList()
}

data class Creature(val hp: Int, val damage: Int, val armor: Int)
data class Weapon(val cost: Int, val damage: Int)
data class Armor(val cost: Int, val armor: Int)
data class Ring(val cost: Int, val damage: Int, val armor: Int)
