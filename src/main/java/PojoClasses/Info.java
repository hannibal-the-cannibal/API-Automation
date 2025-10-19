package PojoClasses;

public class Info {
    private String name;
    private  String desc;
    private  String schema;

    public Info(){

    }
    public Info(String name, String desc, String schema){
        this.name=name;
        this.desc=desc;
        this.schema=schema;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getDesc() {
        return desc;
    }

    public String getSchema() {
        return schema;
    }
}
