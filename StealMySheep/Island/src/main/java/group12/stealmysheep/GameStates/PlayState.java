/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.GameStates;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import group12.stealmysheep.Manager.Manager;
import group12.stealmysheep.common.assets.Player;
import group12.stealmysheep.common.game.Input;
/**
 *
 * @author Antonio
 */
public class PlayState extends GameState {

    private ShapeRenderer sr;
	
	private Player player;
        private Input GameKey; 
	
	public PlayState(Manager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		sr = new ShapeRenderer();
		
		player = new Player();
		
	}
	
	public void update(float dt) {
		
		handleInput();
		
		player.update(dt);
		
	}
	
	public void draw() {
		player.draw(sr);
	}
	
	public void handleInput() {
		player.setLeft(GameKey.isDown(GameKey.LEFT));
		player.setRight(GameKey.isDown(GameKey.RIGHT));
		player.setUp(GameKey.isDown(GameKey.UP));
	}
	
	public void dispose() {}
	
}
