public class Talk extends Event{

    private String speaker;
    private String description;

    public Talk (String name, int room, String speaker) {
        super(name, room, 1);
        this.speaker = speaker;
        description = super.getDescription();
    }

    public Talk (String name, int room, String speaker, String description) {
        super(name, room, 1);
        this.speaker = speaker;
        this.description = description;
    }

    public String[] getSpeakers() {
        return new String[] {speaker};
    }

    public String getDescription() {
        return description;
    }
}
