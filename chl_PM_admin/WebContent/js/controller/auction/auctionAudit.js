$(function() {
	initAuditData();
});
function initAuditData(){
	$("#vehicleBody").load("auctionTemp.html",function(){
		$("#procedureBody").load("../procedure/procedureTemp.html",function(){
			$("#warehouseBody").load("../warehouse/warehouseTemp.html",function(){
				var id = getParamValue("id");
				$.post("../../auction/getVPWById",{
					id : id,
				},function(res) {
					var auction=res.entity.auction;
					var procedure=res.entity.procedure;
					var warehouse=res.entity.warehouse;
					/*车辆*/
					loadVehicleData("#vehicleInfo",auction);
					/*手续*/
					initProcedureData("#addProcedure",procedure);
					/*入库*/
					initWarehouseData("#addWarehouse",auction,warehouse);
					if(auction.auctionAuditState==null){
						$('#vechileAudit').hide();
					}else if(auction.auctionAuditState==1){
						$('#vechileAudit').hide();
						$('#vechileAuditY').show();
					}else if(auction.auctionAuditState==-1){
						$('#vechileAudit').hide();
						$('#vechileAuditN').show();
					}
					if(procedure.auditState==null){
						$('#procedureAudit').hide();
					}else if(procedure.auditState==1){
						$('#procedureAudit').hide();
						$('#procedureAuditY').show();
					}else if(procedure.auditState==-1){
						$('#procedureAudit').hide();
						$('#procedureAuditN').show();
					}
					if(warehouse.auditState==null){
						$('#warehouseAudit').hide();
					}else if(warehouse.auditState==1){
						$('#warehouseAudit').hide();
						$('#warehouseAuditY').show();
					}else if(warehouse.auditState==-1){
						$('#warehouseAudit').hide();
						$('#warehouseAuditN').show();
					}
				});
			});
		});
	});
}
/**
 * 
 * _type(auditFlag):车辆-0，手续-1，入库-2
 * _isPass:是否通过：通过-0 驳回-1
 * _dialog:驳回弹窗id；没有则''
 */
function auctionAudit(_type,_isPass,_dialog){
	var id=$("form[id='vehicleInfo'] input[name='id']").val();
	var auditFlagId='';
	var noPassReason='';
	if(_type==0){
		auditFlagId=$("form[id='vehicleInfo'] input[name='id']").val();
	}else if(_type==1){
		auditFlagId=$("form[id='addProcedure'] input[name='id']").val();
	}else if(_type==2){
		auditFlagId=$("form[id='addWarehouse'] input[name='id']").val();
	}
	if(_isPass==1){
		noPassReason=$("#noPassReason").val();
	}
	$.ajax({
		type : 'post',
		url : '../../auctionAudit/addAuctionAudit',
		dataType : "json",
        data:{	'auctionId':id,
        		'isPass':_isPass,
        		'auditFlag':_type,
        		'auditFlagId':auditFlagId,
        		'noPassReason':noPassReason
        },
		success : function(data) {
			if(_isPass==0){
				if (data.returnCode == "000000") {
					$.messager.alert('提示','审核通过');
					if(_type==0){
						$('#vechileAudit').hide();
						$('#vechileAuditY').show();
					}
					if(_type==1){
						$('#procedureAudit').hide();
						$('#procedureAuditY').show();
					}
					if(_type==2){
						$('#warehouseAudit').hide();
						$('#warehouseAuditY').show();
					}
				}else {
					$.messager.alert('提示', "审核失败");
				}
			}else{
				if (data.returnCode == "000000") {
					$.messager.alert('提示', "驳回成功",'info',function(){
						$(_dialog).dialog('close');
					});
					if(_type==0){
						$('#vechileAudit').hide();
						$('#vechileAuditN').show();
					}
					if(_type==1){
						$('#procedureAudit').hide();
						$('#procedureAuditN').show();
					}
					if(_type==2){
						$('#warehouseAudit').hide();
						$('#warehouseAuditN').show();
					}
				} else {
					$.messager.alert('提示', "驳回失败");
				}
			}
		},
		error : function(e) {
			$.messager.alert('提示', "失败");
		}
       
	});
}
//驳回
function auctionReject(_type){
	var id=$("form[id='vehicleInfo'] input[name='id']").val();
	$('#REJECT_DIALOG').dialog({
        title: '信息审核',
        width: 500,
        height: 300,
        modal: true,
        resizable: true,
        href:'../../html/auction/auditReject.html',
        buttons:[
			{id:'submitFormBtn', text:'确定', handler:function(){
				auctionAudit(_type,1,'#REJECT_DIALOG')
			}},
			{text:'取消', handler:function(){
				$('#REJECT_DIALOG').dialog("close");
			}
		}],
    });
}
