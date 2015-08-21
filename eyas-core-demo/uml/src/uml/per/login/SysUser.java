package uml.per.login;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015-6-9.
 */
public class SysUser {
    private static final long serialVersionUID = 1L;
    private Integer id; 			// 用户id
    private String account; 		// 账号
    private String password; 		// 密码
    private String name; 			// 用户名
    private Integer deptId; 		// 部门id
    private String workNumber; 		// 工号
    private String phone; 			// 联系电话
    private String mobile; 			// 手机号码
    private String userEmail; 		// 用户邮箱
    private String internalEmail; 	// 内部邮箱
    private String state; 			// 用户状态[Y-正式用户 N-非正式用户 S-账号暂停]
    private Integer grade; 			// 等级[1-管理员 2-总监 3-经理 4-主管 5-一般人员]
    private String webSiteRight; 	// 分站点权限[例：1401,1402]
    private String lastLoginIp; 	// 上次登陆ip
    private String lastLoginHost; 	// 上次登陆主机地址
    private Date lastLoginDate; 	// 上次登陆日期
    private Date firstLoginDate; 	// 首次登陆日期
    private Long loginCount; 		// 登陆次数
    private Integer advisorFlag; 	// 是否顾问[1-是 0-否]
    private Integer score; 			// 分数
    private String fax; 			// 传真
    private String avatarPath; 		// 用户头像路径
    private String avatarName;		// 用户头像名称
    private Integer resumeNum; 		// 可下载简历数量
    private Integer usedNum; 		// 已下载简历数量
    private String globalCall; 		// 全国呼叫中心[默认95105333]
    private Date createDate; 		// 创建日期
    private String createBy; 		// 创建人
    private Date editDate; 			// 修改日期
    private String editBy; 			// 修改人

    private SysDepartment department; // 所属部门
    private List<SysRole> roleList = Lists.newArrayList(); // 拥有的角色列表
}
