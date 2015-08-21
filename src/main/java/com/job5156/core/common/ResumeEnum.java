package com.job5156.core.common;

/**
 * 简历枚举常量配置
 *
 * @author leo
 *
 */
public class ResumeEnum {
    /**
     * <p>
     * 搜索模块简历类型<br>
     * 主要用于简历同步
     * </p>
     *
     * @author leo
     *
     */
    public enum SearchResumeType {
		ALL((short) 0, "所有"), COMMON((short) 100, "普通简历"), CARD((short) 101, "求职名片");

        private Short value;
        private String descript;

        public Short getValue() {
            return value;
        }

        public String getDescript() {
            return descript;
        }

        private SearchResumeType(Short value, String descript) {
            this.value = value;
            this.descript = descript;
        }
    }

    /**
     * <p>
     * 求职状态
     * </p>
     *
     * @author leo
     *
     */
    public enum JobStatus {
        LOOKING_FOR_JOB(0, "正在找工作"),
        NO_PLAN_WITHIN_HALF_YEAR(1, "半年内无计划"),
        NO_PLAN_WITHIN_ONE_YEAR(2, "一年内无计划"),
        WATTING_FOR(3, "观望"),
        NO_INTENT_CURRENTLY(4, "我暂时不想找工作");

        private Integer value;
        private String descript;

        public Integer getValue() {
            return value;
        }

        public String getDescript() {
            return descript;
        }

        private JobStatus(Integer value, String descript){
            this.value = value;
            this.descript = descript;
        }
    }
}
