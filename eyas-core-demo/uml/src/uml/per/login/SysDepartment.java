package uml.per.login;
import java.util.Date;
/**
 * Created by Administrator on 2015-6-9.
 */
public class SysDepartment {
    private static final long serialVersionUID = 1L;

    private Integer id; 				// 部门id
    private String name; 				// 部门名称
    private Integer parentId; 			// 父部门id
    private Integer deptType; 			// 部门性质[0-行政部门 1-业务部门]
    private Integer deptProperty; 		// 部门属性[10-大区 20-公司 30-部门 40-小组]
    private String descript;  			// 部门描述
    private Date createDate; 			// 创建日期
}
