package com.car.auction.procedure.standard.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.car.auction.procedure.entity.ProcedureFile;

/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedureUtil
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月7日 下午3:31:22
 * @version
 */
public class ProcedureUtil {
	
	/**
	 * getFileList()获取文件集合 用于新增
	 * @param fileTypeList
	 * @param aIdList
	 * @param procedureId
	 * @return
	 */
	public static List<ProcedureFile> getFileList(List<Integer> fileTypeList,List<Integer> aIdList,String procedureId,Integer version){
		List<ProcedureFile> procedureFileList=new ArrayList<ProcedureFile>();
		ProcedureFile procedureFile;
		for (int i=0;i<fileTypeList.size();i++) {
			Integer fileType = fileTypeList.get(i);
			procedureFile=new ProcedureFile(procedureId, fileType, aIdList.get(i),version);
			procedureFileList.add(procedureFile);
		}
		return procedureFileList;
	}
	
	/**
	 * getFileNames()获取上传文件名称
	 */
	public static String getFileNames(List<Integer> fileTypeList){
		StringBuffer sb=new StringBuffer();
		for (Integer fileType : fileTypeList) {
			switch (fileType) {
			case 0:
			case 1:
				if(sb.toString().contains("身份证")) 
					continue;
				sb.append("身份证,");
				break;
			case 2:
				sb.append("委托合同,");
				break;
			case 3:
				sb.append("车辆登记证,");
				break;
			case 4:
				sb.append("进口车商检证,");
				break;
			case 5:
				sb.append("车辆行驶证,");
				break;
			case 6:
				sb.append("交强险单凭证,");
				break;
			case 7:
				sb.append("购车发票,");
				break;
			case 8:
				sb.append("交裁证明复印件,");
				break;
			case 9:
				sb.append("购置附加税证,");
				break;
			case 10:
				sb.append("进口车报关单,");
				break;
			case 11:
				sb.append("工商认证章,");
				break;
			case 12:
				sb.append("其他证件,");
				break;
			case 13:
				sb.append("委托方机构代码证,");
				break;
			}
		}
		String fileNames=sb.toString();
		if(fileNames.indexOf(",")>-1) {
			fileNames=fileNames.substring(0,fileNames.length()-1);
		}
		return fileNames;
	}
	/**
	 * getFileName 获取上传文件名称
	 */
	public static String getFileName(Integer fileType){
		String fileName="";
		if (fileType!=null) {
			switch (fileType) {
			case 0:
				fileName="身份证";
				break;
			case 1:
				fileName="身份证";
				break;
			case 2:
				fileName="委托合同";
				break;
			case 3:
				fileName="车辆登记证";
				break;
			case 4:
				fileName="进口车商检证";
				break;
			case 5:
				fileName="车辆行驶证";
				break;
			case 6:
				fileName="交强险单凭证";
				break;
			case 7:
				fileName="购车发票";
				break;
			case 8:
				fileName="交裁证明复印件";
				break;
			case 9:
				fileName="购置附加税证";
				break;
			case 10:
				fileName="进口车报关单";
				break;
			case 11:
				fileName="工商认证章";
				break;
			case 12:
				fileName="其他证件";
				break;
			case 13:
				fileName="委托方机构代码证";
				break;
			default:
				fileName="";
				break;
			}
		}
		return fileName;
	}
	/**
	 * @param list1 旧数据
	 * @param list2 新数据
	 * @return
	 */
	public static List<Integer> getDifferent(List<Integer> list1, List<Integer> list2) {
        List<Integer> diff = new ArrayList<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(list1.size() + list2.size());
        List<Integer> maxList = list1;
        List<Integer> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }
        for (Integer id : maxList) {
            map.put(id, 1);
        }
        for (Integer id : minList) {
            Integer count = map.get(id);
            if (count != null) {
                map.put(id, ++count);
                continue;
            }
            map.put(id, 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        return diff;
 
    }
}
