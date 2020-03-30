/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.island;

/**
 *
 * @author Antonio
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
public class Main {
	
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration cfg =
			new LwjglApplicationConfiguration();
		cfg.title = "StealMySheep";
		cfg.width = 500;
		cfg.height = 400;
		cfg.useGL30 = false;
		cfg.resizable = false;
		
		new LwjglApplication(new Island(), cfg);
		
	}
	
}
