package Entity;

import iface.iItem;

import java.util.ArrayList;

public class Containers {
    private ArrayList<iItem> items;//容器内的物品
    private double volume;//容器的容积
    private double currentSize;//当前内容物大小
    public Containers(){
        items = new ArrayList<>();
        volume = 0;
        currentSize = 0;
    }
    public boolean putItem(iItem i){//放入物品
        if(i.getSize() + currentSize > volume){
            System.out.println("Container is full");
            return false;
        }
        items.add(i);
        currentSize = currentSize + i.getSize();
        return true;
    }
    public iItem takeItem(iItem i){//取出物品
        if(items.remove(i)){
            return i;
        }else{
            return null;
        }
    }
}
