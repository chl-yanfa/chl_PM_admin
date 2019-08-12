$(document).ready(function() {
	getOutToAudit();
	initAuctionWarehouse();
});
function getOutToAudit(){
	var stockId = getParamValue("stockId");
	$.post("../../warehouse/getOutOfWarehouseToAudit", {
		stockId : stockId,
	}, function(res) {
		var data = res.entity;
		if(data==null){
			$.messager.alert('提示','没有要审核的出库申请','info',function(){
				window.location.href="../../html/warehouse/warehouseList.html";
			});
		}else{
			$("#outAudit").form('load', data);
			$("#outAudit input,#outAudit textarea").attr("readonly", true);
		}
	});
}
function initAuctionWarehouse(){
	$("#warehouseBody").load("warehouseTemp.html",function(){
		$("#vehicleBody").load("../auction/auctionTemp.html",function(){
			var id = getParamValue("id");
			$.post("../../warehouse/getAuctionWarehouseById",{
				auctionId : id,
			},function(res) {
				initWareCombobox(true);
				var auction=res.entity.auction;
				var warehouse=res.entity.warehouse;
				if(auction.auctionType==0){
					/*车辆*/
					loadVehicleData("#vehicleInfo",auction);								
				}else if(auction.auctionType==1){
					$("#goodsInfo").show();
					$("#vehicleInfo").hide();
					/*物资*/
					loadGoodsData("#goodsInfo",auction);
				}
				initWarehouseData("#addWarehouse",auction,warehouse);
			});
		});
	});
}

/**
 * _isPass 同意|拒绝 0-同意 1-拒绝
 * 
 */
function outAudit(_isPass,_dialog){
	var placingId = $('#id').val();
	var remark='';
	if(_isPass==1){
		remark=$("#noPassReason").val();
	}
	$.ajax({
		type : 'post',
		url : '../../warehouse/addPlacingAudit',
		dataType : "json",
        data:{	
			'placingId':placingId,
			'isPass':_isPass,
			'remark':remark
        },
		success : function(data) {
			if (data.returnCode == "000000") {
				if(_isPass==0){
					$.messager.alert('提示','审核通过','info',function(){
						window.location.href="../../html/warehouse/warehouseList.html";	
					});
				}else{
					$('#abnormal_DIALOG').dialog("close");	
					$.messager.alert('提示','驳回成功','info',function(){
						window.location.href="../../html/warehouse/warehouseList.html";
					});
				}
			}else {
				if(_isPass==0){
					$.messager.alert('提示', "审核失败");
				}else{
					$.messager.alert('提示', "驳回失败");
				}
			}
		},
		error : function(e) {
			$.messager.alert('提示', "失败");
		}
       
	});
}
//拒绝
function openReject(){
	$('#abnormal_DIALOG').dialog({
        title: '出库审核',
        width: 500,
        height: 300,
        modal: true,
        resizable: true,
        href:'../../html/auction/auditReject.html',
        buttons:[
			{id:'submitFormBtn', text:'确定', handler:function(){
				outAudit(1,"abnormal_DIALOG");
			}},
			{text:'取消', handler:function(){
				$('#abnormal_DIALOG').dialog("close");
				
			}
		}],
    });
}
