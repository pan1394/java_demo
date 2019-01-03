package animal;


public class Bird implements IFly{

    @Override
    public void fly() {
        System.out.println("I can fly");
        System.out.println("yes, I can!");
    }
}