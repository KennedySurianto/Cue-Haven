package main.model;

public class BoxModel {
    private String labelText;
    private boolean isOpen;

    public BoxModel(String labelText) {
        this.labelText = labelText;
        this.isOpen = false;
    }

    public String getLabelText() {
        return labelText;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
