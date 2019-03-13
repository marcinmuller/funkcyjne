package main.java.pl.mmuller;

import java.util.function.Consumer;

public interface  Result<T> {
    void bind(Consumer<T> success, Consumer<String> failure);
    class Success<T> implements Result{
        private final T value;
        public Success(T value) {
            this.value = value;
        }
        @Override
        public void bind(Consumer s,Consumer f){
            s.accept(value);
        }
    }

    class Failure implements Result{
        private final String value;
        public Failure(String value) {
            this.value = value;
        }
        @Override
        public void bind(Consumer s, Consumer f){
            f.accept(value);
        }
    }

    static <T> Result<T> success(T value){
        return new Success<>(value);
    }

    static Result failure(String value){
        return new Failure(value);
    }
}
