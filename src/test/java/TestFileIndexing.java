import com.gokhanabi.index.FolderSizeIndexer;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by Masraf2 on 12/6/2015.
 */
@RunWith(JMockit.class)
public class TestFileIndexing {


    @Test
    public void testIndexingSystemFolders(){
        boolean stuff = true;
        FolderSizeIndexer.getInstance().index();
        Map<String,Long> fileMap = FolderSizeIndexer.getInstance().getFileSizeIndexes();
        List<Long> fileSizes = new ArrayList<>();
        Map<Long,String> reverseMap = new HashMap<>();
        for (String s : fileMap.keySet()) {
            fileSizes.add(fileMap.get(s));
            reverseMap.put(fileMap.get(s), s);
        }
        Collections.sort(fileSizes);
        for (Long fileSize : fileSizes) {
            if(!fileSize.equals(0L)){
                System.out.println(fileSize + " ->" + reverseMap.get(fileSize) );
            }
        }
        assertTrue(stuff);
    }

}
