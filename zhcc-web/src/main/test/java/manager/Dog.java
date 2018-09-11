package manager;

/**
 * Created by hulichao on 2018/7/10.
 */
public class Dog {
    String name;

    Dog(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    String getObjectAddress() {
        return super.toString();
    }

    public void setDog(String name){
        this.name = name;
    }
}