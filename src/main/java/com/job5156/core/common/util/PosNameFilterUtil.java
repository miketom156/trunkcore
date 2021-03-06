package com.job5156.core.common.util;

import com.google.common.collect.Lists;
import com.job5156.core.bo.PosBriefSimpleBo;
import com.job5156.core.common.searcher.entity.PosBrief;
import com.job5156.core.bo.form.ComPositionBo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;

import java.util.*;

/**
 * Function: 过滤一些非法的或低端的职位名,职位热度计算等
 *
 * @autor:leeton
 * Date: 2014/10/21
 * Time: 8:27
 */
public class PosNameFilterUtil {

    /**
     * 过滤假高薪的职位>2万且非高端职位,以及低端职位 <5000元
     * @param posBriefList
     * @return
     */
    public static List<PosBrief> filterNotGoodPos(List<PosBrief> posBriefList){
        for(Iterator<PosBrief> posBriefIter = posBriefList.iterator();posBriefIter.hasNext();) {
            PosBrief pos = posBriefIter.next();
            if (pos.getSalary() < 5 || (pos.getSalary() > 10 && !isBetterSalaryPos(pos.getPosName())) || isCheapPos(pos.getPosName())) {
                posBriefIter.remove();
            }
        }
        return posBriefList;
    }

    /**
     * 过滤假高薪的职位 薪资>salaryLeve且非高端职位
     * salaryLeve = OptionMap.OPT_MAP_SALARY
     * @param posBriefList
     * @return
     */
    public static List<PosBrief> filterNotTrueSalaryPos(List<PosBrief> posBriefList,Integer salaryLeve){
        for(Iterator<PosBrief> posBriefIter = posBriefList.iterator();posBriefIter.hasNext();) {
            PosBrief pos = posBriefIter.next();
            if (pos.getSalary() > salaryLeve && !isBetterSalaryPos(pos.getPosName())) {
                posBriefIter.remove();
            }
        }
        return posBriefList;
    }

    /**
     * 过滤假高薪
     * 规则:非中端以上职位，并且月薪过万
     * @param posBriefList
     * @param salaryLeve
     * @return
     */
    public static List<PosBrief> filterNotTrueMidSalaryPos(List<PosBrief> posBriefList,Integer salaryLeve){
        for(Iterator<PosBrief> posBriefIter = posBriefList.iterator();posBriefIter.hasNext();) {
            PosBrief pos = posBriefIter.next();
            if (pos.getSalary() > salaryLeve && (!isMidSalaryPos(pos.getPosName()) || !isBetterSalaryPos(pos.getPosName())) ) {
                posBriefIter.remove();
            }
        }
        return posBriefList;
    }




    /**
     * 低薪职位 <5000元
     * @param posBriefList
     * @return
     */
    public static List<PosBrief> filterDownSalaryPos(List<PosBrief> posBriefList){
        for(Iterator<PosBrief> posBriefIter = posBriefList.iterator();posBriefIter.hasNext();) {
            PosBrief pos = posBriefIter.next();
            if (pos.getSalary() < 5 ) {
                posBriefIter.remove();
            }
        }
        return posBriefList;
    }

    /**
     * 按职称名，过滤低端职位
     * @param posBriefList
     * @return
     */
    public static List<PosBrief> filterCheapPos(List<PosBrief> posBriefList){
        for(Iterator<PosBrief> posBriefIter = posBriefList.iterator();posBriefIter.hasNext();) {
            PosBrief pos = posBriefIter.next();
            if (isCheapPos(pos.getPosName())) {
                posBriefIter.remove();
            }
        }
        return posBriefList;
    }


    /**
     * 按职称名，过滤低端职位
     * @param posBriefList
     * @return
     */
    public static List<ComPositionBo> filterCheapComPos(List<ComPositionBo> posBriefList){
        for(Iterator<ComPositionBo> posBriefIter = posBriefList.iterator();posBriefIter.hasNext();) {
            ComPositionBo pos = posBriefIter.next();
            if (isCheapPos(pos.getPosName())) {
                posBriefIter.remove();
            }
        }
        return posBriefList;
    }

    /**
     * 排除 X天前的职位
     * @param posBriefList
     * @ minusDays
     * @return
     */
    public static List<PosBrief> filterOldTimePos(List<PosBrief> posBriefList,Integer minusDays){
        for(Iterator<PosBrief> posBriefIter = posBriefList.iterator();posBriefIter.hasNext();) {
            PosBrief pos = posBriefIter.next();
            if (pos.getFreDate().before(DateTime.now().minusDays(minusDays).toDate())) {
                posBriefIter.remove();
            }
        }
        return posBriefList;
    }

    /**
     * 高端职位
     * @param posName
     * @return
     */
    public static boolean isBetterSalaryPos(String posName){
        if(StringUtils.isBlank(posName)) return false ;
        List<String> posList = Arrays.asList("首席", "经理", "秘书长", "总监", "CEO", "CTO",  "CIO", "CFO", "COO", "HRM", "HRD", "董事");
        return RegExUtil.isFound(posList, posName) ;
    }

    /**
     * 中端职位
     * @param posName
     * @return
     */
    public static boolean isMidSalaryPos(String posName){
        if(StringUtils.isBlank(posName)) return false ;
        List<String> posList = Arrays.asList("工程师", "技术", "软件", "IOS", "ANDROID", "安卓", "财务", "会计", "秘书", "设计", "研发","PHP","翻译","程序员","师傅");
        return RegExUtil.isFound(posList, posName) ;
    }

    /**
     * 低端职位
     * @param posName
     * @return
     */
    public static boolean isCheapPos(String posName){

        if(StringUtils.isBlank(posName)) return false ;
        List<String> posList = Arrays.asList("文员","仓库", "干部", "推广", "销售顾问", "实习生", "销售员","组长","线长","仓管","生管","专员","助理","服务员","招待","储备","技师","应届生","学徒","储干","兼职","学习");
        return  RegExUtil.isFound(posList, posName) ;
    }

    /**
     * 以 / ( 【 , 分隔的职位名只取前面字串
     * @param posName
     * @return
     */
    public static String getFirstName(String posName){
        if(StringUtils.isBlank(posName)){return "";}
        if(StringUtils.containsIgnoreCase(posName,"c#")||StringUtils.containsIgnoreCase(posName,".net")){
            return posName;
        }
        String[] specStr = new String[]{"/","(","（","【","[",",","〖","「","〔","『","+","#"};
        for (String s : specStr) {
            if(StringUtils.contains(posName,s)) {
                try {
                    posName = StringUtils.split(posName,s)[0];
                    break;
                } catch (Exception e) {
                }
            }
        }
        posName = posName.replaceAll("[()（）〖〗【】<>《》－&、，;,；·.| |/#]", "");
        posName = StringUtils.replace(posName,"　", "");
        return posName;
    }


    /**
     * 追加搜索出来的职位到LIST,已存在的职位跳过
     * @param posBriefList
     * @param tempPosBriefList
     * @return
     */
    public static List<PosBrief> addPosToListByDistinct(List<PosBrief> posBriefList,List<PosBrief> tempPosBriefList){

        List<Integer> posIdList = Lists.newArrayList();
        for (PosBrief posBrief  : posBriefList) {
            posIdList.add(posBrief.getId());
        }

        for (PosBrief posBrief : tempPosBriefList) {
            if(!posIdList.contains(posBrief.getId())) {
                posBriefList.add(posBrief);
            }
        }
        posIdList = null;
        return posBriefList;
    }

    /**
     * 追加搜索出来的职位VO,到LIST,已存在的职位跳过
     * @param posBriefSimpleBoList
     * @param tempPosBriefSimpleBoList
     * @return
     */
    public static List<PosBriefSimpleBo> addPosVoToListByDistinct(List<PosBriefSimpleBo> posBriefSimpleBoList,List<PosBriefSimpleBo> tempPosBriefSimpleBoList){

        List<Integer> posIdList = Lists.newArrayList();
        for (PosBriefSimpleBo vo  : posBriefSimpleBoList) {
            posIdList.add(vo.getPosId());
        }

        for (PosBriefSimpleBo vo : tempPosBriefSimpleBoList) {


            if(!posIdList.contains(vo.getPosId())) {
                posBriefSimpleBoList.add(vo);
            }
        }
        posIdList = null;
        return posBriefSimpleBoList;
    }

    /**
     * 手动抽取出短格式公司名称
     * @param comName
     * @return
     */
    public static String getShortComName(String comName){

        if(StringUtils.isNotBlank(comName)) {
            try {
                comName = StringUtils.replace(comName, "股份有限公司", "");
                comName = StringUtils.replace(comName, "发展有限公司", "");
                comName = StringUtils.replace(comName, "有限公司", "");

           /*     if(comName.contains("国") && comName.indexOf("中国") == -1) {
                    comName = splitByIdxStr(comName, "国");
                }*/
                if(comName.contains("省")) {
                    comName = splitByIdxStr(comName, "省");
                }
                if(comName.contains("市") && comName.lastIndexOf("市") != comName.length() -1){
                    comName = splitByIdxStr(comName, "市");
                }
                if(comName.contains("区") && comName.indexOf("区")<5 && comName.lastIndexOf("区") != comName.length() -1){
                    comName = splitByIdxStr(comName, "区");
                }
                if(comName.contains("镇")  && comName.lastIndexOf("镇") != comName.length() -1){
                    comName = splitByIdxStr(comName, "镇");
                }
            } catch (Exception e) {
               //截取失败跳过
            }
            return comName;
        }else {
            return "";
        }

    }

    private static String splitByIdxStr(String str, String idxStr) {
        String[] tempArr = new String[80];
        tempArr =  StringUtils.split(str, idxStr);
        if (tempArr.length == 2) {
            return tempArr[1];
        }
        return str;
    }


    /**
     * 计算热度的等级
     * @param posBriefList
     * @return
     */
    public static List<PosBrief> hotsToRank(List<PosBrief> posBriefList){

        Collections.sort(posBriefList, new Comparator<PosBrief>() {
            @Override
            public int compare(PosBrief o1, PosBrief o2) {
                if (o1 == null || o1.getHotDegree() == null) return -1;
                if (o2 == null || o2.getHotDegree() == null) return 1;
                return o1.getHotDegree().compareTo(NumberUtils.toInt(o2.getHotDegree() + "", 0));
            }
        });

        //计算中位数
        PosBrief modianObj = posBriefList.get(Integer.valueOf(posBriefList.size()/2));
        int median = Integer.valueOf(NumberUtils.toInt(modianObj.getHotDegree()+"",0));
        if(median <10){ //如果中位数很小，则用平均数代替
            int total = 0 ;
            for (PosBrief posBrief : posBriefList) {
                total += NumberUtils.toInt(posBrief.getHotDegree()+"",0);
            }
            median = Integer.valueOf(total / posBriefList.size());
        }

        int radix = 0;
        //计算基数, int（中位数/5）,这里还要再优化
        radix = median >= 1000?Integer.valueOf(median / 5):1;
        radix = median >= 100?Integer.valueOf(median / 5):1;
        radix = median >= 10?Integer.valueOf(median / 5):1;

        //设置rank
        for (PosBrief posBrief : posBriefList) {
            int hots = NumberUtils.toInt(posBrief.getHotDegree()+"",0);
            int rank = Integer.valueOf(hots / radix) + 1;
            rank =  rank>5?5:rank;
            posBrief.setRank(rank);
        }

        return  posBriefList;

    }

    /**
     * 常见的适合接收名片的职位名称
     * @param posName   受验证的职位名
     * @return
     */
    public static boolean isMatchCardJob(String posName){

        if(StringUtils.isBlank(posName)) return false ;
        final String cheapPosJobName = "销售,业务,客服,司机,厨师,煮饭,普工,技工,文员,服务,导购,前台,收银,营业,幼教,早教,美容,美发,发型,修理,理货,送货,快递,速递,仓管,仓库,保洁,清洁,保安,安保,迎宾,接待,配菜,打荷,杂工,后勤,水电,电工,保险,顾问,保姆,月嫂,钟点工,木工,油漆工,钳工,切割,焊工,钣金工,车工,铣工,缝纫工,锅炉工,制冷,水暖工,铲车,叉车,铸造,注塑,手机维修,电梯工,操作工,包装工,水泥工,钢筋工,管道工,瓦工,组装工,样衣工,染工,纺织工,印花工,压熨工,模具";
        String[] jobNameArr = StringUtils.split(cheapPosJobName, ",");
        List<String> posList = Arrays.asList(jobNameArr);
        return  RegExUtil.isFound(posList, posName) ;

    }

    public static void main(String[] args) {
       // String comName = "广东理文造纸有限公司";
       // String comName = "东莞市合景实业发展有限公司";
       // String comName = "佛山市禅城区恒溢塑料彩印厂";
        String comName = "深圳市捷顺科技实业股份有限公司佛山技术服务部";
        //String comName = "中国平安人寿保险股份有限公司东莞中心支公司（茶山片区）";
       // String comName = "中国平安人寿保险股份有限公司东莞市";
        //String comName = "满堂红（中国）置业有限公司东莞分公司";
        comName = getShortComName(comName);
        System.out.printf(comName);
    }

}
