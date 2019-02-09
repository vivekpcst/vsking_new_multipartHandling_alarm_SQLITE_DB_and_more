package dzo.com.barcodescanner;

/**
 * Created by Vivek vsking on 1/30/2019.
 * vivekpcst.kumar@gmail.com
 */
public class HorItemModel {
    String image,title;
    public HorItemModel(String image,String title){
        this.image=image;
        this.title=title;
    }

    public HorItemModel() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
