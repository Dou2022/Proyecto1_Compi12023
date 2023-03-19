    package Objects;

public class Piece extends Posicion {
    
    private String type;

    public Piece(int posX, int posY,String type) {
        super(posX, posY);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
