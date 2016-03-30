public class Key {
    private final int idx;

    public Key(int idx) {
        this.idx = idx;
    }

    public int index() {
        return idx;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Key val = (Key)o;

        return idx == val.idx;
    }

    @Override public int hashCode() {
        return idx;
    }
}
