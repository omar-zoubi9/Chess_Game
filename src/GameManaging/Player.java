package GameManaging;

public class Player {
    private final int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Player(int id) {
        this.id = id;
    }
}
