package card;

public abstract class AbstractCard {
    private String name;
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Chancekort: " + name + "\n"
                + description;
    }
}


