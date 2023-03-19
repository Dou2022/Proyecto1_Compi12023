package control;

import Objects.*;



public class ValidarControl {
    private int rows;
    private int column;
    private String Tablero[][];
    
    public void colorConfig(Config config,String v,int left,int right,String tipo){
        switch(tipo){
            case "box_color":
                if (config.getBoxColor() == null) {
                    config.setBoxColor(v);
                }else{
                    error(v,left,right,"Ya hay un box_color");
                }
            break;
            case "box_on_target_color":
                if (config.getBoxOnTargetColor()== null) {
                    config.setBoxOnTargetColor(v);
                }else{
                    error(v,left,right,"Ya hay un box_on_target_color");
                }
            break;
            case "target_color":
                if (config.getTargetColor() == null) {
                    config.setTargetColor(v);
                }else{
                    error(v,left,right,"Ya hay un target_color");
                }
            break;
            case "brick_color":
                if (config.getBrickColor() == null) {
                    config.setBrickColor(v);
                }else{
                    error(v,left,right,"Ya hay un brick_color");
                }
            break;
            case "hall_color":
                if (config.getHallColor()== null) {
                    config.setHallColor(v);
                }else{
                    error(v,left,right,"Ya hay un hall_color");
                }
            break;
            case "undefined_color":
                if (config.getUndefinedColor()== null) {
                    config.setUndefinedColor(v);
                }else{
                    error(v,left,right,"Ya hay un undefined_color");
                }
            break;
            case "player_color":
                if (config.getPlayerColor()== null) {
                    config.setPlayerColor(v);
                }else{
                    error(v,left,right,"Ya hay un player_color");
                }
            break;
            default:
                ;
        }
                
    }
    
    public void error(String v,int left,int right,String tipo){
        System.out.println("valor: "+v+" fila: "+left+" columna: "+right+" descripcion: "+tipo);
        
    }
    
    public void worldConfig(World world,String v,int left,int right,String tipo){
        System.out.println("valor: "+v);
        switch(tipo){
            case "name":
                if (world.getName() == null) {
                    world.setName(v);
                }else{
                    error(v,left,right,"nombre mundo ya iniciado");
                }
            break;
            case "rows":
                if (world.getRows() == 0) {
                    world.setRows(Integer.parseInt(v));
                    this.rows = Integer.parseInt(v);
                }else{
                    error(v,left,right,"fila mundo ya iniciado");
                }
            break;
            case "cols":
                if (world.getCols() == 0) {
                    world.setCols(Integer.parseInt(v));
                    this.column = Integer.parseInt(v);
                }else{
                    error(v,left,right,"columna mundo ya iniciado");
                }
            break;
            default:
                
        }
        if (world.getRows() != 0 && world.getCols() != 0) {
            Tablero = new String[world.getRows()+1][world.getCols()+1];
        }
    }
    
    public boolean boardValidar(World world, Piece p,int left,int right){
        boolean estado = true;
        
         if (this.rows < p.getPosX()) {
                error("posx:"+p.getPosX()+" posy: "+p.getPosY(),left,right,"sale del limite de fila tablero");
                estado = false;
        }
        if (this.column < p.getPosY()) {
            error("posx:"+p.getPosX()+" posy: "+p.getPosY(),left,right,"sale del limite de columna tablero");
            estado = false;
        }
        if (estado && Tablero[p.getPosX()][p.getPosY()] == null) {
            Tablero[p.getPosX()][p.getPosY()] = p.getType();
        }else{
            if(estado)
                error("posx:"+p.getPosX()+" posy: "+p.getPosY(),left,right,"Ya esta ocupado este espacio"+Tablero[p.getPosX()][p.getPosY()]);
            estado = false;
        }
        if (estado)
            world.createPiece(p);
        
        return estado;
    }
    
    public boolean targetValidar(World world, Target p,int left,int right){
        boolean estado = true;
        
        if (this.rows < p.getPosX()) {
                error("posx:"+p.getPosX()+" posy: "+p.getPosY(),left,right,"sale del limite de fila tablero");
                estado = false;
        }
        if (this.column < p.getPosY()) {
            error("posx:"+p.getPosX()+" posy: "+p.getPosY(),left,right,"sale del limite de columna tablero");
            estado = false;
        }
        if ( estado &&  Tablero[p.getPosX()][p.getPosY()] == null ) {
            Tablero[p.getPosX()][p.getPosY()] = "Almacenamiento";
        }else{
            if(estado)
                error("posx:"+p.getPosX()+" posy: "+p.getPosY(),left,right,"Ya esta ocupado este espacio"+Tablero[p.getPosX()][p.getPosY()]);
            estado = false;
        }
        
        if (estado)
            world.createTarget(p);
        return estado;
    }
    public boolean boxValidar(World world, Box p,int left,int right){
        boolean estado = true;

        if (this.rows < p.getPosX()) {
            error("posx:"+p.getPosX()+" posy: "+p.getPosY(),left,right,"sale del limite de fila tablero");
            estado = false;
        }
        if (this.column < p.getPosY()) {
            error("posx:"+p.getPosX()+" posy: "+p.getPosY(),left,right,"sale del limite de columna tablero");
            estado = false;
        }
        if ( estado &&  Tablero[p.getPosX()][p.getPosY()] == null ) {
            Tablero[p.getPosX()][p.getPosY()] = "Caja";
        }else{
            if(estado)
                error("posx:"+p.getPosX()+" posy: "+p.getPosY(),left,right,"Ya esta ocupado este espacio"+Tablero[p.getPosX()][p.getPosY()]);
            estado = false;
        }
        if (estado)
            world.createBox(p); ;
        return estado;
    }
    public boolean playerValidar(World world, Player p,int left,int right){
        boolean estado = true;
        
        if (this.rows < p.getPosX() ) {
            error("posx:"+p.getPosX()+" posy: "+p.getPosY(),left,right,"sale del limite de fila tablero");
            estado = false;
        }
        if (this.column < p.getPosY()) {
            error("posx:"+p.getPosX()+" posy: "+p.getPosY(),left,right,"sale del limite de columna tablero");
            estado = false;
        }
        if ( estado &&  Tablero[p.getPosX()][p.getPosY()] == null ) {
            Tablero[p.getPosX()][p.getPosY()] = "Player";
        }else{
            if(estado)
                error("posx:"+p.getPosX()+" posy: "+p.getPosY(),left,right,"Ya esta ocupado este espacio"+Tablero[p.getPosX()][p.getPosY()]);
            estado = false;
        }
        if (estado)
            world.createPlayer(p); ;
        return estado;
    }
    
}
