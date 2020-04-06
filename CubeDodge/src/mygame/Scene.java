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
import java.util.Random;


/**
 *
 * @author panpa
 */


public class Scene {
    
    Node map;
    Node treeNode;
    private final AssetManager assetManager;
    TreeManager tm;
    Player p;
    
    public Scene(AssetManager assetManager, Node map, Node treeNode, Player p){
        this.map = map;
        this.assetManager = assetManager;
        this.treeNode = treeNode;
        this.p = p;
        
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
        }
    }
    
    public void update(float tpf){
        float speed = 10;
        for(int i = 0; i < treeNode.getQuantity(); i++){
            
            Node tree = (Node) treeNode.getChild(i);
            Vector3f treePos = tree.getWorldTranslation();
            //System.out.println(p.sphere.getLocalTranslation());
            tree.move(0, 0, tpf * speed * 1);
            
            if(treePos.z > 40){
                moveTree(treeNode.getChild(i));
            }
            
            if(treePos.multLocal(1, 0, 1).distance(p.sphere.getWorldTranslation()) < 2.6f){
                System.out.println("Trees touched." + treePos.multLocal(1, 0, 1).distance(p.sphere.getWorldTranslation()));
            }
        }
    }
    
    private void moveTree(Spatial tree){
        //System.out.println("Moving tree at" + tree.getWorldTranslation());
        tree.move((randomInt(-80,80)), 0, (randomInt(-130, -100)));
    }
    
    
    private  int randomInt(int min, int max) {

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
