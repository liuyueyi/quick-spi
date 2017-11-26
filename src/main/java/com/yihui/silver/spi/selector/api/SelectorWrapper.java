package com.yihui.silver.spi.selector.api;

import lombok.*;

/**
 * Created by yihui on 2017/5/25.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SelectorWrapper {

    /**
     * 具体的选择器
     */
    private ISelector selector;


    /**
     * 具体选择器对应的条件类型
     */
    private Class conditionType;

}
