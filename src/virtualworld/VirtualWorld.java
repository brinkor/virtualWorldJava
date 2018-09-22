/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;

import virtualworld.world.World;

/**
 *
 * @author brink
 */
public class VirtualWorld {
    private int x, y;
    public static void main(String[] args) {
        World world = new World(15, 15);
        world.zyj();
    }
    
}
