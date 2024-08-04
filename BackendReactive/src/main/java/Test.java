//import io.smallrye.mutiny.Multi;
//
//import java.time.Duration;
//
//public class Test {
//    public static void main(String[] args) {
//        Multi.createFrom().ticks().every(Duration.ofMillis(1000))
//                .onItem().transform(i -> i * 11)
//                .onItem().transform(i-> i + "Test")
//                .subscribe().with(item -> System.out.println(item),
//                        faiure -> System.out.println(faiure.getMessage()),
//                        () -> System.out.println("Done"));
//
//    }
//}
