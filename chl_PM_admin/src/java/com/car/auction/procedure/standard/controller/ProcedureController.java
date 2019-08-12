package com.car.auction.procedure.standard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.auction.dto.EntityDto;
import com.car.auction.common.BaseController;
import com.car.auction.common.ResultDTO;
import com.car.auction.procedure.dto.ProcedureDto;
import com.car.auction.procedure.dto.ProcedurePlacingDto;
import com.car.auction.procedure.entity.Procedure;
import com.car.auction.procedure.entity.ProcedureHistory;
import com.car.auction.procedure.entity.ProcedurePlacingAudit;
import com.car.auction.procedure.entity.ProcedureRecord;
import com.car.auction.procedure.entity.ProcedureStatistics;
import com.car.auction.procedure.entity.ProcedureWhole;
import com.car.auction.procedure.standard.service.ProcedureService;
import com.car.auction.procedure.vo.ProcedureVo;


/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedureBillController
 * 类描述：手续Controller
 * 创建人：张婉欣
 * 创建时间： 2018年7月14日 下午4:36:30
 * @version
 */
@Controller
@RequestMapping(value = "/procedure")
public class ProcedureController extends BaseController{
	
	@Autowired
	private ProcedureService procedureService;
	/**
	 * getProcedureList
	 * 手续查询
	 */
	@RequestMapping(value = "/getProcedureList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<ProcedureWhole> getProcedureList(HttpSession session,ProcedureDto procedure) {
		return procedureService.getProcedureList(session,procedure);
	}
	/**
	 * getAuctionProcedureById 根据拍品id查询(拍品信息，入库手续)
	 */
	@RequestMapping(value = "/getAuctionProcedureById", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<EntityDto> getAuctionProcedureById(String auctionId) {
		return procedureService.getAuctionProcedureById(auctionId);
	}
	/**
	 * getProcedureByAuctionId
	 * 根据拍品id查询入库手续
	 */
	@RequestMapping(value = "/getProcedureByAuctionId", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Procedure> getProcedureByAuctionId(String auctionId) {
		return procedureService.getProcedureByAuctionId(auctionId);
	}
	/**
	 * addProcedure
	 * 手续入库
	 */
	@RequestMapping(value = "/addProcedure", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addProcedure(ProcedureVo procedureVo) {
		return procedureService.addProcedure(procedureVo);
	}
	/**
	 * getOutofProcedure
	 * 出库手续页面查询
	 * 根据procedureId获取已入库手续用来出库
	 */
	@RequestMapping(value = "/getOutofProcedure", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<ProcedurePlacingDto> getOutofProcedure(String procedureId) {
		return procedureService.getOutofProcedure(procedureId);
	}
	/**
	 * outofProcedure
	 * 手续出库申请
	 */
	@RequestMapping(value = "/outofProcedure", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> outofProcedure(ProcedurePlacingDto dto) {
		return procedureService.outofProcedure(dto);
	}
	/**
	 * getOutofProcedureAudit
	 * 出库手续申请查询
	 */
	@RequestMapping(value = "/getOutofProcedureAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<ProcedureHistory> getOutofProcedureAudit(String procedureId) {
		return procedureService.getOutofProcedureAudit(procedureId);
	}
	/**
	 * outofProcedureAudit
	 * 手续出库申请审核
	 */
	@RequestMapping(value = "/outofProcedureAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> outofProcedureAudit(ProcedurePlacingAudit dto) {
		return procedureService.outofProcedureAudit(dto);
	}
	/**
	 * addProcedureRecord
	 * 手续完结归档
	 */
	@RequestMapping(value = "/addProcedureRecord", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addProcedureRecord(HttpServletRequest request,ProcedureRecord dto) {
		return procedureService.addProcedureRecord(request,dto);
	}
	/**
	 * getProcedureRecord
	 * 查询手续完结归档
	 */
	@RequestMapping(value = "/getProcedureRecord", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<ProcedureRecord> getProcedureRecord(String procedureId) {
		return procedureService.getProcedureRecord(procedureId);
	}
	/**
	 * getProcedureHistory
	 * 出入库记录查询
	 */
	@RequestMapping(value = "/getProcedureHistory", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<ProcedureHistory> getProcedureHistory(String procedureId,Integer page,Integer rows) {
		return procedureService.getProcedureHistory(procedureId,page,rows);
	}
	/**
	 * getProcedureStatistics
	 * 获取手续统计
	 */
	@RequestMapping(value = "/getProcedureStatistics", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<ProcedureStatistics> getProcedureStatistics(HttpSession session,ProcedureDto procedure) {
		return procedureService.getProcedureStatistics(session,procedure);
	}

}
