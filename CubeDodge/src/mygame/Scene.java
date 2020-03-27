/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;


/**
 *
 * @author panpa
 */


public class Scene {
    
    Node map;
    private final AssetManager assetManager;
    TreeManager tm;
    
    public Scene(AssetManager assetManager, Node map){
        this.map = map;
        this.assetManager = assetManager;
        
        tm = new TreeManager(assetManager);
        changeFloor();
        addTrees(tm);
    }

    private void changeFloor(){
        map.setLocalTranslation(0, -4, 0);
    }

    private void addTrees(TreeManager tm) {
        for(int i = 0; i <= 20; i++){
        map.attachChild(tm.createTree());
            System.out.println(map.getChildren().size());
        }
    }
    public void moveMap(){
        
    }
}
