package uml.per.login;

/**
 * Created by Administrator on 2015-6-9.
 */
public class SysOperate {
    private static final long serialVersionUID = 1L;

    private Integer id; 			// 操作id
    private String name; 			// 操作名称
    private String url; 			// 操作url
    private Integer moduleId; 		// 绑定模块id
    private Integer displayFlag;	// 是否在菜单显示[0-否 1-是]
    private Integer pageType;		// 页面类型[1-page 2-frame 默认page]
    private String displayName; 	// 菜单显示名称
    private String descript; 		// 操作描述
    private Integer sort;			// 排序
    private SysModule module; 		// 绑定模块ok 恩
}
