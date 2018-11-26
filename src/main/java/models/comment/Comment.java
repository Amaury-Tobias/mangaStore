package models.comment;

public class Comment {

    private int idItem;
    private String text;
    private String date;
    private String author;
    private int valoration;

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @return the valoration
     */
    public int getValoration() {
        return valoration;
    }

    /**
     * @param valoration the valoration to set
     */
    public void setValoration(int valoration) {
        this.valoration = valoration;
    }

    /**
     * @return the idItem
     */
    public int getIdItem() {
        return idItem;
    }

    /**
     * @param idItem the idItem to set
     */
    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
    
}