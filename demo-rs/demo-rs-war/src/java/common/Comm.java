/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author il.lenskii
 */
public class Comm {
    
    public static final String pathToDump = "dump";
    public static final String serverUrl = "http://127.0.0.1:8080/";
    
    public static ArrayList<String> getAllFolderContent(Path dir) {
        File subFolder = new File(dir.toString());
        ArrayList<String> arrList = new ArrayList<>();

        File[] files = subFolder.listFiles();
        
        if(files == null || files.length < 1) {
            
            return arrList;
        }
        
        for(final File file : files) {

            arrList.add(file.getName() + " - " + (file.isDirectory() ? "dir" : "file"));
        }
        
        return arrList;
    }    

    public static ArrayList<String> findFiles(Path dir, String text) {
        File subFolder = new File(dir.toString());
        ArrayList<String> arrList = new ArrayList<>();

        File[] files = subFolder.listFiles((d, n) -> {

            return n.contains(text);
        });
        
        if(files == null || files.length < 1) {
            
            return arrList;
        }
        
        for(final File file : files) {

            arrList.add(file.getName() + " - " + (file.isDirectory() ? "dir" : "file"));
        }

        return arrList;    
        
    }
}
