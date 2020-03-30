package group12.stealmysheep.Manager;
import group12.stealmysheep.GameStates.GameState;
import group12.stealmysheep.GameStates.PlayState;
import group12.stealmysheep.common.game.Input;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antonio
 */
public class Manager {
    
	
	// current game state
	private GameState gameState;
	
	public static final int MENU = 0;
	public static final int PLAY = 893746;
	
	public Manager() {
		setState(PLAY);
	}
	
	public void setState(int state) {
		if(gameState != null) gameState.dispose();
		if(state == MENU) {
			// gameState = new MenuState(this);
		}
		if(state == PLAY) {
			gameState = new PlayState(this);
		}
	}
	
	public void update(float dt) {
		gameState.update(dt);
	}
	
	public void draw() {
		gameState.draw();
	}
	
}

