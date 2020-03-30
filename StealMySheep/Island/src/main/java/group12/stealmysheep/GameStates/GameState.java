/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.GameStates;

import group12.stealmysheep.Manager.Manager;

/**
 *
 * @author Antonio
 */
public abstract class GameState {


	
	protected Manager gsm;
	
	protected GameState(Manager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public abstract void init();
	public abstract void update(float dt);
	public abstract void draw();
	public abstract void handleInput();
	public abstract void dispose();
	
}
