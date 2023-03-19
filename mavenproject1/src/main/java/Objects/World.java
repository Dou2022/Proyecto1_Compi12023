
package Objects;

import java.util.ArrayList;
import java.util.List;



public class World {
    
    private String name = null;
    private int rows;
    private int cols;
    private Config config;
    private List<Piece> board;
    private List<Box> boxes;
    private List<Target> targets;
    private Player player;
    
    
    public World() {
        
        board = new ArrayList<Piece>();
        boxes = new ArrayList<Box>();
        targets = new ArrayList<Target>();
        player = null;
        config = null;
        
    }
    public void createWorld(String name, int rows, int cols){
        this.name = name;
        this.rows = rows;
        this.cols = cols;
    }
    public void createPlayer(Player player){
        this.player = player;
        
    }
    
    public void createBox(Box box){
        boxes.add(box);
    }
    
    public void createTarget(Target target ){
        targets.add(target);
    }
    
    public void createPiece(Piece piece){
        board.add(piece);
    }
    
    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public List<Piece> getBoard() {
        return board;
    }

    public void setBoard(List<Piece> Board) {
        this.board = Board;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<Box> Boxes) {
        this.boxes = Boxes;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
    
    
    
}
