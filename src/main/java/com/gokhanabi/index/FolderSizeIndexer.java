package com.gokhanabi.index;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Masraf2 on 12/6/2015.
 */
public class FolderSizeIndexer {

    private static FolderSizeIndexer instance;
    private Map<String,Long> folderSizes;
    private Logger logger = Logger.getLogger(this.getClass());


    private FolderSizeIndexer(){
        folderSizes = new HashMap<>();
    }


    public static synchronized FolderSizeIndexer getInstance(){
        if(instance==null)
            instance = new FolderSizeIndexer();
        return instance;
    }

    /**
     * Creates index of fileSizes for each folder.
     */
    public void index(){

        File[] roots =File.listRoots();
        for (int i = 0; i < roots.length; i++) {
            File root = roots[i];
           try{
            readSizes(root.listFiles());
           }catch(Throwable t){
               logger.error("Failed to read contents of the file.",t);
           }
        }
    }

    private void readSizes(File[] root) {
        if(root == null)
            return;
        for (int i = 0; i < root.length; i++) {
            File file = root[i];
            if(file.isDirectory()){
                Long size = file.length();
                folderSizes.put(file.getAbsolutePath(),size);
                readSizes(file.listFiles());
            }
        }
    }

    public Map<String,Long> getFileSizeIndexes(){
        return folderSizes;
    }


}
