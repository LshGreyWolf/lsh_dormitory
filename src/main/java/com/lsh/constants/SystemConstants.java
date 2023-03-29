package com.lsh.constants;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/06
 */
public class SystemConstants {
    /**
     * 文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     * 文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    /**
     * 文章分类为正常状态
     */
    public static final int CATEGORY_STATUS_NORMAL = 0;
    /**
     * 文章分类为禁用状态
     */
    public static final int CATEGORY_STATUS_DRAFT = 1;
    /**
     * 友链
     */
    public static final int LINK_STATUS_DRAFT = 1;
    public static final int LINK_STATUS_NORMAL = 0;

    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友链评论
     */
    public static final String LINK_COMMENT = "1";

}