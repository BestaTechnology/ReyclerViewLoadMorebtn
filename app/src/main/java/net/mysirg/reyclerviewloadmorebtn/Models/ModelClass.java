package net.mysirg.reyclerviewloadmorebtn.Models;

import android.support.v7.widget.RecyclerView;

public class ModelClass  {

    int image;
    String title;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ModelClass(int image, String title) {
        this.image = image;
        this.title = title;
    }
}
