package io.github.seujorgenochurras.mapper.gradlew.tree.node;

public class GradleNode {
    private String textContents;

    public String getTextContents() {
        return textContents;
    }

    public void setTextContents(String textContents) {
        this.textContents = textContents;
    }

    public GradleNode(String textContents ) {
        this.textContents = textContents;
    }

    @Override
    public String toString() {
        return "GradleNode{" +
                "textContents='" + textContents + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradleNode that)) return false;

        return getTextContents() != null ? getTextContents().equals(that.getTextContents()) : that.getTextContents() == null;
    }

    @Override
    public int hashCode() {
        return getTextContents() != null ? getTextContents().hashCode() : 0;
    }
}
