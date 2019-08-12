$(function() {
	initAuctionProcedure();
});
function initAuctionProcedure(){
	var procedureId = getParamValue("procedureId");
	$("#procedureBody").load("procedureTemp.html",function(){
		initProcedureHistory(procedureId);
		$("#vehicleBody").load("../auction/auctionTemp.html",function(){
			var id = getParamValue("id");
			$.post("../../procedure/getAuctionProcedureById",{
				auctionId : id,
			},function(res) {
				var auction=res.entity.auction;
				var procedure=res.entity.procedure;
				loadAuctionDetail("#vehicleInfo","#carPhotoUrl",auction);
				/*手续*/
				initPProcedureData("#addProcedure",procedure);
			});
		});
	});
	$.post("../../procedure/getProcedureRecord", {
		procedureId : procedureId,
	}, function(data) {
		if(data.returnCode=="000000"&&data.entity!=null){
			$("#record").show();
			$("#procedureRecord").form('load', data.entity);
		}else{
			$("#notRecord").show();
		}
	});
}
function initProcedure(){
	$("#addProcedure input[name='fileIds']").change(function(){
		var value = $(this).val();
		for (var i = 1; i <= 12; i++) {
			var istrue = $('#file-img-'+i).is(':hidden');
			if (!istrue) {
				$("#cb-" + i).prop("checked", true);
			}else{
				$("#cb-" + i).prop("checked", false);
			}
		}
		if(value==0){
			var istrue = $('#file-img-00').is(':hidden');
			if (!istrue) {
				$("#cb-0").prop("checked", true);
			}else{
				$("#cb-0").prop("checked", false);
			}
			var istrue = $('#file-img-01').is(':hidden');
			if (!istrue) {
				$("#cb-0").prop("checked", true);
			}else{
				$("#cb-0").prop("checked", false);
			}
		}
	});
}
