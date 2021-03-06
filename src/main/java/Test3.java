import java.util.concurrent.atomic.AtomicBoolean;

public class Test3 extends AbstractTest {
    private static volatile AtomicBoolean val = new AtomicBoolean();

    public static void main(String... args) throws InterruptedException {
        for (int i = 0; i < 10_000; i++) {
            assert size() == 0;

            Thread th1 = th();
            Thread th2 = th();
            Thread th3 = th();

            th1.start();
            th2.start();
            th3.start();

            th1.join();
            th2.join();
            th3.join();
            assert size() == 3;
            assert containsKey(1);
            assert containsKey(2);
            assert containsKey(3);
            assert contains(th1);
            assert contains(th2);
            assert contains(th3);

            clear();

            System.out.println("Checked " + i);
        }
    }


    private static Thread th() {
        return new Thread(() -> {
            // Правки можно внисить от этой линии
            AtomicBoolean local = val;

            if (local.compareAndSet(false, true)) {
                while(val != null) {
                }
                if (local.compareAndSet(true, false)) {
                    put(1);
                    return;
                }
                put(3);
                val = local;
                return;
            }

            if (local.compareAndSet(true,  false)) {
                while(!local.get()) {
                }
                val = null;
                put(2);
                return;
            }

            local.set(true);
            put(3);
            while (local.get()) {
            }
            val = local;
            // До этой
        });
    }
}
