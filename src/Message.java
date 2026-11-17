import java.util.Date;
import java.util.ArrayList;

public abstract class Message {
    private String id;  
    private String sender;
    private String content;
    private Date sendDate;

    public Message(String id, String sender, String content, Date sendDate) {
        setId(id);
        setSender(sender);
        setContent(content);
        setSendDate(sendDate);
    }

    public Message(String id, String sender, String content) {
        this(id, sender, content, new Date());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Message ID cannot be null or empty.");
        }
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        if (sender == null || sender.trim().isEmpty()) {
            throw new IllegalArgumentException("Sender name cannot be null or empty.");
        }
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty.");
        }
        this.content = content;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        if (sendDate == null) {
            throw new IllegalArgumentException("Send date cannot be null.");
        }
        this.sendDate = sendDate;
    }

    public boolean find(ArrayList<String> words) {
        if (words == null || words.isEmpty()) {
            return false;
        }
        for (String word : words) {
            if (word != null && !word.isEmpty() && this.content.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public int getContentLength() {
        return this.content.length();
    }

    public abstract String generatePreview();

    @Override
    public String toString() {
        return "ID: " + id + ", Sender: " + sender + ", Send Date: " + sendDate + ", Content: " + content;
    }
}