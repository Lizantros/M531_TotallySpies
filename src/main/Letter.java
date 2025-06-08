package main;

public class Letter extends Item {
    private String content;
    private boolean hasBeenRead;

    public Letter(String name, String description, String content) {
        super(name, description);
        this.content = content;
        this.hasBeenRead = false;
    }

    public String getContent() {
        return content;
    }

    public boolean hasBeenRead() {
        return hasBeenRead;
    }


    public void inspect() {
        System.out.println("You're reading very hard, you're locked in, you're focused !         " + getName() + "':");
        System.out.println("----------------------------------------");
        System.out.println(this.content);
        System.out.println("----------------------------------------");
        this.hasBeenRead = true;
    }
}