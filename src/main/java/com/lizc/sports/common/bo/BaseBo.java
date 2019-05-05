package com.lizc.sports.common.bo;

/**bo接口
 * @author: lizc@sdhuijin.cn
 * @date: 2019-05-05 15:33
 **/
public interface BaseBo<T>
{
    /**
     * 通过po类，获取bo类
     * @return 获取的bo类
     */
    BaseBo generate(T t);

    /**
     * 获取po类
     * @return po类
     */
    T transform();
}
