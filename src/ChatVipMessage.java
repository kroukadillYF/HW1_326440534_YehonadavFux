import java.util.Date;

class VipLevelException extends RuntimeException {
    public VipLevelException(String message) {
        super(message);
    }
}

public class ChatVipMessage extends Message implements IDigital {
    private int vipLevel;
    private boolean isEncrypted;

    public ChatVipMessage(String id, String sender, String content, Date sendDate, int vipLevel, boolean isEncrypted) {
        super(id, sender, content, sendDate);
        setVipLevel(vipLevel);
        this.isEncrypted = isEncrypted;
    }

    public ChatVipMessage(String id, String sender, String content, int vipLevel, boolean isEncrypted) {
        super(id, sender, content, new Date());
        setVipLevel(vipLevel);
        this.isEncrypted = isEncrypted;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        if (vipLevel < 1 || vipLevel > 5) {
            throw new VipLevelException("Invalid VIP Level. Must be between 1 and 5.");
        }
        this.vipLevel = vipLevel;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(boolean encrypted) {
        isEncrypted = encrypted;
    }

    @Override
    public String printCommunicationMethod() {
        return "Sent via Secure Encrypted Chat Gateway";
    }

    @Override
    public String generatePreview() {
        return "[VIP Chat (Level " + vipLevel + ")] " + getSender() + ": " + getContent();
    }

    @Override
    public String toString() {
        return "ChatVipMessage [" + super.toString() + ", VIP Level: " + vipLevel + ", Encrypted: " + isEncrypted + "]";
    }
}