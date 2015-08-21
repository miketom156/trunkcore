package com.job5156.core.srv.per;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.job5156.core.bo.form.per.PerLetterFormBo;
import com.job5156.core.eao.per.PerLetterEao;
import com.job5156.core.srv.base.BaseSrv;
import com.job5156.webapp.model.per.PerLetter;

/**
 * <p></p>
 * Date:Administrator 2015-8-4
 *
 * @author hjs
 * @version 1.0
 */
@Service
@Transactional(value="transactionManagerAction")
public class PerLetterSrv extends BaseSrv<PerLetter,Integer>{

    @Resource
    private PerLetterEao perLetterEao;

    @Override
    protected void initBaseHibernateEao() {
        this.baseHibernateEao = perLetterEao;
    }

    /**
     * 根据userid 获取求职信的列表
     * @param userId
     * return bo
     */
    public PerLetterFormBo getJobLetter(Integer userId){
        List<PerLetter> jobLetter  = perLetterEao.findByUserId(userId);
        PerLetterFormBo bo = new PerLetterFormBo();
        if(CollectionUtils.isNotEmpty(jobLetter)){
            for(PerLetter letter:jobLetter){
                if(letter.getSign() == 1){
                    bo.setJobLetterId1(letter.getId().toString());
                    bo.setJobLetterContent1(letter.getJobLetter());
                }
                if(letter.getSign() == 2){
                    bo.setJobLetterId2(letter.getId().toString());
                    bo.setJobLetterContent2(letter.getJobLetter());
                }
                if(letter.getSign() == 3){
                    bo.setJobLetterId3(letter.getId().toString());
                    bo.setJobLetterContent3(letter.getJobLetter());
                }
                if(letter.getDefaultLetter()==1){
                    bo.setDefaultLetter(letter.getSign());
                }
            }
        }
        return bo;
    }

    /**
     * 分析求职信的数据
     * @param bo
     * @param userId
     */
    public void analysisAndUpdateDate(PerLetterFormBo bo, Integer userId){
        if(StringUtils.isNotEmpty(bo.getJobLetterContent1()) || StringUtils.isNotEmpty(bo.getJobLetterId1())){
            PerLetter letter1 = new PerLetter();
            letter1.setId(NumberUtils.toInt(bo.getJobLetterId1(), 0));
            letter1.setDefaultLetter(bo.getDefaultLetter()==1?1:0);
            letter1.setJobLetter(bo.getJobLetterContent1());
            letter1.setSign(1);
            letter1.setTitle("求职信1");
            letter1.setUpdateTime(DateTime.now().toDate());
            letter1.setUserId(userId);
            saveOrUpdate(letter1);
        }
        if(StringUtils.isNotEmpty(bo.getJobLetterContent2()) || StringUtils.isNotEmpty(bo.getJobLetterId2())){
            PerLetter letter2 = new PerLetter();
            letter2.setId(NumberUtils.toInt(bo.getJobLetterId2(),0));
            letter2.setDefaultLetter(bo.getDefaultLetter()==2?1:0);
            letter2.setJobLetter(bo.getJobLetterContent2());
            letter2.setSign(2);
            letter2.setTitle("求职信2");
            letter2.setUpdateTime(DateTime.now().toDate());
            letter2.setUserId(userId);
            saveOrUpdate(letter2);
        }
        if(StringUtils.isNotEmpty(bo.getJobLetterContent3()) || StringUtils.isNotEmpty(bo.getJobLetterId3())){
            PerLetter letter3 = new PerLetter();
            letter3.setId(NumberUtils.toInt(bo.getJobLetterId3(),0));
            letter3.setDefaultLetter(bo.getDefaultLetter()==3?1:0);
            letter3.setJobLetter(bo.getJobLetterContent3());
            letter3.setSign(3);
            letter3.setTitle("求职信3");
            letter3.setUpdateTime(DateTime.now().toDate());
            letter3.setUserId(userId);
            saveOrUpdate(letter3);
        }
    }
    
    /**
     * 获取用户的默认的求职信
     * @param bo
     * @param userId
     */  
	public PerLetter getDefaultLetter(Integer perUserId){
		PerLetter defaultLetter = null;
		List<PerLetter> perLetters = perLetterEao.findByUserId(perUserId);
        if(CollectionUtils.isNotEmpty(perLetters)) {
            for (PerLetter perLetter : perLetters) {
                if(perLetter.getDefaultLetter() == 1){
                    defaultLetter = perLetter;
                    break;
                }
            }
        }
		return defaultLetter;
	}
}
