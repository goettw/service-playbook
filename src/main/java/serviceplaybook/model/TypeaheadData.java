package serviceplaybook.model;

public class TypeaheadData {
private String id, name;
private Object payload;

public Object getPayload() {
    return payload;
}

public void setPayload(Object payload) {
    this.payload = payload;
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
}
