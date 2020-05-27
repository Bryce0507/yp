package com.yp.common.constant;

/**
 * @Classname MenuConstant
 * @Description 菜单常量
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-04-29 19:49
 * @Version 1.0
 */
public class ActivityStatusConstant {

    /**
     * 菜单类型
     */
    public enum ActivityStatus {
        /**
         * 未发布
         */
        TODO("0"),
        /**
         * 进行中
         */
        ONGOIND("1"),
        /**
         * 已结束
         */
        END("2");

        private String value;

        ActivityStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
