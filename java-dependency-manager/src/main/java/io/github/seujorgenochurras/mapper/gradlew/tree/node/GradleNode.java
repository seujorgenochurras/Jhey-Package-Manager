package io.github.seujorgenochurras.mapper.gradlew.tree.node;

public class GradleNode {
    private String textContents;
    private int linePosition;

    public String getTextContents() {
        return textContents;
    }

    public void setTextContents(String textContents) {
        this.textContents = textContents;
    }

    public int getLinePosition() {
        return linePosition;
    }

    public void setLinePosition(int linePosition) {
        this.linePosition = linePosition;
    }

    public GradleNode(String textContents, int linePosition) {
        this.textContents = textContents;
        this.linePosition = linePosition;
    }

    public GradleNode(String textContents) {
        this.textContents = textContents;
    }
}
