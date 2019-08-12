function initSetHtml(){
	initSetData();
}
function initSetAuditHtml(){
	initData();
}
function initSetData(){
	$("#vehicleBody").load("auctionTemp.html",function(){
		var id = getParamValue("id");
		$.post("../../procedure/getAuctionProcedureById",{
			auctionId : id,
		},function(res) {
			var auction=res.entity.auction;
			var procedure=res.entity.procedure;
			
			loadAuctionDetail("#vehicleInfo","#carPhotoUrl",auction);
			
			if(auction.setState!=0){
				getAuctionSetData(auction.id);				
			}
			if(auction.setState==1){
				$("#setBtnID").hide();
				$("#setTestBtn").html("已设置");
				$("#setedBtnID").show();
				$("#auctionSet input,#auctionSet textarea").attr("readonly", true);
				$("#auctionSet input[type='checkbox'],#auctionSet input[type='radio']").click(function(){
					return false;
				});
			}else if(auction.setState==2){
				$("#setBtnID").hide();
				$("#setTestBtn").text("待审核");
				$("#setedBtnID").show();
			}else if(auction.setState==3){
				$("#setBtnID").hide();
				$("#setTestBtn").text("已驳回");
				$("#setedBtnID").show();
			}
		});
	});
}
function initSetAuditData(){
	$("#procedureBody").load("../procedure/procedureTemp.html",function(){
		var id = getParamValue("id");
		$.post("../../procedure/getAuctionProcedureById",{
			auctionId : id,
		},function(res) {
			var auction=res.entity.auction;
			var procedure=res.entity.procedure;
			/*车辆*/
			loadAuctionDetail("#vehicleInfo","#carPhotoUrl",auction);
			/*手续*/
			initProcedureData("#addProcedure",procedure);
		});
		
	});
}
function getAuctionSetData(id){
	$.post("../../auctionSet/getAuctionSetByAuctionId",{
		auctionId : id,
	},function(res) {
		$('#auctionSet').form('load', res.entity);
	});
}
function submitSet(){
	if (!$('#auctionSet').form("validate")) {
		return;
	}
	var auctionId=$("#vehicleInfo input[name='id']").val();
	var ff = "";
	ff = $('#auctionSet input,#auctionSet textarea').serialize();
	ff += "&auctionId=" + auctionId;
	$.ajax({type: 'post', url:'../../auctionSet/addAuctionSet', dataType: "json",
		data:ff,
		success:function(data){
			if (data.returnCode == "000000") {
				$.messager.alert('提示','参拍设置成功','info',function(){
					window.location.href="../../html/auction/auctionList.html";	
				});
			}else {
				$.messager.alert('提示', "参拍设置失败");
			}
		},
		error : function(e) {
			$.messager.alert('提示', "失败");
		}
	});
}