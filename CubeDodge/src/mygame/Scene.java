/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;


/**
 *
 * @author panpa
 */


public class Scene {
    
    Node map;
    Node treeNode;
    private final AssetManager assetManager;
    TreeManager tm;
    
    public Scene(AssetManager assetManager, Node map, Node treeNode){
        this.map = map;
        this.assetManager = assetManager;
        this.treeNode = treeNode;
        
        tm = new TreeManager(assetManager);
        changeFloor();
        addTrees(tm);
    }

    private void changeFloor(){
        map.setLocalTranslation(0, -4, 0);
    }

    private void addTrees(TreeManager tm) {
        for(int i = 0; i <= 20; i++){
        treeNode.attachChild(tm.createTree());
            //System.out.println(map.getChildren().size());
        }
    }
    public void moveMap(float tpf){
        Vector3f v = treeNode.getLocalTranslation();
        treeNode.setLocalTranslation(v.x, v.y, v.z + (tpf *10));
        
        for(int i = 0; i > treeNode.getQuantity(); i++){
            Vector3f treePos = treeNode.getChild(i).getLocalTranslation();
            if (treePos.z > 0){
                moveTree(treeNode.getChild(i));
            }
        }
    }
    
    private void moveTree(Spatial tree){
        tree.setLocalTranslation(0, 0, -10);
    }
}
