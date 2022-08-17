public enum Operation  {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String operation;

    Operation(String title) {
        this.operation = title;
    }

    public String getCh() {
        return operation;
    }
}