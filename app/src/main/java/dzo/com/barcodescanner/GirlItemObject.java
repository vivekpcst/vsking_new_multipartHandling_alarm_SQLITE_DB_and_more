package dzo.com.barcodescanner;

/**
 * Created by Vivek vsking on 1/19/2019.
 * vivekpcst.kumar@gmail.com
 */
public class GirlItemObject {

    String id,name,age,occu;
    public GirlItemObject(){}
    public GirlItemObject(String id,String name,String age,String occu){
        this.id=id;
        this.name=name;
        this.age=age;
        this.occu=occu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOccu() {
        return occu;
    }

    public void setOccu(String occu) {
        this.occu = occu;
    }
}
