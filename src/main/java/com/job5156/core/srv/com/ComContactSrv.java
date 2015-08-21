/**
 * 
 */
package com.job5156.core.srv.com;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.job5156.core.bo.mapping.AddressBo;
import com.job5156.core.bo.mapping.ComContactBo;
import com.job5156.core.common.CommonEnum.CopyType;
import com.job5156.core.common.annotation.Log;
import com.job5156.core.common.annotation.Log.AccountType;
import com.job5156.core.common.annotation.Log.LogModule;
import com.job5156.core.common.util.JsonValidator;
import com.job5156.core.eao.com.ComContactEao;
import com.job5156.core.eao.com.ComPositionEao;
import com.job5156.core.eao.com.ComUserEao;
import com.job5156.core.srv.base.BaseSrv;
import com.job5156.core.srv.base.ModelVoConvertSrv;
import com.job5156.webapp.model.com.ComContact;
import com.job5156.webapp.model.com.ComContactBase;
import com.job5156.webapp.model.com.ComPosition;
import com.job5156.webapp.model.com.ComUser;

/**
 * @author lyh
 * @Description 
 * @date 2015年8月11日
 * 
 */
@Service
@Transactional(value="transactionManager")
public class ComContactSrv extends ModelVoConvertSrv<ComContact, ComContactBo> {

	
	@Resource 
	private ComContactEao comContactEao;
	
	@Resource
	private ComUserEao comUserEao;
	@Resource
	private ComPositionSrv comPositionSrv;
	@Resource
	private ComPositionEao comPositionEao;
	
	public ComContact findById(Integer id) {
		return comContactEao.get(id) ;
	}
	
	public ComContact findDefaultComContact(Integer comId){
		return comContactEao.getDefaultComContact(comId);
	}

    public String findDefaultComEmail(Integer comId) {
        ComContact comContact = findDefaultComContact(comId);
        return findDefaultComEmail(comContact);
    }

    public String findDefaultComEmail(ComContact comContact) {
        if(comContact != null && comContact.getComContactBase() != null && StringUtils.isNotBlank(comContact.getComContactBase().getEmail())) {
            String email = comContact.getComContactBase().getEmail();
            if(!StringUtils.equals("[]",email)) {
                Gson gson = new Gson();
                List<String> emails = gson.fromJson(email, new TypeToken<List<String>>() {}.getType());
                if(CollectionUtils.isNotEmpty(emails)) {
                    return emails.get(0);
                }
            }
        }
        return "";
    }
	
	public List<ComContact> getComContactList(Integer comId) {

		return  comContactEao.getComContactList(comId);
	}
	
	public ComContact getComContact(String comName, String email) {
		return  comContactEao.getComContact(comName, email);
	}
	
	@Log(module=LogModule.COM_INFO_MANGER, userType=AccountType.COM, desc="企业删除联系方式")
	public void delete(ComContact comContact){
		comContact.setDelStatus(-1);
        comContact.setUpdateDate(DateTime.now().toDate());
        comContactEao.update(comContact);
	}
	
	@Log(module=LogModule.COM_INFO_MANGER, userType=AccountType.COM, desc="企业修改联系方式")
	public void update(ComContactBo ComContactBo, ComContact comContact){
     	
		ComContactBo.setUpdateDate(DateTime.now().toDate());
     	copyProperties(comContact, ComContactBo,new String[]{"id","comId","defaultFlag","createDate"}, CopyType.VO_TO_MODEL);
     	//判断是否为默认联系人，如果为默认联系人则将关联的企业location设置给默认联系人（前台需要）
		if(comContact.getDefaultFlag() == 1)
			ComContactBo.setLocation(comContact.getComInfo().getLocation().intValue());
     	comContactEao.update(comContact);
	}
	
	@Log(module=LogModule.COM_INFO_MANGER, userType=AccountType.COM, desc="企业新增联系方式")
	public Integer save(ComContactBo ComContactBo, Integer comId){
     	
		 ComContact comContact = new ComContact();
         ComContactBo.setComId(comId);
         ComContactBo.setCreateDate(DateTime.now().toDate());
         ComContactBo.setUpdateDate(DateTime.now().toDate());
         copyProperties(comContact, ComContactBo, CopyType.VO_TO_MODEL);
         
         return comContactEao.save(comContact);
		
	}

    /**
     * 按email取得公司ID
     * @param email
     * @return
     */
    public Integer getComIdByEmail(String email) {

        ComContact comContact = comContactEao.getComContactByEmail(email);
        if(comContact != null){
            return comContact.getComId();
        }
        return 0;
    }

    @Override
	public void modelJsonToVoObject(ComContact model, ComContactBo vo) {

		ComContactBase comContactBase = model.getComContactBase();
		
		BeanUtils.copyProperties(comContactBase, vo);
		
		//判断是否为默认联系人，如果为默认联系人则将关联的企业location设置给默认联系人（前台需要）
		if(model.getDefaultFlag() != null && model.getDefaultFlag() == 1 && model.getComInfo() != null && model.getComInfo().getLocation() != null) {
			vo.setLocation(model.getComInfo().getLocation().intValue());
        }
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		 if (comContactBase.getAddress() != null && JsonValidator.validJsonToVo(comContactBase.getAddress(), new TypeToken<AddressBo>(){})) {
			 vo.setAddressBo(gson.fromJson(comContactBase.getAddress(), AddressBo.class));
         }
         if (comContactBase.getInterviewAddress() != null && JsonValidator.validJsonToVo(comContactBase.getInterviewAddress(), new TypeToken<AddressBo>(){})) {
        	 vo.setInterviewAddressBo(gson.fromJson(comContactBase.getInterviewAddress(), AddressBo.class));
         }
         if (comContactBase.getEmail() != null && JsonValidator.validJsonToVo(comContactBase.getEmail(), new TypeToken<List<String>>(){})){//加入校验
     		List<String> emailList = gson.fromJson(comContactBase.getEmail(), new TypeToken<List<String>>(){}.getType());
     		vo.setEmailList(emailList);
         }
	}

	@Override
	public void voObjectToModelJson(ComContactBo vo, ComContact model) {
		if(model.getComContactBase() == null){
			model.setComContactBase(new ComContactBase());
		}
		BeanUtils.copyProperties(vo, model.getComContactBase());
		Gson gson = new GsonBuilder().serializeNulls().create();
		model.getComContactBase().setEmail(vo.getEmailList() != null ? gson.toJson(vo.getEmailList()) : null);
		model.getComContactBase().setAddress(new Gson().toJson(vo.getAddressBo()));
		model.getComContactBase().setInterviewAddress(new Gson().toJson(vo.getInterviewAddressBo()));
	}



    public  String delComContact(Model model,Integer comId, Integer id){
        List<ComContact> comContactList = getComContactList(comId);
        if(comContactList.size()<=1){
            model.addAttribute("status", false);
            model.addAttribute("message", "唯一联系人不能删除");
            return "blank";
        }
        ComContact comContact = findById(id);
        if (comContact != null && comContact.getComId().equals(comId) ) {
            if( comContact.getDefaultFlag()== 1)  {
                ComContact minDate=null;
                for (ComContact contact:comContactList) {
                    if(contact.getId().equals(comContact.getId())){
                        continue;
                    }
                    if(minDate==null){
                        minDate= contact;
                        continue;
                    }
                    if(contact.getCreateDate().before(minDate.getCreateDate())) {
                        minDate=contact;
                    }
                }
                minDate.setDefaultFlag(1);
                //设置最近的为默认联系人
                this.comContactEao.update(minDate);
                //联系人被修改，同时修改职位表上关联的联系人信息未默认联系人
        		List<ComPosition> positions = comPositionSrv.getComListByContact(comId, comContact.getId());
        		if(positions != null){
        			comPositionSrv.updatePosContactList(minDate, positions);
        		}
        		//删除
                delete(comContact);
                model.addAttribute("status", true);
                model.addAttribute("message", "删除成功");
            }else{
            	//联系人被修改，同时修改职位表上关联的联系人信息未默认联系人
        		List<ComPosition> positions = comPositionSrv.getComListByContact(comId, comContact.getId());
        		if(positions != null){
        			//默认联系人
        			ComContact defaultContact = comContactEao.getDefaultComContact(comId);
        			comPositionSrv.updatePosContactList(defaultContact, positions);
        		}
                delete(comContact);
                model.addAttribute("status", true);
                model.addAttribute("message", "删除成功");
            }
        }else{
            model.addAttribute("status", false);
            model.addAttribute("message", "参数错误");
        }
        return "blank";
    }


    /**
     *
     * @Title: syncPositionContact
     * @Description: 同步指定联系人信息到所有职位或本部门职位中
     * @param @param userId   用户ID
     * @param @param synType  0:同步所有职位 1：同步到部门职位
     * @param @param contactId    联系人ID
     * @param @param departmentId   部门ID
     */
    @Log(module=LogModule.COM_POSITION_MANGER, userType=AccountType.COM, desc="企业同步职位联系方式-智聘")
    public boolean syncPositionContact(Integer userId,Integer synType,Integer id,Integer departmentId) {
        ComUser user = comUserEao.get(userId);
        ComContact comContact=comContactEao.get(id);
        if(user == null||comContact==null) return false;
        List<ComPosition> positionList = null;
        //判断是同步到所有职位还是同步到当前职位部门的所有职位（0=所有职位，1=当前职位部门）
        if(synType == 0) {
            //判断是否为主账号
            if(user.getRoleType() == 0) {
                positionList = comPositionSrv.getAllComPositionByComId(user.getComId());
            } else {
                //获取用户可管理分子公司 /部门权限
                List<Integer> deptRights = new ArrayList<Integer>();
                if(new JsonValidator().validate(user.getDeptRights())) {
                    deptRights = new Gson().fromJson(user.getDeptRights(),
                            new TypeToken<List<Integer>>() {}.getType());
                }
                //判断是否有管理其他分子公司/部门权限(-1=有全部部门权限)
                if(org.springframework.util.CollectionUtils.contains(deptRights.iterator(), -1)) {
                    positionList = comPositionSrv.getAllComPositionByComId(user.getComId());
                } else {
                    comPositionEao.getAllComPositionByDeptIds(user.getComId(), deptRights);
                }
            }
        } else {
            positionList = comPositionEao.getAllComPositionByComIdAndDeptId(user.getComId(), departmentId);
        }

        if(positionList != null && !org.springframework.util.CollectionUtils.isEmpty(positionList)) {
            //批量同步更新联系人信息
            for(ComPosition position : positionList) {
                ComContactBase  comContactBase=new  ComContactBase();
                BeanUtils.copyProperties(comContact.getComContactBase(),comContactBase);
                position.setComContactBase(comContactBase);
                position.setContactId(id);
                comPositionEao.saveOrUpdate(position);
            }
        }
        return  true;
    }

    /**
     * 修改或保存公司联系人
     * @param comContact
     * @return
     */
    public ComContact saveOrUpdate(ComContact comContact)  {
    	comContactEao.saveOrUpdate(comContact);
        return comContact;
    }
    
    /**
     * 获取企业默认联系人
     * 
     * @param comId 企业ID
     * @return
     */
    public ComContact getDefaultComContact(Integer comId){
    	return comContactEao.getDefaultComContact(comId);
    }

}
