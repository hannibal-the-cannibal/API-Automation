package PojoClasses;

public class PojoclassRoot {
    Collection collection;

    public PojoclassRoot(){

    }
    public PojoclassRoot(Collection collection) {
        this.collection = collection;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
