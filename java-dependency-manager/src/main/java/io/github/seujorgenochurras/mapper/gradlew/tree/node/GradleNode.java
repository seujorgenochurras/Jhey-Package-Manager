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

    @Override
    public String toString() {
        return "GradleNode{" +
                "textContents='" + textContents + '\'' +
                ", linePosition=" + linePosition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradleNode that)) return false;

        if (getLinePosition() != that.getLinePosition()) return false;
        return getTextContents() != null ? getTextContents().equals(that.getTextContents()) : that.getTextContents() == null;
    }

    @Override
    public int hashCode() {
        int result = getTextContents() != null ? getTextContents().hashCode() : 0;
        result = 31 * result + getLinePosition();
        return result;
    }
}
