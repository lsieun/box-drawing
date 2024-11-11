package lsieun.box.ds;

public class Quad<A, B, C, D> {
    private final A first;
    private final B second;
    private final C third;
    private final D fourth;

    private Quad(A first, B second, C third, D fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public A first() {
        return first;
    }

    public B second() {
        return second;
    }

    public C third() {
        return third;
    }

    public D fourth() {
        return fourth;
    }

    public static <T, S, R, V> Quad<T, S, R, V> of(T first, S second, R third, V fourth) {
        return new Quad<T, S, R, V>(first, second, third, fourth);
    }
}
