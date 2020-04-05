/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;


/**
 *
 * @author panpa
 */
public class Player implements ActionListener{
    
    private final InputManager inputManager;
    Node sphere;
    boolean isRunning = true;
    float speed;
    boolean left = false;
    boolean right = false;
    
    public Player(Node sphere, InputManager inputManager){
        this.sphere = sphere;
        this.inputManager = inputManager;
        speed = 10;
        setUpKeys();
    }
    
    private void setUpKeys(){
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this, "Pause", "Right", "Left");
        System.out.println("Key mappings set.");
    }
    
    
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("Pause") && !isPressed) {
            isRunning = !isRunning;
            System.out.println("Game Paused");
        }
        if (name.equals("Right")) {
            right = isPressed;
        }
        if(name.equals("Left")){
            left = isPressed;
        }
    }
    
    public void update(float tpf){
        float speed = 10;
        
        if(right){
            Vector3f v = sphere.getLocalTranslation();
            //sphere.setLocalTranslation(v.x + tpf * speed, v.y, v.z);
            sphere.move(1 * tpf * speed, 0, 0);
        }
        else if(left){
            Vector3f v = sphere.getLocalTranslation();
            //sphere.setLocalTranslation(v.x - tpf * speed, v.y, v.z);
            sphere.move(-1 * tpf * speed, 0, 0);
        }
    }
}
