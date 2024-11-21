package material;


public abstract class Material {
    protected String content;

    public Material() {
        this.content = loadContent();
    }

    protected abstract String loadContent();

    public void displayContent() {
        System.out.println("Content:\n" + content);
    }
}
