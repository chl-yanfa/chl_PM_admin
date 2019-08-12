$(function() {
	initCombobox();
});
function initCombobox(){
	$('#paimaiStartTime').datetimebox({
		required:true,
		editable : false,
		width : 200
	});
	$('#paimaiFirstPauseTime').datetimebox({
		required:true,
		editable : false,
		width : 200,
		showSeconds : false
	});
	
}
function addPaimai(){
	if (!$('#addPaimai').form("validate")) {
		$.messager.alert('提示', "请完善信息");
		return;
	}
	var ff = "";
	ff = $('#addPaimai input,#addPaimai textarea').serialize();
	$.post("../../paimai/addPaimai",ff,function(res) {
		if(res.returnCode='000000'){
			$.messager.alert('提示', "提交成功",'info',function(){
				window.location.href="../../html/paimai/paimaiList.html";
			});
		}
	});
}
function cancel(){
	window.location.href="../../html/paimai/paimaiList.html";
}