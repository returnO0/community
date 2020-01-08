package com.community.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * @author zhiqiang.hu01@hand-china.com
 * @date 2020-01-02 17:23
 **/
public enum CommentType implements IEnum {
    /**
     * type 类型
     * des 描述
     */
    QUESTION(1,"问题"),
    COMMENT(2,"评论");
    private Integer type;
    private String desc;

    CommentType(Integer type,String desc){
        this.type=type;
        this.desc=desc;
    }

    public static boolean isExist(Integer type) {
        for (CommentType commentType:CommentType.values()){
            if (commentType.getValue().equals(type)){
                return true;
            }
        }
        return false;
    }

    public String getDesc(){
        return desc;
    }

    @Override
    public Serializable getValue() {
        return this.type;
    }

    public Integer getType() {
        return this.type;
    }

    /**
     * 根据id 返回枚举
     * @param id 类型id
     * @return 枚举
     */
    public static CommentType parse(Integer id){
        for (CommentType commentType:CommentType.values()){
            if(commentType.getValue().equals(id)){
                return commentType;
            }
        }
        return null;
    }
}
