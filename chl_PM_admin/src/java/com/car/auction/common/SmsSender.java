package com.car.auction.common;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
public class SmsSender {
	
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
    static final String accessKeyId = "LTAIqxxRY1gTaWiE";
    static final String accessKeySecret = "PDOnfIlQOQ21cOCXI7xPJJ2UIj6m8S";
    /*--------------------------公用模板-----------------------------*/
    //公用提示信息
    public static String PUBLICCODE="SMS_89775001";
    public static String formatmag="{ \"code\":\"%s\"}"; 
    
    /*--------------------------拍卖成功-----------------------------*/
    //您于${time}成功获拍${name}，请凭此验证码（${bid}）到拍品存放地办理交接手续
    public static String AUCTIONCODE="SMS_164185032";
    public static String formatSuccessMag="{'time':'%s','name':'%s','bid':'%s'}";
    
    
    /*--------------------------密码重置-----------------------------*/
    //${name}您好，登录密码已重置成功，如非本人操作，请及时与我们联系 400-671-9518
    public static String RESETCODE="SMS_120411703";
    public static String formatPwdMag="{'name':'%s'}";
    
    private static IAcsClient acsClient= null;
    
    static{
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e) {
			e.printStackTrace();
		}
         acsClient = new DefaultAcsClient(profile);
    }

    public static SendSmsResponse sendSms(String mobile,String format,String templeCope,Object... args){
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("车互联");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templeCope);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为//"{ \"code\":\"123\"}"
        request.setTemplateParam(String.format(format, args));
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse=new SendSmsResponse();
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
        return sendSmsResponse;
    }
}
