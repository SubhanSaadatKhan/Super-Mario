package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.World;
import game.actors.enemies.Bowser;
import game.actors.friends.PrincessPeach;
import game.actors.friends.Toad;
import game.actors.players.Player;
import game.grounds.harmless.Dirt;
import game.grounds.harmless.Floor;
import game.grounds.harmful.Lava;
import game.grounds.harmless.fountains.HealthFountain;
import game.grounds.harmless.fountains.PowerFountain;
import game.grounds.harmless.jumpablegrounds.Sprout;
import game.grounds.harmless.jumpablegrounds.Wall;
import game.grounds.harmless.jumpablegrounds.teleportablegrounds.WarpPipe;

/**
 * The main class for the Mario World game.
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());

        List<String> map = Arrays.asList(
                "..........................................##..........+.........................",
                "............+............+..................#...................................",
                "............................................#...................................",
                ".............................................##......................+..........",
                "...............................................#................................",
                "................................................#...............................",
                ".................+................................#.............................",
                ".................................................##.............................",
                "................................................##..............................",
                ".........+..............................+#____####.................+............",
                ".......................................+#_____###++.............................",
                ".......................................+#______###..............................",
                "........................................+#_____###..............................",
                "........................+........................##.............+...............",
                "...................................................#............................",
                "....................................................#...........................",
                "...................+.................................#..........................",
                "......................................................#.........................",
                ".......................................................##.......................");



        List<String> map2 = Arrays.asList(
                "....+............................................",
                "............+............+.................._....",
                "....................##......................_....",
                ".................___.............................",
                ".......................#.......##..............#.",
                "................+................................",
                ".....#..........+.._........__....#..............",
                ".................#___##........___...............",
                "..+.....................................+......._",
                ".........+........#____#..........++............+",
                "___....................................+#_____##_",
                "............_#___......................+.........");



        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        GameMap lavaMap = new GameMap(groundFactory, map2);
        world.addGameMap(lavaMap);

        Random rand = new Random();

        //placing some sprouts randomly all over the GameMap
        for(int i=0;i<25;i++){
            Sprout sprout = new Sprout();
            //gives a random value from 0-80
            int x_random = rand.nextInt(80);
            //gives a random value from 0-18
            int y_random = rand.nextInt(18);

            Ground ground = gameMap.at(x_random,y_random).getGround();

            //checks if ground fertile
            if (ground.getDisplayChar()=='.'){
                gameMap.at(x_random,y_random).setGround(sprout);
            }
        }

        //placing some Warp Pipes randomly all over the GameMap
        for(int i=0;i<15;i++){
            WarpPipe warpPipe = new WarpPipe(gameMap,lavaMap);
            //gives a random value from 0-80
            int x_random = rand.nextInt(80);
            //gives a random value from 0-18
            int y_random = rand.nextInt(18);

            Ground ground = gameMap.at(x_random,y_random).getGround();
            if (ground.getDisplayChar()=='.'){
                gameMap.at(x_random,y_random).setGround(warpPipe);
            }
        }

        //placing some lava randomly all over the LavaMap
        for(int i=0;i<5;i++){
            Lava lava = new Lava();
            //gives a random value from 0-48
            int x_random = rand.nextInt(48);
            //gives a random value from 0-11
            int y_random = rand.nextInt(11);
            lavaMap.at(x_random,y_random).setGround(lava);

        }

        //placing a Warp Pipe in the top left corner of LavaMap
        lavaMap.at(0,0).setGround(new WarpPipe(gameMap,lavaMap));

        //todo still understand
        lavaMap.at(3,4).addActor(new Bowser());
        lavaMap.at(3,6).addActor(new PrincessPeach());

        Player mario = new Player("Player", 'm', 1000000);
        world.addPlayer(mario, gameMap.at(42, 10));

        gameMap.at(44, 11).addActor(new Toad());
        gameMap.at(41, 11).setGround(new HealthFountain());
        gameMap.at(44, 10).setGround(new PowerFountain());
        world.run();

    }
}
