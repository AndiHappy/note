package redissource.lock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import redis.clients.jedis.Jedis;

public abstract class RedisLock implements Lock {

    protected Jedis jedis;
    protected String lockKey;
    protected String lockValue;
    protected volatile boolean isOpenExpirationRenewal = true;


    public RedisLock(Jedis jedis,String lockKey) {
       this.jedis = jedis;
       this.lockKey = lockKey;
    }

    public RedisLock(Jedis jedis,String lockKey,String lockKeyValue) {
    	 this.jedis = jedis;
       this.lockKey = lockKey;
       this.lockValue = UUID.randomUUID().toString()+Thread.currentThread().getId();
  }

    
    /**
     * 开启定时刷新
     */
    protected void scheduleExpirationRenewal(){
        Thread renewalThread = new Thread(new ExpirationRenewal());
        renewalThread.start();
    }

    /**
     * 刷新key的过期时间
     */
    private class ExpirationRenewal implements Runnable{
        @Override
        public void run() {
            while (isOpenExpirationRenewal){
                System.out.println("执行延迟失效时间中...");

                String checkAndExpireScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                        "return redis.call('expire',KEYS[1],ARGV[2]) " +
                        "else " +
                        "return 0 end";
                jedis.eval(checkAndExpireScript, 1, lockKey, lockValue, "30");

                //休眠10秒
                sleepBySencond(10);
            }
        }
    }


    public void sleepBySencond(int sencond){
        try {
            Thread.sleep(sencond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void lockInterruptibly(){}

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit){
        return false;
    }

}