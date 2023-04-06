package com.lsh.domain;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (NoticeReceive)表实体类
 *
 * @author lsh
 * @since 2023-03-28 17:31:08
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeReceive  extends Page{
    
    private Integer id;
    
    private Integer noticeId;
    
    private Integer buildingId;





}
