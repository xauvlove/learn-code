package com.xauv.concurrent.aqsImpl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private Helper helper = new Helper();

    private class Helper extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            if(state == 0) {
                //使用 CAS 修改 state
                if (compareAndSetState(0, arg)) {
                    //修改成功，设置当前线程占有资源
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
                //修改具有可重入性
            } else if(Thread.currentThread() == getExclusiveOwnerThread()) {
                setState(getState() + arg);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int state = getState() - arg;
            boolean flag = false;
            if(state == 0) {
                //如果 state = 0  说明其他线程可以占有该资源
                setExclusiveOwnerThread(null);
                return true;
            }
            // 这里不存在线程安全问题，因为释放前已经占有了资源
            setState(state);
            return false;
        }

        public Condition newConditionObject() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        helper.acquire(1);
    }

    /**
     * 以中断方式获取锁
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    /**
     * 多久没有获取锁就抛出异常
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    /**
     * 在某些条件下 进行加锁
     * @return
     */
    @Override
    public Condition newCondition() {
        return helper.newConditionObject();
    }
}
