package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.World;
import game.actors.friendlyactors.Toad;
import game.actors.players.Player;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.Lava;
import game.grounds.jumpablegrounds.Sprout;
import game.grounds.jumpablegrounds.Wall;
import game.grounds.jumpablegrounds.teleportablegrounds.WarpPipe;

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

        //placing some sprouts randomly all over the map2

        for(int i=0;i<35;i++){
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

        //placing some warp pipes randomly all over the map
        for(int i=0;i<15;i++){
            WarpPipe warpPipe = new WarpPipe(gameMap,lavaMap);
            //gives a random value from 0-80
            int x_random = rand.nextInt(80);
            //gives a random value from 0-18
            int y_random = rand.nextInt(18);

            Ground ground = gameMap.at(x_random,y_random).getGround();

            //checks if ground fertile
            if (ground.getDisplayChar()=='.'){
                gameMap.at(x_random,y_random).setGround(warpPipe);
            }
        }




        //placing some lava randomly all over the map
        for(int i=0;i<12;i++){
            Lava lava = new Lava();
            //gives a random value from 0-48
            int x_random = rand.nextInt(48);
            //gives a random value from 0-11
            int y_random = rand.nextInt(11);

            Ground ground = lavaMap.at(x_random,y_random).getGround();

            //checks if ground fertile
            if (ground.getDisplayChar()=='.'){
                lavaMap.at(x_random,y_random).setGround(lava);
            }
        }

        lavaMap.at(0,0).setGround(new WarpPipe(gameMap,lavaMap));

        Player mario = new Player("Player", 'm', 100);
        world.addPlayer(mario, gameMap.at(42, 10));

        gameMap.at(44, 11).addActor(new Toad());
        world.run();

    }
}
