import java.util.Date;

public class BoardMessage extends Message {
    public enum Priority {
        REGULAR, URGENT, CRITICAL
    }

    private Priority priority;
    private String boardCategory;

    public BoardMessage(String id, String sender, String content, Date sendDate, Priority priority, String boardCategory) {
        super(id, sender, content, sendDate);
        setPriority(priority);
        setBoardCategory(boardCategory);
    }

    public BoardMessage(String id, String sender, String content, Priority priority, String boardCategory) {
        this(id, sender, content, new Date(), priority, boardCategory);
    }

    public BoardMessage(String id, String sender, String content, String boardCategory) {
        this(id, sender, content, new Date(), Priority.REGULAR, boardCategory);
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null.");
        }
        this.priority = priority;
    }

    public String getBoardCategory() {
        return boardCategory;
    }

    public void setBoardCategory(String boardCategory) {
        if (boardCategory == null || boardCategory.trim().isEmpty()) {
            throw new IllegalArgumentException("Board category cannot be null or empty.");
        }
        this.boardCategory = boardCategory;
    }

    @Override
    public String generatePreview() {
        String content = getContent();
        String truncatedContent = content.length() > 15 ? content.substring(0, 15) + "..." : content;
        return "[Board] " + getSender() + ": " + truncatedContent;
    }

    @Override
    public String toString() {
        return "BoardMessage [" + super.toString() + ", Priority: " + priority + ", Category: " + boardCategory + "]";
    }
}