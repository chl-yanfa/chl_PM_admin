package com.car.auction.auction.standard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.auction.auction.dao.AuctionAuditDao;
import com.car.auction.auction.dao.AuctionDao;
import com.car.auction.auction.entity.Auction;
import com.car.auction.auction.entity.AuctionAudit;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants; 

/**
 * 项目名称：SDIC-Inner
 * 类名称：AuctionAuditService
 * 类描述：参拍审核（拍品信息）
 * 创建人：张婉欣
 * 创建时间： 2018年8月2日 上午11:02:58
 * @version
 */
@Service
public class AuctionAuditService {
	
	@Autowired
	private AuctionAuditDao auctionAuditDao;
	@Autowired
	private AuctionDao auctionDao;
	/*@Autowired
	private ProcedureBillDao procedureDao;
	@Autowired
	private WarehouseDao warehouseDao;*/
	
	/**
	 * 新增拍品审核信息
	 * createtime now()
	 * is_delete 0
	 */
	public ResultDTO<String> addAuctionAudit(AuctionAudit auctionAudit) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(null != auctionAudit){
			String auctionId = auctionAudit.getAuctionId();
			Integer auditFlag = auctionAudit.getAuditFlag();
//			String auditFlagId = auctionAudit.getAuditFlagId();
			Integer isPass = auctionAudit.getIsPass();//0 通过 1驳回
			//查询参拍数据 车辆  手续  入库
			Auction auctionByDB = auctionDao.getAuctionById(auctionId);
			auctionByDB=auctionByDB==null?new Auction():auctionByDB;
			/*ProcedureBill procedureByDB = procedureDao.getProcedureBillByAuctionId(auctionId);
			procedureByDB=procedureByDB==null?new ProcedureBill():procedureByDB;
			Warehouse warehouseByDB = warehouseDao.getWarehouseByAuctionId(auctionId);
			warehouseByDB=warehouseByDB==null?new Warehouse():warehouseByDB;*/
			Auction auction=new Auction();
			auction.setId(auctionId);
			switch (auditFlag) {
			case 0://车辆
				if(isPass!=0) {
					auction.setRegistState(2);//已驳回
					auction.setAuditState(-1);
				}else {
					/*if(null!=procedureByDB.getAuditState()&&procedureByDB.getAuditState()==1
							&&null!=warehouseByDB.getAuditState()&&warehouseByDB.getAuditState()==1) {
					}*/
					auction.setAuditState(1);
				}
				auction.setAuctionAuditState(isPass==0?1:-1);//拍品信息审核
				break;
			case 1://手续
				/*if(isPass!=0) {
					auction.setProcedureState(3);//已驳回
					auction.setAuditState(-1);
				}else {
					if(null!=auctionByDB.getAuctionAuditState()&&auctionByDB.getAuctionAuditState()==1
							&&null!=warehouseByDB.getAuditState()&&warehouseByDB.getAuditState()==1) {
						auction.setAuditState(1);
					}
				}
				ProcedureBill procedure=new ProcedureBill();
				procedure.setId(auditFlagId);
				procedure.setAuditState(isPass==0?1:-1);
				procedureDao.updateProcedureBillBySelective(procedure);*/
				break;
			case 2://入库
				/*if(isPass!=0) {
					auction.setStockState(3);//已驳回
					auction.setAuditState(-1);
				}else {
					if(null!=auctionByDB.getAuctionAuditState()&&auctionByDB.getAuctionAuditState()==1
							&&null!=procedureByDB.getAuditState()&&procedureByDB.getAuditState()==1) {
						auction.setAuditState(1);
					}
				}
				Warehouse warehouse=new Warehouse();
				warehouse.setId(auditFlagId);
				warehouse.setAuditState(isPass==0?1:-1);
				warehouseDao.updateWarehouseSelective(warehouse);*/
				break;
			default:
				break;
			}
			//修改车辆参拍审核状态
			auctionDao.updateAuctionState(auction);
			
			auctionAuditDao.addAuctionAudit(auctionAudit);
		}else{
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			return res;
		}
		return res;
	}
	/**
	 * TODO getAuctionById(查询拍品审核信息)
	 */
	public AuctionAudit getAuctionAuditById(String id) {
		return auctionAuditDao.getAuctionAuditById(id);
	}
	
}
