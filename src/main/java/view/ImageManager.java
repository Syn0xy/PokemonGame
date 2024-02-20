package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import model.util.BufferedWriter;

public class ImageManager {

    private static final String SERPARATOR = File.separator;

    private static final String IMAGE_PATH = "res" + SERPARATOR + "img" + SERPARATOR;

    private static final String POKEMON_PATH = "res" + SERPARATOR + "pokemon" + SERPARATOR;
    
    private static final String PATH_POKEMON_ANIMATION = "overworld.png";
    
    // private static final String PATH_POKEMON_ANIMATION_SHINY = "overworld-shiny.png";
    
    private final static String PATH_GRAPHISM = "diamond_pearl";
    
    private static Map<String, Image> pokemonImages;

    private static Map<String, Image> worldImages;

    public static Map<String, Image> getPokemonImages() {
        return pokemonImages;
    }

    public static Image get(String name){
        if(pokemonImages.containsKey(name)){
            return pokemonImages.get(name);
        }else if(worldImages.containsKey(name)){
            return worldImages.get(name);
        }
        System.out.println(name + ": null :'(");
        return null;
    }

    public static void init(BufferedWriter writer){
        pokemonImages = new HashMap<>();
        worldImages = new HashMap<>();

        try {
            initPokemon(writer, POKEMON_PATH);
            initWorldImages(writer, PATH_GRAPHISM);
        } catch (Exception e) {
            writer.println("Failed to load images: " + e.getMessage());
        }
    }

    private static void initPokemon(BufferedWriter writer, String path) throws IOException {
        File directory = new File(POKEMON_PATH);

        if(directory.exists() && directory.isDirectory()){
            String[] names = directory.list();
            int total = 0;
            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                File crntFile = new File(POKEMON_PATH + name + "/" + PATH_POKEMON_ANIMATION);
                if(crntFile.exists()){
                    pokemonImages.put(name, ImageIO.read(crntFile));
                    writer.println(i + ". pokemon load: " + name);
                    total++;
                }else{
                    writer.println(i + ". pokemon fail: " + name + " - est impossible à charger !");
                }
            }
            writer.println("Total: " + total + " pokemons loaded");
        }
    }
    
    public static void initWorldImages(BufferedWriter writer, String path) throws IOException {
        int total = 0;
        for (File file : getFiles(IMAGE_PATH + PATH_GRAPHISM + SERPARATOR)) {
            String fileName = file.getName();
            String name = fileName.substring(0, fileName.length() - 4);
            worldImages.put(name, ImageIO.read(file));
            writer.println(total + ". world load: " + name);
            total++;
        }
    }

    private static List<File> getFiles(String path){
        return getFiles(new ArrayList<>(), path);
    }

    private static List<File> getFiles(List<File> list, String path){
        File file = new File(path);

        if(file.isDirectory()){
            for(File f : file.listFiles()){
                getFiles(list, path + SERPARATOR + f.getName());
            }
        }else if(isPNG(file)){
            list.add(file);
        }
        
        return list;
    }

    private static boolean isPNG(File file){
        return file.isFile() && file.getName().endsWith(".png");
    }
}
