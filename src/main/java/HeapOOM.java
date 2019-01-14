import java.util.ArrayList;
import java.util.List;

public class HeapOOM{
 
    public static void main(String... args) {

        Runnable r = () ->{
            List<OOM>  lst = new ArrayList<>();
            while(true){
                lst.add(new OOM());
            }
        };
        new Thread(r, "thread-xx").start();

        while(true){
            ;
        }
    }
 
}

class OOM{}