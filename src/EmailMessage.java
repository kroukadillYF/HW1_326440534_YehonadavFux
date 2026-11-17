import java.util.Date;
import java.util.ArrayList;

public class EmailMessage extends Message implements IDigital {
    private String subject;
    private ArrayList<File> attachments;

    public EmailMessage(String id, String sender, String content, Date sendDate, String subject, ArrayList<File> attachments) {
        super(id, sender, content, sendDate);
        setSubject(subject);
        this.attachments = attachments != null ? attachments : new ArrayList<>();
    }

    public EmailMessage(String id, String sender, String content, String subject) {
        this(id, sender, content, new Date(), subject, new ArrayList<>());
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Email subject cannot be null or empty.");
        }
        this.subject = subject;
    }

    public ArrayList<File> getAttachments() {
        return attachments;
    }

    public void addAttachment(File file) {
        if (file != null) {
            this.attachments.add(file);
        }
    }

    public void removeAttachment(File file) throws AttachmentException {
        boolean removed = this.attachments.removeIf(f -> f.getFileName().equals(file.getFileName()) && f.getFileType().equals(file.getFileType()));
        if (!removed) {
            throw new AttachmentException("Attachment not found in the email message.");
        }
    }

    @Override
    public String printCommunicationMethod() {
        return "Sent via Email Server";
    }

    @Override
    public String generatePreview() {
        return "[Email] Subject: " + subject + " | From: " + getSender();
    }

    @Override
    public String toString() {
        return "EmailMessage [" + super.toString() + ", Subject: " + subject + ", Attachments Count: " + attachments.size() + "]";
    }
}