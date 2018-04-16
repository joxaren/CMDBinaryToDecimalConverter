
class NotABinaryNumberException extends RuntimeException {
    public NotABinaryNumberException () {
        super();
    }

    public NotABinaryNumberException (String s) {
        super(s);
    }
}