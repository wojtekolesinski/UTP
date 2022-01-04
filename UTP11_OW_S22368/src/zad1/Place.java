package zad1;

public enum Place {
    SEE("see"),
    LAKE("lake"),
    MOUNTAINS("mountains");

    private final String name;

    Place(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
