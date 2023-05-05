package entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Permission)表实体类
 *
 * @author lsh
 * @since 2023-05-04 18:13:23
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission  {
    
    private Integer id;
    //权限名称
    private String permission;
    //权限描述
    private String desc;




}
