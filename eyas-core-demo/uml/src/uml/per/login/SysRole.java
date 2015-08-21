package uml.per.login;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Created by Administrator on 2015-6-9.
 */
public class SysRole {
    private static final long serialVersionUID = 1L;
    private Integer id; 			// 角色id
    private String name; 			// 角色名称
    private String descript; 		// 角色描述
    private List<SysUser> userList = Lists.newArrayList();          //用户此角色的用户列表
    private List<SysOperate> operateList = Lists.newArrayList();	//权限操作列表
    private List<SysDataRight> dataRightList = Lists.newArrayList();//数据权限列表
}
