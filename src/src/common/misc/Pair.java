package common.misc;

import java.util.Objects;

public class Pair<T1, T2> {
    private T1 _first;
    private T2 _second;

    public Pair(T1 first, T2 second) {
        _first = first;
        _second = second;
    }

    public T1 getFirst() {
        return _first;
    }

    public T2 getSecond() {
        return _second;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Pair p = (Pair) o;
        if (p.getFirst().getClass() == this._first.getClass() && p.getSecond().getClass() == this._second.getClass()) {
            return Objects.equals(_first, p.getFirst()) && Objects.equals(_second, p.getSecond());
        }
        return false;
    }
}
