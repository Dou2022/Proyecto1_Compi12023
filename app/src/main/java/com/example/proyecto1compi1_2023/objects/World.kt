package com.example.proyecto1compi1_2023.objects

class World  : java.io.Serializable{
    var name: String? = null
    var rows = 0
    var cols = 0
    var config: Config?
    private var board: MutableList<Piece>
    private var boxes: MutableList<Box>
    private var targets: MutableList<Target>
    var player: Player?

    init {
        board = ArrayList()
        boxes = ArrayList()
        targets = ArrayList()
        player = null
        config = null
    }

    fun createWorld(name: String?, rows: Int, cols: Int) {
        this.name = name
        this.rows = rows
        this.cols = cols
    }

    fun createPlayer(player: Player?) {
        this.player = player
    }

    fun createBox(box: Box) {
        boxes.add(box)
    }

    fun createTarget(target: Target) {
        targets.add(target)
    }

    fun createPiece(piece: Piece) {
        board.add(piece)
    }

    fun getBoard(): List<Piece> {
        return board
    }

    fun setBoard(Board: MutableList<Piece>) {
        board = Board
    }

    fun getBoxes(): List<Box> {
        return boxes
    }

    fun setBoxes(Boxes: MutableList<Box>) {
        boxes = Boxes
    }

    fun getTargets(): List<Target> {
        return targets
    }

    fun setTargets(targets: MutableList<Target>) {
        this.targets = targets
    }
}