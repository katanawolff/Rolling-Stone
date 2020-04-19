/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapText;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.Random;


/**
 *
 * @author panpa
 */


public class Scene {
    
    Main app;
    Node map;
    Node treeNode;
    private final AssetManager assetManager;
    TreeManager tm;
    Player p;
    Boolean paused;
    BitmapText scoreText;
    int score = 0;
    float speed = 10;
    float localInt = score/60;
    float finalSpeed = speed;
    
    public Scene(AssetManager assetManager, Node map, Node treeNode, Player p, BitmapText scoreText, Main app){
        this.map = map;
        this.assetManager = assetManager;
        this.treeNode = treeNode;
        this.p = p;
        this.scoreText = scoreText;
        this.app = app;
        
        tm = new TreeManager(assetManager);
        changeFloor();
        addTrees(tm);
        
    }
    
    private void updateScore(int score){
        localInt = score/60;
        finalSpeed = speed + localInt;
        if(finalSpeed >= 60){
            finalSpeed = 60;
        }
            scoreText.setText("Current Score: " + (score/60));
            System.out.println(finalSpeed);
            
    }

    private void changeFloor(){
        map.setLocalTranslation(0, -4, 0);
    }

    private void addTrees(TreeManager tm) {
        for(int i = 0; i <= 60; i++){
        treeNode.attachChild(tm.createTree());
        }
    }
    
    public void update(float tpf){
        for(int i = 0; i < treeNode.getQuantity(); i++){
            Node tree = (Node) treeNode.getChild(i);
            Vector3f treePos = tree.getWorldTranslation();
            //System.out.println(p.sphere.getLocalTranslation());
            tree.move(0, 0, tpf * finalSpeed * 1);
            
            if(treePos.z > 10){
                moveTree(tree);
            }
            
            Vector3f pSpot = p.sphere.getLocalTranslation();
            float distance = treePos.set(treePos.x, pSpot.y, treePos.z).distance(pSpot);
            if(distance < 2.5f){
                System.out.println("Contact");
                die();
            }
        }
    }
    
    private void die(){
        app.stop();
    }
    
    private void moveTree(Node tree){
        //System.out.println("Moving tree at" + tree.getWorldTranslation());
        tree.move((randomInt(-80,80)), 0, (randomInt(-100, -45)));
        score++;
        updateScore(score);
    }
    
    
    private  int randomInt(int min, int max) {

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
