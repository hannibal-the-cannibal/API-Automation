package PojoClasses;

import java.util.List;

public class Folder {
    private String name;
    List<RequestRoot> item;

    public Folder(String name, List<RequestRoot> requestroot) {
        this.name = name;
        this.item = requestroot;
    }

   public Folder(){

   }

    public String getName() {
        return name;
    }

    public void setItem(List<RequestRoot> item) {
        this.item = item;
    }

    public List<RequestRoot> getItem() {
        return item;
    }

    public void setName(String name) {
        this.name = name;
    }
}
