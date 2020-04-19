/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Dome;
import java.util.Random;

/**
 *
 * @author panpa
 */


public class TreeManager {
    

    private final AssetManager assetManager;
    
    public TreeManager(AssetManager assetManager){

        this.assetManager = assetManager;
        
        System.out.println("Tree Manager Complete");
    }
    
    public Node createTree(){
        
        Node tree = new Node();
        Vector3f temp = new Vector3f(0,0,0);
        Dome top = new Dome(temp,4,4,4);
        Geometry treeTop = new Geometry("Dome", top);
        Material topMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        topMat.setColor("Color", ColorRGBA.Green);
        treeTop.setMaterial(topMat);
        
        //System.out.println("Dome Complete");
        
        Box stem = new Box(1.5f, 7, 1.5f);
        Geometry treeStem = new Geometry("Box", stem);
        Material stemMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        stemMat.setColor("Color", ColorRGBA.Brown);
        treeStem.setMaterial(stemMat);
        
        //System.out.println("Stem Complete");
        treeTop.setLocalTranslation(0, 7, 0);
        tree.attachChild(treeTop);
        treeStem.setLocalTranslation(0, 0, 0);
        tree.attachChild(treeStem);
        
        
        //System.out.println("Tree Complete, attaching to node");
        tree.setLocalTranslation((randomInt(-80,80)), 0, (randomInt(-100, -65)));
        return tree;
    }
    
    private  int randomInt(int min, int max) {

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }
    

}
