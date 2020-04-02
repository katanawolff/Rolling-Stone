/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
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
        inputManager.addListener(analogListener, "Right", "Left");
        inputManager.addListener(this, "Pause");
        System.out.println("Key mappings set.");
    }
    
       @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("Pause") && !isPressed) {
                isRunning = !isRunning;
                System.out.println("Game Paused");
            }
    }

    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf){
            if(isRunning){
                if (name.equals("Right")) {
                    Vector3f v = sphere.getLocalTranslation();
                    sphere.setLocalTranslation(v.x + value * speed, v.y, v.z);
                    System.out.println("You pressed right.");
                }
                if(name.equals("Left")){
                    Vector3f v = sphere.getLocalTranslation();
                    sphere.setLocalTranslation(v.x - value * speed, v.y, v.z);
                    System.out.println("You pressed left.");
                }
            }
        }
    };
}
