package uml.per.login;

import java.util.Date;

/**
 * Created by Administrator on 2015-6-9.
 */
public class SysDataRight {
    private static final long serialVersionUID = 1L;

    private Integer id; 			// 记录id
    private Integer roleId; 		// 角色id
    private Integer moduleId; 		// 模块id
    private String rightValue; 		// 数据权限值
    private Date createDate; 		// 创建日期
    private String createBy; 		// 创建人
    private Date editDate; 			// 修改日期
    private String editBy; 			// 修改人
    private SysRole role;           // 关联角色对象
    private SysModule module;       // 关联模块对象
}
