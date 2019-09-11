package jdk.forkjoinpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;
import java.util.concurrent.RecursiveTask;

/**
 * @author guizhai
 *
 */
public class ForkJoinPoolTest {

	static final int MAX_CAP  = 0x7fff; 
	
	public static final ForkJoinWorkerThreadFactory
  defaultForkJoinWorkerThreadFactory  = null;
	
//	 static final class DefaultForkJoinWorkerThreadFactory
//   implements ForkJoinWorkerThreadFactory {
//   public final ForkJoinWorkerThread newThread(ForkJoinPool pool) {
//       return new ForkJoinWorkerThread(pool);
//   }
//}
	/**
	 * @param args
	 */
	
	//计算 π 的值有一个通过多项式方法，即：π = 4 * (1 - 1/3 + 1/5 - 1/7 + 1/9 - ……)，而且多项式的项数越多，计算出的 π 的值越精确
	static class PiEstimateTask extends RecursiveTask<Double> {
		private static final long serialVersionUID = 1L;
		private final long begin;
    private final long end;
    private final long threshold; // 分割任务的临界值

    public PiEstimateTask(long begin, long end, long threshold) {
        this.begin = begin;
        this.end = end;
        this.threshold = threshold;
    }

    @Override
    protected Double compute() {  // 实现 compute 方法
        if (end - begin <= threshold) {  // 临界值之下，不再分割，直接计算

            int sign; // 符号，多项式中偶数位取 1，奇数位取 -1（位置从 0 开始）
            double result = 0.0;
            for (long i = begin; i < end; i++) {
                sign = (i & 1) == 0 ? 1 : -1;
                result += sign / (i * 2.0 + 1);
            }
            System.out.println(Thread.currentThread()+ " From: "+ begin + " End: "+ end);
            return result * 4;
        }

        // 分割任务
        long middle = (begin + end) / 2;
        PiEstimateTask leftTask = new PiEstimateTask(begin, middle, threshold);
        PiEstimateTask rightTask = new PiEstimateTask(middle, end, threshold);

        leftTask.fork();  // 异步执行 leftTask
        rightTask.fork(); // 异步执行 rightTask

        double leftResult = leftTask.join();   // 阻塞，直到 leftTask 执行完毕返回结果
        double rightResult = rightTask.join(); // 阻塞，直到 rightTask 执行完毕返回结果

        return leftResult + rightResult; // 合并结果
    }

}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		PiEstimateTask task = new PiEstimateTask(0, 1_000_000_000, 10_000_00);
		pool.execute(task);
		System.out.println(task.get());
	}

}


