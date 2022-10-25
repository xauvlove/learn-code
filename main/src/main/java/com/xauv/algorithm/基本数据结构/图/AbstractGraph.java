package com.xauv.algorithm.基本数据结构.图;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/10/25 20:20
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.图
 * @Desc
 */
public abstract class AbstractGraph<V, E> implements Graph<V, E> {

    WeightManager<E> weightManager;

    public AbstractGraph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }
}
