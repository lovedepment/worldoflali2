public enum Direction {
    
    ESZAK("észak"),
    DEL("dél"),
    NYUGATi("nyugat"),
    KELET("kelet");

    private final String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return direction;
    }
}
