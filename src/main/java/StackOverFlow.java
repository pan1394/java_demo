public class StackOverFlow{

    static int i = 0;
    public void overlap(){

        i++;
        overlap();
    }

    public static void main(String... args) {
        
        StackOverFlow sof = new StackOverFlow();
        try{
            sof.overlap();
        }catch(Throwable e){
            System.out.println("stack lenth:" + i); 
             
        }
    }

}