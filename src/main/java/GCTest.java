 
public class GCTest{

    private static final int _1MB = 1024 * 1024;

    /**
     * VM args: -Xmx20m -Xmn10m -verbose:gc -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC/-XX:+UseSerialGC
     */
    public static void testAllocation(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB * 2];
        allocation2 = new byte[_1MB * 2];
        allocation3 = new byte[_1MB * 2];
        allocation4 = new byte[_1MB * 4]; 
    }

    /**
     * VM args: -Xmx20m -Xms2m -Xmn10m -verbose:gc -XX:+PrintGCDetails -XX:+UseSerialGC
     */
    public static void testPretenureSizeThreshold(){
        byte[] allocation = new byte[_1MB * 4];
    }

    public static void main(String[] args){
        //testAllocation();
        testPretenureSizeThreshold();
    }
}