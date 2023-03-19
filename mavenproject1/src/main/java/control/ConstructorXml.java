package control;

import Objects.*;
import java.util.List;

public class ConstructorXml {
    
    public String xmlData(List<World> worlds){
        String data = "";
        
        for (int i = 0; i < worlds.size(); i++) {
            data += world(worlds.get(i));
        }
        data = com1("worlds",data);
        data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+data;
        return data;
    }
    public String xmlDataWorld(World world){
        String data = "";
        
        data += world(world);
        
        data = com1("worlds",data);
        data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+data;
        return data;
    }
    
    private String world(World world){
        String data = "";
        data += com("name",world.getName());
        data += com("rows",world.getRows()+"");
        data += com("cols",world.getCols()+"");
        if (world.getConfig() !=null) {
            data += config(world.getConfig());
        }
        
        data += board(world.getBoard());
        data += boxes(world.getBoxes());
        data += targets(world.getTargets());
        data += player(world.getPlayer());
        data = com1("world",data);
        
        return data;
    }
    private String config(Config value){
        String data = "";
        if (value.getBoxColor()!= null) 
            data += com("box_color",value.getBoxColor());
        if (value.getBoxOnTargetColor()!= null)
            data += com("box_on_target_color",value.getBoxOnTargetColor());
        if (value.getTargetColor()!= null)
            data += com("target_color",value.getTargetColor());
        if (value.getBrickColor() != null)
            data += com("brick_color",value.getBrickColor());
        if (value.getHallColor()!= null)
            data += com("hall_color",value.getHallColor());
        if (value.getUndefinedColor()!= null)
            data += com("undefined_color",value.getUndefinedColor());
        data = com1("config","\n"+data);
        return data;
    }
    private String board(List<Piece> piece){
        String data1 = "";
        String data;
        for (int i = 0; i < piece.size(); i++) {
            data = "";
            data += com("posX",piece.get(i).getPosX()+"");
            data += com("posY",piece.get(i).getPosY()+"");
            data += com("type",piece.get(i).getType());
            data = com1("board",data);
            data1 += data;
        }
        return data1;
    }
     private String boxes(List<Box> box){
        String data;
        String data1 = "";
        for (int i = 0; i < box.size(); i++) {
            data = "";
            data += com("posX",box.get(i).getPosX()+"");
            data += com("posY",box.get(i).getPosY()+"");
            data = com1("boxes",data);
            data1 += data;
        }
        return data1;
    }
     private String targets(List<Target> target){
        String data;
        String data1 = "";
        for (int i = 0; i < target.size(); i++) {
            data = "";
            data += com("posX",target.get(i).getPosX()+"");
            data += com("posY",target.get(i).getPosY()+"");
            data = com1("targets",data);
            data1 += data;
        }
        return data1;
    }
     private String player(Player p){
        String data = "";
            data += com("posX",p.getPosX()+"");
            data += com("posY",p.getPosY()+"");
            data = com1("player",data);
        
        return data;
    }
    
    private String com(String name, String value){
        return "\t<"+name+">"+value+"</"+name+">"+"\n";
    }
    private String com1(String name, String value){
        String n = name;
        String v = value;
        return "<"+n+">"+"\n"+v+"\n"+"</"+n+">"+"\n";
    }
    public String xmlDataWorlds(List<World> worlds){
        String data = "";
        
        for (int i = 0; i < worlds.size(); i++) {
            data += com("world",worlds.get(i).getName());
        }
        data = com1("worlds",data);
        data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+data;
        return data;
    }
    
    
            
}
