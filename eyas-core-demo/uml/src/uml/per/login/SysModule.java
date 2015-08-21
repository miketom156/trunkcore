package uml.per.login;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Created by Administrator on 2015-6-9.
 */
public class SysModule {
    private static final long serialVersionUID = 1L;

    private Integer id;          // 模块ID
    private Integer parentId;    // 父模块ID
    private String name;         // 模块名称
    private Integer state;       // 模块状态[1-正常使用 0-禁止使用]
    private Integer orderBy;     // 模块排序
    private String descript;     // 模块描述
    private Date createDate; 	 // 创建日期
    private String createBy; 	 // 创建人
    private Date editDate; 	     // 修改日期
    private String editBy;       // 修改人

    private SysModule parentModule; // 父模块
    private List<SysModule> subModuleList = Lists.newArrayList();     // 子模块列表
    private List<SysOperate> operateList = Lists.newArrayList();      // 功能操作列表
    private List<SysDataRight> dataRightList = Lists.newArrayList();  // 模块对应数据权限列表
}
