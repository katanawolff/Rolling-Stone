/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.scene.Node;


/**
 *
 * @author panpa
 */
public class Player {
    private final InputManager inputManager;
    Node sphere;
    
    public Player(Node sphere, InputManager inputManager){
        this.sphere = sphere;
        this.inputManager = inputManager;
    }
    
    public void setUpKeys(){
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
    }
}
