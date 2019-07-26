package core.struct

import core.geom.AABB
import core.geom.Point

import java.util.*

class Structure : Iterable<Structure> {

    private var parent: Structure? = null

    var aabb: AABB? = null
        protected set
    private val subStructures = ArrayList<Structure>()
    var isBase: Boolean = false
        protected set

    constructor() {
        aabb = AABB.ZERO
        isBase = false
    }

    constructor(aabb: AABB) {
        aabb = aabb
        isBase = true
    }


    fun getSubStructures(): ArrayList<Structure> {
        if (isBase) throw UnsupportedOperationException()

        return subStructures
    }

    fun getParent(): Optional<Structure> {
        return Optional.ofNullable(parent)
    }

    fun setBaseAABB(aabb: AABB) {
        if (!isBase) throw UnsupportedOperationException("cannot set base AABB to not base structure")
        this.aabb = aabb
        recalculateParentAABB()
    }

    fun translate(p: Point) {
        translate(p.x, p.y)
    }

    fun translate(dx: Int, dy: Int) {
        translateUnsafe(dx, dy)
        recalculateParentAABB()
    }

    fun getIntersectionStructure(other: Structure): Optional<Entry<Structure, Structure>> {
        if (!aabb!!.intersects(other.aabb)) return Optional.empty<Entry<Structure, Structure>>()

        return if (isBase) {
            if (other.isBase) {
                Optional.of<Entry<Structure, Structure>>(Map.entry<Structure, Structure>(this, other))
            } else {
                other.getSubStructures().stream().map<Optional<Entry<Structure, Structure>>> { s -> s.getIntersectionStructure(this) }
                        .filter(Predicate<Optional<Entry<Structure, Structure>>> { it.isPresent() })
                        .findAny().orElse(Optional.empty<Entry<Structure, Structure>>())
            }
        } else {
            getSubStructures().stream().map<Optional<Entry<Structure, Structure>>> { s -> s.getIntersectionStructure(other) }
                    .filter(Predicate<Optional<Entry<Structure, Structure>>> { it.isPresent() })
                    .findAny().orElse(Optional.empty<Entry<Structure, Structure>>())
        }
    }


    fun intersects(other: Structure): Boolean {
        if (!aabb!!.intersects(other.aabb)) return false

        return if (isBase) {
            if (other.isBase) {
                true
            } else {
                other.getSubStructures().stream().anyMatch { s -> s.intersects(this) }
            }
        } else {

            getSubStructures().stream().anyMatch { s -> s.intersects(other) }

        }
    }

    fun intersects(aabb: AABB): Boolean {
        if (!aabb.intersects(aabb)) return false
        return if (isBase) true else getSubStructures().stream().anyMatch { s -> s.intersects(aabb) }

    }


    fun attach(vararg ss: Structure) {
        if (isBase) throw UnsupportedOperationException()

        for (s in ss) {
            if (this == s) continue
            if (!s.setParent(this)) throw IllegalArgumentException("Structure has parent")
            this.getSubStructures().add(s)
            aabb = aabb!!.append(s.aabb!!)
        }

        recalculateParentAABB()
    }

    fun detach() {
        if (!getParent().isPresent) throw NullPointerException("Structure does not have parent")

        if (!getParent().get().getSubStructures().remove(this))
            throw RuntimeException("Invalid structure hierarchy")

        getParent().get().recalculateAABB()
        this.parent = null
    }

    private fun translateUnsafe(dx: Int, dy: Int) {
        if (isBase) {
            aabb = aabb!!.translate(dx, dy)
        } else {
            getSubStructures().forEach { s -> s.translateUnsafe(dx, dy) }
            aabb = aabb!!.translate(dx, dy)
        }
    }

    private fun setParent(s: Structure): Boolean {
        if (getParent().isPresent) return false
        this.parent = s

        return true
    }

    private fun recalculateParentAABB() {
        getParent().ifPresent(Consumer<Structure> { it.recalculateAABB() })
    }

    private fun recalculateAABB() {
        if (isBase) throw UnsupportedOperationException()
        aabb = AABB.ZERO
        getSubStructures().forEach { s -> aabb = aabb!!.append(s.aabb!!) }
        recalculateParentAABB()
    }

    override fun toString(): String {
        return "Structure{" +
                "AABB=" + aabb +
                (if (isBase) "" else ", subStructures=$subStructures") +
                '}'.toString()
    }

    override fun iterator(): Iterator<Structure> {
        return getSubStructures().iterator()
    }
}
