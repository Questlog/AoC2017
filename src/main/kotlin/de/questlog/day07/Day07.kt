package de.questlog.day07

data class TreeElement (
        val name : String,
        var weight : Int,
        var parent : TreeElement?,
        val children : MutableList<TreeElement> = ArrayList()
) {
    fun getFullWeight() : Int = weight + children.sumBy { it.getFullWeight() }
    fun hasChilds() : Boolean = !children.isEmpty()
    fun isLeaf() : Boolean = children.isEmpty()
    fun countAllChildren() : Int = children.size + children.sumBy { it.countAllChildren() }
    fun isBalanced() : Boolean {
        if(isLeaf())
            return true
        val fullWeight = children.first().getFullWeight()
        return !children.any { it.getFullWeight() != fullWeight }
    }
}

data class Day07Result(
        val rootElementName : String,
        val rebalanceValue : Int
)

fun solve(flatTree : String) : Day07Result {

    val treeElements = HashMap<String, TreeElement>()

    flatTree.split("\n").forEach { line ->
        val tower = line.split(" -> ")
        val hasChildren = tower.size > 1

        val parent = tower.first()

        if (parent.isEmpty())
            return@forEach

        val (parentName, parentWeight) = parent.split(" ").let {
            Pair(
                it.first(),
                it.last().trim('(', ')').toInt()
            )
        }

        val treeElement = treeElements.getOrPut(parentName, { TreeElement(parentName, parentWeight, null) })
        treeElement.weight = parentWeight

        if(hasChildren) {
            val childsStr = tower.last()
            val children = childsStr.split(", ").forEach {
                treeElements.compute(it) { k, v ->
                    if(v != null){
                        treeElement.children.add(v)
                        v.parent = treeElement
                        return@compute v
                    } else {
                        val e = TreeElement(k, 0, treeElement)
                        treeElement.children.add(e)
                        return@compute e
                    }
                }
            }
        }
    }

    var node = treeElements.values.first()
    while (node.parent != null)
        node = node.parent!!

    val newWeight = getRebalanceValue(node)

    return Day07Result(rootElementName = node.name, rebalanceValue = newWeight)
}

/*
 * 133
 * 1
 * 1
 * 1
 *
 * 1
 * 1
 * 1
 * 1133
 *
 */

fun getRebalanceValue(element: TreeElement) : Int {
    if(element.hasChilds()) {
        var unbalancedChild : TreeElement? = element
        var parent :TreeElement = element
        while (unbalancedChild != null) {
            parent = unbalancedChild
            unbalancedChild = unbalancedChild.children.firstOrNull { !it.isBalanced() }
        }

        val rightElement = parent.children.zipWithNext().first { it.first.getFullWeight() == it.second.getFullWeight() }.first
        val rightWeight = rightElement.getFullWeight()
        val wrongElement = parent.children.first {it.getFullWeight() != rightWeight}
        return wrongElement.weight - (wrongElement.getFullWeight() - rightWeight)
    }
    return 0
}

fun printTree(element: TreeElement?, depth: Int = 0) {
    println(" " * depth + "${element?.name} (${element?.weight} , ${element?.getFullWeight()})")
    if (element != null) {
        for(child in element.children)
            printTree(child, depth + 1)
    }
}

private operator fun String.times(times: Int): String {
    val sb = StringBuilder(this.length*times)
    for(i in 1..times)
        sb.append(this)
    return sb.toString()
}
