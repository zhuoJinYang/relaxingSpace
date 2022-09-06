package com.space.job.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ScheduledStatus {
    ENABLE(0,"禁用"),
    DISABLE(1,"启用");

    private int code;
    private String name;

    public static ScheduledStatus getByCode(int code){
        for (ScheduledStatus st : values()) {
            if(code == st.getCode()) return st;
        }
        return null;
    }
}
