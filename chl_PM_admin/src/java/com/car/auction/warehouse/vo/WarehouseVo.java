package com.car.auction.warehouse.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：Warehouse
 * 类描述：车辆入库登记表
 * 创建人：张婉欣
 * 创建时间： 2018年8月8日 下午2:25:12
 * @version
 */
public class WarehouseVo {
    private String id;
    private String auctionId;		//拍品id
    private String carCard;			//车牌：两块|一块|无|损坏
    private String ccProvince;
    private String ccProvinceId;
    private String ccCity;
    private String ccCityId;
    private String ccArea;
    private String ccAreaId;
    private String carCardAddress;	//车牌位置
    private String key;				//正常钥匙：无|一把|两把
    private String spareKey;		//备用钥匙：无|一把|两把
    private String kProvince;
    private String kProvinceId;
    private String kCity;
    private String kCityId;
    private String kArea;
    private String kAreaId;
    private String keyAddress;		//钥匙位置
    private String keyRemark;		//备注
    private String sProvince;
    private String sProvinceId;
    private String sCity;
    private String sCityId;
    private String sArea;
    private String sAreaId;
    private String storeAddress;	//库存存放地
    private String storeSpace;		//库存区域
    private Date storeTime;			//入库时间
    private BigDecimal trailerCost;	//拖车费
    private String trailerCostPay;	//应支付给
    private Integer voucherId;			//上传凭证
    private String trailerPhone;	//拖车电话
    private String originProvince;	//起始地：省
    private String originProvinceId;
    private String originCity;		//起始地：市
    private String originCityId;
    private String originAddress;	//起始地：详细地址
    private String distance;		//公里数
    private String destinationProvince;	//目的地：省
    private String destinationProvinceId;
    private String destinationCity;		//目的地：市
    private String destinationCityId;
    private String destinationAddress;	//目的地：详细地址
    private String storePhoto;		//上传拍卖截图以及拖车图片
    private String carStatus;		//车辆状况：外观正常|无或缺失|拆解|破损|无法确认
    private String gearboxType;		//变速箱类型：手动|自动|手自一体
    private String carMileage;		//表显里程
    private Integer vinIsDamage;	//车架号是否受损：0-是|1-否
    private String engine;			//发动机
    private String gearbox;			//变速箱
    private String waterbox;		//水箱
    private String condenser;		//冷凝器
    private String battery;			//电瓶
    private String airconditionPump;//空调泵
    private String motor;			//马达
    private String powerSteeringPump;	//转向助力泵
    private String dynamo;			//发电机
    private String absPump;			//ABS泵
    private String threeWayCatalyticConverter;	//三元催化转化器
    private String exhaustPipe;		//排气管
    private String compressor;		//压缩机
    private String rightFrontHeadlight;	//右前大灯
    private String rightMirror;		//右后视镜
    private String rightFrontDoor;	//右前车门
    private String rightRearDoor;	//右后车门
    private String rightRearTailLight;	//右后尾灯
    private String leftFrontHeadlight;	//左前大灯
    private String leftMirror;		//左后视镜
    private String leftFrontDoor;	//左前车门
    private String leftRearDoor;	//左后车门
    private String leftRearTailLight;	//左后尾灯
    private String trunk;			//后备箱
    private String spareTire;		//备胎
    private String vehicleTools;	//随车工具
    private String triangleIndicator;	//三角指示牌
    private String trim;			//内饰
    private String dashboard;		//仪表台
    private String seat;			//座椅
    private String DVDCD;			//DVD/CD/收音机
    private String voicebox;		//音箱
    private String engineComputer;	//发动机电脑
    private String gearboxComputer;	//变速箱电脑
    private String drivingComputer;	//行车电脑
    private String burglarComputer;	//防盗电脑
    private String gasbagComputer;	//气囊电脑
    private String entertainmentComputer;	//娱乐电脑
    private String troubleDescription;	//车架号、发送机号的查验和事故形态描述
    private String maintenanceManual;	//保养手册：有|无
    private String operatingManual;	//使用说明书：有|无
    private Integer tyreNum;		//轮胎数量
    private String dismantling;		//车辆是否拆解
    private String dismantlingPart;	//拆解部位
    private String twoAccidents;	//是否二次事故：是|否
    private String twoAccidentsRemark;	//二次事故备注说明
    private String refit;			//车辆是否改装：是|否
    private String refitPart;		//改装部位
    private String checkPart;		//已清点部件
    private String noCheckPark;		//无法清点部件
    private String receivingPart;	//单独收纳部件
    private String remark;			//备注
    private String salesman;		//业务员
    private Integer auditState;		//审核状态：0-未审核；1-审核通过；-1：审核驳回
    private Integer isWarehouse;	//入库状态：0-未入库；1-已入库；2-已出库；
    private Integer isPrint;		//是否打印提货单：0-未打印；1-已打印
    private Date outStockTime;		//出库时间
    private Integer dataState;		//数据状态 ： 0-保存（暂存）；1-提交（入库）
    private String fileIds;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	public String getCarCard() {
		return carCard;
	}
	public void setCarCard(String carCard) {
		this.carCard = carCard;
	}
	public String getCcProvince() {
		return ccProvince;
	}
	public void setCcProvince(String ccProvince) {
		this.ccProvince = ccProvince;
	}
	public String getCcProvinceId() {
		return ccProvinceId;
	}
	public void setCcProvinceId(String ccProvinceId) {
		this.ccProvinceId = ccProvinceId;
	}
	public String getCcCity() {
		return ccCity;
	}
	public void setCcCity(String ccCity) {
		this.ccCity = ccCity;
	}
	public String getCcCityId() {
		return ccCityId;
	}
	public void setCcCityId(String ccCityId) {
		this.ccCityId = ccCityId;
	}
	public String getCcArea() {
		return ccArea;
	}
	public void setCcArea(String ccArea) {
		this.ccArea = ccArea;
	}
	public String getCcAreaId() {
		return ccAreaId;
	}
	public void setCcAreaId(String ccAreaId) {
		this.ccAreaId = ccAreaId;
	}
	public String getCarCardAddress() {
		return carCardAddress;
	}
	public void setCarCardAddress(String carCardAddress) {
		this.carCardAddress = carCardAddress;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSpareKey() {
		return spareKey;
	}
	public void setSpareKey(String spareKey) {
		this.spareKey = spareKey;
	}
	public String getkProvince() {
		return kProvince;
	}
	public void setkProvince(String kProvince) {
		this.kProvince = kProvince;
	}
	public String getkProvinceId() {
		return kProvinceId;
	}
	public void setkProvinceId(String kProvinceId) {
		this.kProvinceId = kProvinceId;
	}
	public String getkCity() {
		return kCity;
	}
	public void setkCity(String kCity) {
		this.kCity = kCity;
	}
	public String getkCityId() {
		return kCityId;
	}
	public void setkCityId(String kCityId) {
		this.kCityId = kCityId;
	}
	public String getkArea() {
		return kArea;
	}
	public void setkArea(String kArea) {
		this.kArea = kArea;
	}
	public String getkAreaId() {
		return kAreaId;
	}
	public void setkAreaId(String kAreaId) {
		this.kAreaId = kAreaId;
	}
	public String getKeyAddress() {
		return keyAddress;
	}
	public void setKeyAddress(String keyAddress) {
		this.keyAddress = keyAddress;
	}
	public String getKeyRemark() {
		return keyRemark;
	}
	public void setKeyRemark(String keyRemark) {
		this.keyRemark = keyRemark;
	}
	public String getsProvince() {
		return sProvince;
	}
	public void setsProvince(String sProvince) {
		this.sProvince = sProvince;
	}
	public String getsProvinceId() {
		return sProvinceId;
	}
	public void setsProvinceId(String sProvinceId) {
		this.sProvinceId = sProvinceId;
	}
	public String getsCity() {
		return sCity;
	}
	public void setsCity(String sCity) {
		this.sCity = sCity;
	}
	public String getsCityId() {
		return sCityId;
	}
	public void setsCityId(String sCityId) {
		this.sCityId = sCityId;
	}
	public String getsArea() {
		return sArea;
	}
	public void setsArea(String sArea) {
		this.sArea = sArea;
	}
	public String getsAreaId() {
		return sAreaId;
	}
	public void setsAreaId(String sAreaId) {
		this.sAreaId = sAreaId;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public String getStoreSpace() {
		return storeSpace;
	}
	public void setStoreSpace(String storeSpace) {
		this.storeSpace = storeSpace;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getStoreTime() {
		return storeTime;
	}
	public void setStoreTime(Date storeTime) {
		this.storeTime = storeTime;
	}
	public BigDecimal getTrailerCost() {
		return trailerCost;
	}
	public void setTrailerCost(BigDecimal trailerCost) {
		this.trailerCost = trailerCost;
	}
	public String getTrailerCostPay() {
		return trailerCostPay;
	}
	public void setTrailerCostPay(String trailerCostPay) {
		this.trailerCostPay = trailerCostPay;
	}
	public Integer getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}
	public String getTrailerPhone() {
		return trailerPhone;
	}
	public void setTrailerPhone(String trailerPhone) {
		this.trailerPhone = trailerPhone;
	}
	public String getOriginProvince() {
		return originProvince;
	}
	public void setOriginProvince(String originProvince) {
		this.originProvince = originProvince;
	}
	public String getOriginProvinceId() {
		return originProvinceId;
	}
	public void setOriginProvinceId(String originProvinceId) {
		this.originProvinceId = originProvinceId;
	}
	public String getOriginCity() {
		return originCity;
	}
	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}
	public String getOriginCityId() {
		return originCityId;
	}
	public void setOriginCityId(String originCityId) {
		this.originCityId = originCityId;
	}
	public String getOriginAddress() {
		return originAddress;
	}
	public void setOriginAddress(String originAddress) {
		this.originAddress = originAddress;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDestinationProvince() {
		return destinationProvince;
	}
	public void setDestinationProvince(String destinationProvince) {
		this.destinationProvince = destinationProvince;
	}
	public String getDestinationProvinceId() {
		return destinationProvinceId;
	}
	public void setDestinationProvinceId(String destinationProvinceId) {
		this.destinationProvinceId = destinationProvinceId;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	public String getDestinationCityId() {
		return destinationCityId;
	}
	public void setDestinationCityId(String destinationCityId) {
		this.destinationCityId = destinationCityId;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public String getStorePhoto() {
		return storePhoto;
	}
	public void setStorePhoto(String storePhoto) {
		this.storePhoto = storePhoto;
	}
	public String getCarStatus() {
		return carStatus;
	}
	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}
	public String getGearboxType() {
		return gearboxType;
	}
	public void setGearboxType(String gearboxType) {
		this.gearboxType = gearboxType;
	}
	public String getCarMileage() {
		return carMileage;
	}
	public void setCarMileage(String carMileage) {
		this.carMileage = carMileage;
	}
	public Integer getVinIsDamage() {
		return vinIsDamage;
	}
	public void setVinIsDamage(Integer vinIsDamage) {
		this.vinIsDamage = vinIsDamage;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getGearbox() {
		return gearbox;
	}
	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}
	public String getWaterbox() {
		return waterbox;
	}
	public void setWaterbox(String waterbox) {
		this.waterbox = waterbox;
	}
	public String getCondenser() {
		return condenser;
	}
	public void setCondenser(String condenser) {
		this.condenser = condenser;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getAirconditionPump() {
		return airconditionPump;
	}
	public void setAirconditionPump(String airconditionPump) {
		this.airconditionPump = airconditionPump;
	}
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public String getPowerSteeringPump() {
		return powerSteeringPump;
	}
	public void setPowerSteeringPump(String powerSteeringPump) {
		this.powerSteeringPump = powerSteeringPump;
	}
	public String getDynamo() {
		return dynamo;
	}
	public void setDynamo(String dynamo) {
		this.dynamo = dynamo;
	}
	public String getAbsPump() {
		return absPump;
	}
	public void setAbsPump(String absPump) {
		this.absPump = absPump;
	}
	public String getThreeWayCatalyticConverter() {
		return threeWayCatalyticConverter;
	}
	public void setThreeWayCatalyticConverter(String threeWayCatalyticConverter) {
		this.threeWayCatalyticConverter = threeWayCatalyticConverter;
	}
	public String getExhaustPipe() {
		return exhaustPipe;
	}
	public void setExhaustPipe(String exhaustPipe) {
		this.exhaustPipe = exhaustPipe;
	}
	public String getCompressor() {
		return compressor;
	}
	public void setCompressor(String compressor) {
		this.compressor = compressor;
	}
	
	public String getRightFrontHeadlight() {
		return rightFrontHeadlight;
	}
	public void setRightFrontHeadlight(String rightFrontHeadlight) {
		this.rightFrontHeadlight = rightFrontHeadlight;
	}
	public String getRightMirror() {
		return rightMirror;
	}
	public void setRightMirror(String rightMirror) {
		this.rightMirror = rightMirror;
	}
	public String getRightFrontDoor() {
		return rightFrontDoor;
	}
	public void setRightFrontDoor(String rightFrontDoor) {
		this.rightFrontDoor = rightFrontDoor;
	}
	public String getRightRearDoor() {
		return rightRearDoor;
	}
	public void setRightRearDoor(String rightRearDoor) {
		this.rightRearDoor = rightRearDoor;
	}
	public String getRightRearTailLight() {
		return rightRearTailLight;
	}
	public void setRightRearTailLight(String rightRearTailLight) {
		this.rightRearTailLight = rightRearTailLight;
	}
	public String getLeftFrontHeadlight() {
		return leftFrontHeadlight;
	}
	public void setLeftFrontHeadlight(String leftFrontHeadlight) {
		this.leftFrontHeadlight = leftFrontHeadlight;
	}
	public String getLeftMirror() {
		return leftMirror;
	}
	public void setLeftMirror(String leftMirror) {
		this.leftMirror = leftMirror;
	}
	public String getLeftFrontDoor() {
		return leftFrontDoor;
	}
	public void setLeftFrontDoor(String leftFrontDoor) {
		this.leftFrontDoor = leftFrontDoor;
	}
	public String getLeftRearDoor() {
		return leftRearDoor;
	}
	public void setLeftRearDoor(String leftRearDoor) {
		this.leftRearDoor = leftRearDoor;
	}
	public String getLeftRearTailLight() {
		return leftRearTailLight;
	}
	public void setLeftRearTailLight(String leftRearTailLight) {
		this.leftRearTailLight = leftRearTailLight;
	}
	public String getTrunk() {
		return trunk;
	}
	public void setTrunk(String trunk) {
		this.trunk = trunk;
	}
	public String getSpareTire() {
		return spareTire;
	}
	public void setSpareTire(String spareTire) {
		this.spareTire = spareTire;
	}
	public String getVehicleTools() {
		return vehicleTools;
	}
	public void setVehicleTools(String vehicleTools) {
		this.vehicleTools = vehicleTools;
	}
	public String getTriangleIndicator() {
		return triangleIndicator;
	}
	public void setTriangleIndicator(String triangleIndicator) {
		this.triangleIndicator = triangleIndicator;
	}
	public String getTrim() {
		return trim;
	}
	public void setTrim(String trim) {
		this.trim = trim;
	}
	public String getDashboard() {
		return dashboard;
	}
	public void setDashboard(String dashboard) {
		this.dashboard = dashboard;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	
	public String getDVDCD() {
		return DVDCD;
	}
	public void setDVDCD(String dVDCD) {
		DVDCD = dVDCD;
	}
	public String getVoicebox() {
		return voicebox;
	}
	public void setVoicebox(String voicebox) {
		this.voicebox = voicebox;
	}
	public String getEngineComputer() {
		return engineComputer;
	}
	public void setEngineComputer(String engineComputer) {
		this.engineComputer = engineComputer;
	}
	public String getGearboxComputer() {
		return gearboxComputer;
	}
	public void setGearboxComputer(String gearboxComputer) {
		this.gearboxComputer = gearboxComputer;
	}
	public String getDrivingComputer() {
		return drivingComputer;
	}
	public void setDrivingComputer(String drivingComputer) {
		this.drivingComputer = drivingComputer;
	}
	public String getBurglarComputer() {
		return burglarComputer;
	}
	public void setBurglarComputer(String burglarComputer) {
		this.burglarComputer = burglarComputer;
	}
	public String getGasbagComputer() {
		return gasbagComputer;
	}
	public void setGasbagComputer(String gasbagComputer) {
		this.gasbagComputer = gasbagComputer;
	}
	public String getEntertainmentComputer() {
		return entertainmentComputer;
	}
	public void setEntertainmentComputer(String entertainmentComputer) {
		this.entertainmentComputer = entertainmentComputer;
	}
	public String getTroubleDescription() {
		return troubleDescription;
	}
	public void setTroubleDescription(String troubleDescription) {
		this.troubleDescription = troubleDescription;
	}
	public String getMaintenanceManual() {
		return maintenanceManual;
	}
	public void setMaintenanceManual(String maintenanceManual) {
		this.maintenanceManual = maintenanceManual;
	}
	public String getOperatingManual() {
		return operatingManual;
	}
	public void setOperatingManual(String operatingManual) {
		this.operatingManual = operatingManual;
	}
	public Integer getTyreNum() {
		return tyreNum;
	}
	public void setTyreNum(Integer tyreNum) {
		this.tyreNum = tyreNum;
	}
	public String getDismantling() {
		return dismantling;
	}
	public void setDismantling(String dismantling) {
		this.dismantling = dismantling;
	}
	public String getDismantlingPart() {
		return dismantlingPart;
	}
	public void setDismantlingPart(String dismantlingPart) {
		this.dismantlingPart = dismantlingPart;
	}
	public String getTwoAccidents() {
		return twoAccidents;
	}
	public void setTwoAccidents(String twoAccidents) {
		this.twoAccidents = twoAccidents;
	}
	public String getTwoAccidentsRemark() {
		return twoAccidentsRemark;
	}
	public void setTwoAccidentsRemark(String twoAccidentsRemark) {
		this.twoAccidentsRemark = twoAccidentsRemark;
	}
	public String getRefit() {
		return refit;
	}
	public void setRefit(String refit) {
		this.refit = refit;
	}
	public String getRefitPart() {
		return refitPart;
	}
	public void setRefitPart(String refitPart) {
		this.refitPart = refitPart;
	}
	public String getCheckPart() {
		return checkPart;
	}
	public void setCheckPart(String checkPart) {
		this.checkPart = checkPart;
	}
	public String getNoCheckPark() {
		return noCheckPark;
	}
	public void setNoCheckPark(String noCheckPark) {
		this.noCheckPark = noCheckPark;
	}
	public String getReceivingPart() {
		return receivingPart;
	}
	public void setReceivingPart(String receivingPart) {
		this.receivingPart = receivingPart;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public Integer getAuditState() {
		return auditState;
	}
	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}
	public Integer getIsWarehouse() {
		return isWarehouse;
	}
	public void setIsWarehouse(Integer isWarehouse) {
		this.isWarehouse = isWarehouse;
	}
	public Integer getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(Integer isPrint) {
		this.isPrint = isPrint;
	}
	public Date getOutStockTime() {
		return outStockTime;
	}
	public void setOutStockTime(Date outStockTime) {
		this.outStockTime = outStockTime;
	}
	public Integer getDataState() {
		return dataState;
	}
	public void setDataState(Integer dataState) {
		this.dataState = dataState;
	}
	public String getFileIds() {
		return fileIds;
	}
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
	
}