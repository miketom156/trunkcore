package com.job5156.core.srv.com;

import com.job5156.core.common.Constants;
import com.job5156.core.common.util.KeyMaskUtil;
import com.job5156.core.eao.com.ComPositionEao;
import com.job5156.core.eao.com.ComPositionSlaveEao;
import com.job5156.webapp.model.com.ComContact;
import com.job5156.webapp.model.com.ComContactBase;
import com.job5156.webapp.model.com.ComPosition;
import com.job5156.core.srv.base.BaseSrv;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

@Service
@Transactional(value="transactionManager")
public class ComPositionSrv extends BaseSrv<ComPosition,Integer> {
	@Resource
	private ComPositionEao comPositionEao;

	@Resource
	private ComPositionSlaveEao comPositionSlaveEao;

    @Override
    protected void initBaseHibernateEao() {
        baseHibernateEao = comPositionEao;
    }

    /**
	 * 排序方式
	* @ClassName: SortType 
	* @Description: 列表的排序方式（更新时间、部门名称）
	* @author xh 
	* @date 2013-7-10 上午9:42:28 
	*
	 */
	public enum SortType {
		/**
		 * 按照刷新时间排序
		 */
		REFRESHDATE(-1,"refreshDate", "DESC"),

		/**
		 * 按照更新时间排序
		 */
		UPDATEDATE(Constants.SORT_BY_UPDATETIME,"updateDate", "DESC"),

		/**
		 * 按照部门名称排序
		 */
		DEPARTMENT(-2,"comDepartment.deptName", "DESC"),

		/**
		 * 停止招聘列表中的排序规则（需求为如果为停止招聘列表，则需要按照（过期招聘、停止招聘、已删除）排序）
		 */
		STOPORDER(-3,"posStatus", "ASC"),

        /**
         * 按急聘排序
         */
        URGENT(Constants.SORT_BY_URGENT,"urgent","DESC") ,

        /**
         * 按热度（查看数）排序
         */
        VIEWCOUNT(Constants.SORT_BY_HOT,"viewCount","DESC"),

        /**
         * 按薪资排序
         */
        SALARY(Constants.SORT_BY_SALARY,"salary","DESC");


        private int sortKey;
        private String field;
        private String order;

	    private SortType(int sortKey,String field, String order) {
            this.sortKey = sortKey; //排序号（此项值，对应搜索引擎的排序类型编号,无则设置为负值）
	        this.field = field;
	        this.order = order;
	    }

        public static String getFiledBySortKey(int sortKey){
            for(SortType type : values()) {
                if(type.getSortKey() == sortKey) return type.getField();
            }
            return "";
        }


		public String getField() {
			return field;
		}

		public String getOrder() {
			return order;
		}

        public int getSortKey() {
            return sortKey;
        }

        public void setSortKey(int sortKey) {
            this.sortKey = sortKey;
        }
    }
	
	/**
	 * 
	* @ClassName: SynType 
	* @Description: 同步类型
	* @author xh 
	* @date 2013-7-24 下午3:56:03 
	*
	 */
	public enum SynType {
		/**
		 * 同步更新到所有职位
		 */
		ALL(0),
		/**
		 * 同步更新到本部门所有职位
		 */
		DEPT(1);
		
		private int value;
		 
	    private SynType(int value) {
	        this.value = value;
	    }
	    
	    public int getValue() {
	        return value;
	    }
	}

    public ComPosition findByPosNo(Integer posNo) {
        Integer posId = KeyMaskUtil.posNoToIdAndType(posNo)[0];
        return comPositionEao.get(posId);
    }


	/**
	 * 根据comIds获取最多100条职位信息
	 * 主要用于个人中心的企业动态数据获取
	 * @param comIds
	 * @return
	 */
	@Transactional(value = "transactionManagerSystemQuery", readOnly = true)
	public List<ComPosition> getPosListByComId(List<Integer> comIds) {
		return comPositionSlaveEao.getPosListByComId(comIds);
	}
	
	/**
	 * 查询某个企业所发布的所有职位
	 * 
	 * @Title: getAllComPositionByComId
	 * @Description: 根据企业id查询出该企业所有职位
	 * @param @param comId
	 * @param @return 设定文件
	 * @return List<ComPosition> 返回类型
	 * @throws
	 */
	public List<ComPosition> getAllComPositionByComId(Integer comId) {
		return comPositionEao.getAllComPositionByComId(comId);
	}
    
    /**
     * 查询某个企业联系人关联的职位
     * 
     * @param comId 企业ID
     * @param contactId 企业联系人ID
     * @return
     */
    public List<ComPosition> getComListByContact(Integer comId, Integer contactId){
    	return comPositionEao.findComListByContact(comId, contactId);
    }
    
    /**
     * 批量更新职位联系人信息
     * @param comContact  联系人
     * @param positions 需要更新的职位
     * @return
     */
    public boolean updatePosContactList(ComContact comContact, List<ComPosition> positions){
        try {
            for (ComPosition comPosition : positions){
                if(comPosition==null){continue;}
                comPosition.setContactId(comContact.getId());
                ComContactBase base=new ComContactBase();
                BeanUtils.copyProperties(comContact.getComContactBase(),base);
                comPosition.setComContactBase(base);
                comPositionEao.saveOrUpdate(comPosition);
            }
        }catch (Exception ex){
            return  false;
        }

        return  true;
    }
    
	/**
	 * 根据ID获取职位信息
	 * 
	 * @param id
	 * @return
	 */
	public ComPosition findById(Integer id) {
		return comPositionEao.get(id);
	}
    
}
