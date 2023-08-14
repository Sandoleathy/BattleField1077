package model;

import Entity.Containers;

import java.util.ArrayList;

public class BattleField {//地图类
    private String mapName;//地图名字
    private ArrayList<Containers> containers;//地图内容器
    public BattleField(){
        containers = new ArrayList<>();
    }
    public ArrayList<Containers> getContainers(){
        return containers;
    }
    public String getMapName(){
        return mapName;
    }
}
